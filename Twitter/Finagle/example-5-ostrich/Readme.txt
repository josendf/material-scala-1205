Scala at Twitter
================
http://twitter.github.com/scala_school/


Technologies
============

Scala
-----
http://www.scala-lang.org/

Scala is a general purpose programming language designed
to express common programming patterns in a concise, elegant, and type-safe way.


SBT
---
https://github.com/harrah/xsbt/wiki

Sbt is a build tool for Scala and Java projects that aims
to do the basics well.


Sbt-idea
--------
https://github.com/mpeltonen/sbt-idea

A simple-build-tool (sbt) plugin/processor for creating IntelliJ IDEA 
project files.


Ensime
------
https://github.com/aemoncannon/ensime

ENnhanced Scala Interaction Mode for Emacs. 


standard-project
----------------
https://github.com/twitter/standard-project

A slightly more standard sbt project plugin library


Thrift
-------
http://thrift.apache.org/

Thrift is a software framework for scalable cross-language services
development. It combines a software stack with a code generation engine
to build services that work efficiently and seamlessly

Importante: La versión actual de Finagle usa una versión especial de
Thrift (https://github.com/mariusaeriksen/thrift-0.5.0-finagle)


Scrooge
-------
https://github.com/twitter/scrooge

A Thrift generator for Scala


sbt-scrooge
-----------
https://github.com/twitter/sbt-scrooge

An SBT plugin that adds a mixin for doing Thrift code auto-generation 
during your compile phase


Finagle
-------
https://github.com/twitter/finagle

A fault tolerant, protocol-agnostic RPC system.


Ostrich
-------
https://github.com/twitter/ostrich

A stats collector & reporter for Scala servers.


Querulous
---------
https://github.com/twitter/querulous

An agreeable way to talk to your database.


Gizzard
-------
https://github.com/twitter/gizzard

A flexible sharding framework for creating eventually-consistent 
distributed datastores.


Demo
====

Start Server (thrift + finagle)
-------------------------------

  $ sbt

  compile

  run -f config/development.scala


Use Client
----------

  $ sbt

  console

  import org.sandbox.myservice.SimpleClient
  val cli = new SimpleClient
  cli.myPut("hello", "world")

  cli.myGet("hello")
  
  cli.myGetAsync("hello") onSuccess {resp => println("Response: " + resp)}


Admin interface (ostrich)
-------------------------

  curl http://localhost:9900/stats
  curl http://localhost:9900/stats.txt

  curl http://localhost:9900/ping

  curl http://localhost:9900/shutdown

  curl http://localhost:9900/quiesce

  curl http://localhost:9900/server_info

  curl http://localhost:9900/threads

  curl http://localhost:9900/gc

  In browser: http://localhost:9900/graph/
