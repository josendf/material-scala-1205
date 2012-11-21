package org.sandbox.finagle

import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.finagle.builder.{Server, ServerBuilder}
import com.twitter.finagle.http.Http
import com.twitter.util.Future
import java.net.InetSocketAddress
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.handler.codec.http.HttpResponseStatus._
import org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1
import org.jboss.netty.util.CharsetUtil.UTF_8

/**
 * This example demonstrates a sophisticated HTTP server that handles exceptions
 * and performs authorization via a shared secret. The exception handling and
 * authorization code are written as Filters, thus isolating these aspects from
 * the main service (here called "Respond") for better code organization.
 *
    * curl 127.0.0.1:8099 -v
    *
    * curl 127.0.0.1:8099 -v -H "Authorization:MY_TICKET"
 */

object SampleServer {

  /**
   * A simple Filter that catches exceptions and converts them to appropriate
   * HTTP responses.
   */
  class HandleExceptions extends SimpleFilter[HttpRequest, HttpResponse] {

    def apply(request: HttpRequest,
              service: Service[HttpRequest, HttpResponse]) = {

      // `handle` asynchronously handles exceptions.
      service(request) handle { case error =>

        val statusCode = error match {
            case _: IllegalArgumentException => FORBIDDEN
            case _                           => INTERNAL_SERVER_ERROR
        }

        val resp = new DefaultHttpResponse(HTTP_1_1, statusCode)
        val msg  = error.getStackTraceString
        resp.setContent(copiedBuffer(msg, UTF_8))

        resp
      }
    }
  }

  /**
   * A simple Filter that checks that the request is valid by inspecting the
   * "Authorization" header.
   */
  class Authorize extends SimpleFilter[HttpRequest, HttpResponse] {

    def apply(request: HttpRequest,
              continue: Service[HttpRequest, HttpResponse]) = {

      if ("MY_TICKET" == request.getHeader("Authorization"))
          continue(request)
      else
          Future.exception(new IllegalArgumentException("Not authorized."))

    }
  }

  /**
   * The service itself. Simply echos back "hello world"
   */
  class Respond extends Service[HttpRequest, HttpResponse] {

    def apply(request: HttpRequest) = {

      val resp = new DefaultHttpResponse(HTTP_1_1, OK)

      resp.addHeader("Content-Type", "text/plain")
      resp.setContent(copiedBuffer("Hello world.\r\n", UTF_8))

      Future.value(resp)
    }
  }

  def main(args: Array[String]) {

    val handleExceptions = new HandleExceptions
    val authorize        = new Authorize
    val respond          = new Respond

    // compose the Filters and Service together:
    val service: Service[HttpRequest, HttpResponse] =
      handleExceptions andThen
      authorize        andThen
      respond

    val address = new InetSocketAddress(8099)

    val server: Server = ServerBuilder()
      .codec(Http())
      .bindTo(address)
      .name("SampleServer")
      .build(service)
  }
}
