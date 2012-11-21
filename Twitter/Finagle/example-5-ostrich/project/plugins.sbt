resolvers ++= Seq(
    "twitter"       at "http://maven.twttr.com/"
  , "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
)

libraryDependencies ++= Seq(
    "thrift"                   %  "libthrift"         % "0.5.0" from "http://maven.twttr.com/thrift/libthrift/0.5.0/libthrift-0.5.0.jar"
  , "com.twitter"             %% "scrooge-runtime"    % "1.1.3"
  , "com.twitter"             %% "util-core"          % "1.12.13"
  , "com.twitter"             %% "util-eval"          % "1.12.13"
  , "com.twitter"             %% "util-logging"       % "1.12.13"
  , "com.twitter"             %% "ostrich"            % "4.10.5"
  , "com.twitter"             %% "finagle-core"       % "2.0.1"
  , "com.twitter"             %% "finagle-http"       % "2.0.1"
  , "com.twitter"             %% "finagle-thrift"     % "2.0.1"
  , "com.twitter"             %% "finagle-ostrich4"   % "2.0.1"
)

addSbtPlugin("com.twitter"          % "sbt-scrooge2"   % "0.0.1")

addSbtPlugin("org.ensime"           % "ensime-sbt-cmd" % "0.0.7")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea"       % "1.0.0")
