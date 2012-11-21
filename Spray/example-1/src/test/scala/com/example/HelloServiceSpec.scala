package com.example

import org.specs2.mutable._
import cc.spray._
import test._
import http._
import HttpMethods._
import StatusCodes._

class ExampleServiceSpec extends Specification with SprayTest with ExampleService {
  
  "The ExampleService" should {
    "return a greeting for GET requests to the root path" in {
      testService(HttpRequest(GET, "/")) {
        exampleService
      }.response.content.as[String] mustEqual Right("Say hello to Spray!")
    }
    "leave GET requests to other paths unhandled" in {
      testService(HttpRequest(GET, "/kermit")) {
        exampleService
      }.handled must beFalse
    }
    "return a MethodNotAllowed error for POST requests to the root path" in {
      testService(HttpRequest(POST, "/")) {
        exampleService
      }.response mustEqual HttpResponse(MethodNotAllowed, "HTTP method not allowed, supported methods: GET")
    }
  }
  
}