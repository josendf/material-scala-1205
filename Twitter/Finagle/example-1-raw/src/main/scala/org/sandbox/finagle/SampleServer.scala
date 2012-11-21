package org.sandbox.finagle

import com.twitter.finagle.Service
import com.twitter.finagle.builder.{Server, ServerBuilder}
import com.twitter.util.Future
import java.net.InetSocketAddress

/**
  * A very simple service that simply echos its request back
  * as a response. Note that it returns a Future, since everything
  * in Finagle is asynchronous.
  */

object SampleServer {

  def main(args: Array[String]) {

    val service = new Service[String, String] {

      def apply(request: String) = Future.value(request)

    }

    val address = new InetSocketAddress(10000)

    // Bind the service to port
    val server = ServerBuilder()
      .codec(SampleCodec)
      .bindTo(address)
      .name("SampleServer")
      .build(service)
  }
}
