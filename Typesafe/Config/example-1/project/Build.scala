import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name          := "Config Example 1",
      organization  := "org.example",
      version       := "0.1-SNAPSHOT",
      scalaVersion  := "2.9.2",

      scalacOptions ++= Seq("-deprecation","-unchecked"),

      resolvers     += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",

      libraryDependencies ++= Seq(
        "com.typesafe" % "config"      % "0.4.0"
        )
    )
  )
}
