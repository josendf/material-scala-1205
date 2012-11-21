package org.sandbox.myservice

import com.twitter.finagle.thrift.ThriftClientFramedCodec
import com.twitter.finagle.builder.{ClientBuilder}
import java.net.InetSocketAddress
import org.apache.thrift.protocol.TBinaryProtocol

import org.sandbox.myservice.thrift.{MyService}

class SimpleClient {

  val address = new InetSocketAddress(9999)

  val service = ClientBuilder()
     .hosts(address)
     .codec(ThriftClientFramedCodec())
     .hostConnectionLimit(1)
     .build()

  val client = new MyService.FinagledClient(service,
                                             new TBinaryProtocol.Factory())

  def myGet(key: String) = client.myGet(key)()

  def myGetAsync(key: String) = client.myGet(key)

  def myPut(key: String, value: String) = client.myPut(key, value)()

  def myPutAsync(key: String, value: String) = client.myPut(key, value)
}
