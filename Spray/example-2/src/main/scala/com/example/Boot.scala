package com.example

import cc.spray._
import akka.actor.{Props, ActorSystem}

// this class is instantiated by the servlet initializer,
// which also creates and shuts down the ActorSystem passed
// as an argument to this constructor
class Boot(system: ActorSystem) {

  val mainModule = new ExampleService {
    implicit def actorSystem = system
    // bake your module cake here
  }
  val httpService = system.actorOf(
    props = Props(new HttpService(mainModule.exampleService)),
    name = "example-service"
  )
  val rootService = system.actorOf(
    props = Props(new RootService(httpService)),
    // must match the name in the config so the ConnectorServlet can find the actor
    name = "spray-root-service"
  )
  system.registerOnTermination {
    // put additional cleanup code clear
    system.log.info("Application shut down")
  }

}
