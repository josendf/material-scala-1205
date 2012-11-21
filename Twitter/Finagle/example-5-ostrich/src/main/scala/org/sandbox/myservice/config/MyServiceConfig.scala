package org.sandbox.myservice.config

import com.twitter.logging.Logger
import com.twitter.logging.config._
import com.twitter.ostrich.admin.{RuntimeEnvironment, ServiceTracker}
import com.twitter.ostrich.admin.config._
import com.twitter.util.Config

import org.sandbox.myservice.MyServiceImpl
import org.sandbox.myservice.thrift.MyService

/*
 * A ServerConfig class contains things you want to configure on your server,
 * as vars, and an apply method that turns a RuntimeEnvironment
 * into your server.
 */
class MyServiceConfig extends ServerConfig[MyService.ThriftServer] {

  var thriftPort: Int = 9999

  def apply(runtime: RuntimeEnvironment) = new MyServiceImpl(this)
}
