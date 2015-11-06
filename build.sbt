import sbt._
import Keys._

name := "spray-tiny-url"
organization := "com.aparenton"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= {
  val sprayVersion     = "1.3.2"
  val akkaVersion      = "2.3.9"

  Seq(
    // Akka
    "com.typesafe.akka"    %% "akka-actor"                  % akkaVersion exclude ("org.scala-lang" , "scala-library"),
    "com.typesafe.akka"    %% "akka-slf4j"                  % akkaVersion exclude ("org.slf4j", "slf4j-api") exclude ("org.scala-lang" , "scala-library"),
    "com.typesafe.akka"    %% "akka-testkit"                % akkaVersion % Test,
    // Spray
    "io.spray"             %% "spray-can"                   % sprayVersion,
    "io.spray"             %% "spray-httpx"                 % sprayVersion,
    "io.spray"             %% "spray-json"                  % sprayVersion,
    "io.spray"             %% "spray-routing"               % sprayVersion,
    "io.spray"             %% "spray-testkit"               % sprayVersion % Test,
    // Logging
    "ch.qos.logback"       %  "logback-classic"             % "1.1.3",
    "net.logstash.logback" %  "logstash-logback-encoder"    % "4.4",
    // Persistence
    "com.livestream"       %% "scredis"                     % "2.0.7-RC1",
    // Scalatest
    "org.scalatest"        %% "scalatest"                   % "2.2.4" % Test,
    "org.scalamock"        %% "scalamock-scalatest-support" % "3.2.1" % Test
  )
}

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

exportJars := true

crossPaths := false

parallelExecution in Test := false

assemblyJarName in assembly := "spray-tiny-url.jar"

assemblyMergeStrategy in assembly := {
  case x if x.contains("logback.xml") => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
