package org.sandbox.myservice

import com.twitter.ostrich.admin.RuntimeEnvironment

import org.sandbox.myservice.thrift.MyService

object Main {
  def main(args: Array[String]) {

    val env = RuntimeEnvironment(this, args)

    val server = env.loadRuntimeConfig[MyService.ThriftServer]

    server.start()
  }
}
