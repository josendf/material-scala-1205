package org.example

import akka.actor._
import akka.routing.RoundRobinRouter
import akka.util.Duration
import akka.util.duration._

object MyApp extends App {

  run()

  sealed trait MyMessage

  case object StartMessage
       extends MyMessage

  case class DoWorkMessage(startElement: Int, numElements: Int)
       extends MyMessage

  case class PartialResultMessage(value: Double)
       extends MyMessage

  case class FinalResultMessage(pi: Double, elapsed: Duration)
       extends MyMessage

  class WorkerActor
    extends Actor {

    def calculatePiFor(startElement: Int, numElements: Int): Double = {
      var acc = 0.0
      for (i <- startElement until (startElement + numElements))
        acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
      acc
    }

    def receive = {
      case DoWorkMessage(startElement, numElements) =>
        val res = calculatePiFor(startElement, numElements)  // perform the work
        sender ! PartialResultMessage(res)
    }
  }

  class MasterActor(numWorkers: Int,
                    numMessages: Int,
                    numElements: Int,
                    listener: ActorRef)
    extends Actor {

    val started: Long = System.currentTimeMillis
    val workerRouter = context.actorOf(Props[WorkerActor]
      .withRouter(RoundRobinRouter(numWorkers)), name = "workerRouter")

    var pi: Double = _
    var resultCount: Int = _

    def receive = {
      case StartMessage =>
        for (i <- 0 until numMessages)
          workerRouter ! DoWorkMessage(i * numElements, numElements)

      case PartialResultMessage(value) =>
        pi += value
        resultCount += 1
        if (resultCount == numMessages) {
          val elapsed = System.currentTimeMillis - started
          listener ! FinalResultMessage(pi, elapsed.millis)
          context.stop(self)
        }
    }
  }

  class ListenerActor
    extends Actor {
    def receive = {
      case FinalResultMessage(pi, elapsed) =>
        println(
          """
            Result: %s elapsed: %s
          """.format(pi, elapsed))
        context.system.shutdown()
    }
  }

  def run() {
    calculatePi(numWorkers = 4,
                numElements = 10000,
                numMessages = 10000)
  }

  def calculatePi(numWorkers: Int, numElements: Int, numMessages: Int) {

    // Create an Akka system
    val system = ActorSystem("MySystem")

    // create the result listener, which will print the result and
    // shutdown the system
    val listener = system.actorOf(Props[ListenerActor], name = "listener")

    // create the master
    val master = system.actorOf(Props(
      new MasterActor(numWorkers,
                      numMessages,
                      numElements,
                      listener)),
      name = "master")

    // start the calculation
    master ! StartMessage

  }
}
