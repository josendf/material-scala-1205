resolvers ++= Seq(
   "twitter"   at "http://maven.twttr.com/"
)

addSbtPlugin("org.ensime" % "ensime-sbt-cmd" % "0.0.7")

addSbtPlugin("com.twitter" % "sbt-scrooge2" % "0.0.1")

