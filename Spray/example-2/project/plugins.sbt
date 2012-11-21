resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"
libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v + "-0.2.11.1"))

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
addSbtPlugin("com.typesafe.akka" % "akka-sbt-plugin" % "2.0.1")
