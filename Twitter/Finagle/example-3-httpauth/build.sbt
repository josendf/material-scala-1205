name := "example-3-httpauth"

version := "0.1"

scalaVersion := "2.9.1"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

resolvers ++= Seq(
   "snapshots" at "http://scala-tools.org/repo-snapshots/"
  ,"releases"  at "http://scala-tools.org/repo-releases/"
  ,"twitter"   at "http://maven.twttr.com/"
)

libraryDependencies ++= Seq(
//    "org.specs2"              %% "specs2"             % "1.6.1" % "test"
//  , "org.specs2"              %% "specs2-scalaz-core" % "6.0.1" % "test"
//  , "org.scala-tools.testing" %% "scalacheck"         % "1.9"   % "test"
    "com.twitter"             %% "finagle-core"       % "2.0.1"
  , "com.twitter"             %% "finagle-http"       % "2.0.1"
)
