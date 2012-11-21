package com.example

import cc.spray._
import cc.spray.json._
import cc.spray.typeconversion.SprayJsonSupport._ 

trait ExampleService extends Directives {

  val exampleService = {

    path("") {
      get {
        completeWith("Say hello to Spray!")
      }
    } ~
    path("api" / "v1") {
      get {
        completeWith("This is API version 1.0")
      }
    } ~
    path("api" / "v1" / "json") {
      get {
        import MediaBlobJsonProtocol._
        var blob = MediaBlob("0123456789", "abcdefg", "image/jpeg", "2012-01-02T:03:04Z")
        completeWith(blob)
      }
    }
  }
}
