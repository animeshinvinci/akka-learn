
name := "akka-learn"

version := "1.0"

scalaVersion := "2.12.1"

organization := "io.animesh.learn"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies ++= {
  val akkaVersion = "2.4.12"
  Seq(
    "com.typesafe.akka" %% "akka-actor"      % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
    "ch.qos.logback"    %  "logback-classic" % "1.1.3",
    "com.typesafe.akka" %% "akka-testkit"    % akkaVersion   % "test",
    "com.typesafe.akka" %% "akka-remote"     % akkaVersion ,
    "com.typesafe.akka" % "akka-cluster_2.11" % akkaVersion

  )



}

