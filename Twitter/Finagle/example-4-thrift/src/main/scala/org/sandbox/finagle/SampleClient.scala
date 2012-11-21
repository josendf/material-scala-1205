package org.sandbox.finagle

import com.twitter.finagle.thrift.ThriftClientFramedCodec
import com.twitter.finagle.builder.{ClientBuilder}
import com.twitter.util.Future
import java.net.InetSocketAddress
import org.apache.thrift.protocol.TBinaryProtocol

import org.sandbox.finagle.thrift.MyService

object SampleClient {

 def main(args: Array[String]) {

    // Create a raw Thrift client service. This implements the
    // ThriftClientRequest => Future[Array[Byte]] interface.

   val address = new InetSocketAddress(8099)

    val service = ClientBuilder()
      .hosts(address)
      .codec(ThriftClientFramedCodec())
      .hostConnectionLimit(1)
      .build()

    // Wrap the raw Thrift service in a Client decorator. The client
    // provides a convenient procedural interface for accessing the Thrift
    // server.
    val client = new MyService.FinagledClient(service,
                                              new TBinaryProtocol.Factory())

    client.myOperation() onSuccess { response =>
      println("Received response: " + response)
    } ensure {
      service.release()
    }
  }
}
