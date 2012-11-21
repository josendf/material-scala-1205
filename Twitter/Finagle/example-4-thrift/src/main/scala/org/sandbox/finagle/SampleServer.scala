package org.sandbox.finagle

import com.twitter.finagle.thrift.ThriftServerFramedCodec
import com.twitter.finagle.builder.{ServerBuilder, Server}
import com.twitter.util.Future
import java.net.InetSocketAddress
import org.apache.thrift.protocol.TBinaryProtocol

import org.sandbox.finagle.thrift.MyService

object SampleServer {

 def main(args: Array[String]) {

    // Implement the Thrift Interface
    val processor = new MyService.FutureIface {
      def myOperation() = Future.value("Hello...")
    }

    // Convert the Thrift Processor to a Finagle Service
    val service = new MyService.FinagledService(processor,
                                new TBinaryProtocol.Factory())

   val address = new InetSocketAddress(8099);

    val server: Server = ServerBuilder()
      .bindTo(address)
      .codec(ThriftServerFramedCodec())
      .name("SampleServer")
      .build(service)
  }
}
