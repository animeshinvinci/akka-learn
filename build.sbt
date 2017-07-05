
name := "akka-learn"

version := "1.0"

scalaVersion := "2.12.1"

organization := "io.animesh.learn"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies ++= {
  val akkaVersion = "2.4.12"
  val akkaHttpVersion = "10.0.0"

  Seq(
    "com.typesafe.akka" %% "akka-actor"           % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"           % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit"         % akkaVersion   % "test",
    "com.typesafe.akka" %% "akka-remote"          % akkaVersion ,
    "com.typesafe.akka" %%"akka-cluster"          % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core"       % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion


  )



}

