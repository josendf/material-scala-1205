import com.twitter.sbt._

name         := "example-4-thrift"

version      := "0.1"

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
    "org.apache.thrift"        % "libthrift"          % "0.5.0" intransitive
  , "com.twitter"             %% "scrooge-runtime"    % "1.1.3"
  , "com.twitter"             %% "finagle-core"       % "2.0.1"
  , "com.twitter"             %% "finagle-http"       % "2.0.1"
  , "com.twitter"             %% "finagle-thrift"     % "2.0.1"
  , "com.twitter"             %% "finagle-ostrich4"   % "2.0.1"
)

seq(CompileThriftScrooge.newSettings: _*)
