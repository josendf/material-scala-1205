package org.sandbox.myservice

import com.twitter.util.Future
import scala.collection.mutable

import config.MyServiceConfig
import org.sandbox.myservice.thrift.{MyService, MyServiceException}

class MyServiceImpl(config: MyServiceConfig)
  extends MyService.ThriftServer {

  val serverName = "MyService"

  val thriftPort = config.thriftPort

  /**
   * These services are based on finagle, which implements a nonblocking server.
   * If you are making blocking rpc calls, it's really important that you run
   * these actions in a thread pool, so that you don't block the main
   * event loop. This thread pool is only needed for these blocking actions.
   *
   * The code looks like:
   *     import java.util.concurrent.Executors
   *
   *     val futurePool =
   *        new FuturePool(Executors.newFixedThreadPool(config.threadPoolSize))
   *
   *     def hello() = futurePool {
   *       someService.blockingRpcCall
   *     }
   *
   */

  /**
   * Implement the Thrift Interface
   */
  val database = new mutable.HashMap[String, String]()

  def myGet(key: String) = {
    database.get(key) match {

      case None =>
          log.debug("get %s: miss", key)
          Future.exception(new MyServiceException("No such key"))

      case Some(value) =>
          log.debug("get %s: hit", key)
          Future(value)
    }
  }

  def myPut(key: String, value: String) = {
      log.debug("put %s", key)
      database(key) = value
      Future.Unit
  }
}
