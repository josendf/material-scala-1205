package org.sandbox.finagle

import com.twitter.finagle.Service
import com.twitter.finagle.builder.{Server, ServerBuilder}
import com.twitter.finagle.http.Http
import com.twitter.util.Future
import java.net.InetSocketAddress
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.handler.codec.http.HttpResponseStatus._
import org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1

/* Simple HTTP Server
 *
  * A very simple implementation of an HTTP server and
  * client in which clients make HTTP GET requests and the server responds
  * to each one with an HTTP 200 OK response.
  *
  */

object SampleServer {

  def main(args: Array[String]) {

    val service: Service[HttpRequest, HttpResponse] =

      new Service[HttpRequest, HttpResponse] {

        def apply(request: HttpRequest) =
          Future(new DefaultHttpResponse(HTTP_1_1, OK))

      }

    val address = new InetSocketAddress(10000)

    val server = ServerBuilder()
        .codec(Http())
        .bindTo(address)
        .name("SampleServer")
        .build(service)
  }
}
