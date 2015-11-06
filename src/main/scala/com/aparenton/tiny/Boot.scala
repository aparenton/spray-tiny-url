package com.aparenton.tiny

import java.util.concurrent.TimeUnit

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.util.Timeout
import com.aparenton.tiny.util.Configuration._
import spray.can.Http

object Boot extends App {
  implicit val system = ActorSystem("spray-tiny-url")

  val tinyUrlActor = system.actorOf(Props[TinyUrlActor], "tinyUrlActor")

  implicit val timeout = Timeout(5, TimeUnit.SECONDS)

  IO(Http) ! Http.Bind(listener = tinyUrlActor, interface = httpHost, port = httpPort)
}
