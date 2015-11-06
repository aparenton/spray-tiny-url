package com.aparenton.tiny

import java.net.MalformedURLException

import akka.actor.Actor
import com.aparenton.tiny.routing.{TinyUrlRouting, StatusRouting}
import spray.http.StatusCodes
import spray.routing.{ExceptionHandler, HttpService}
import spray.util.LoggingContext

class TinyUrlActor extends Actor
with HttpService
with TinyUrlRouting
with StatusRouting {
  def actorRefFactory = context

  val routes = tinyUrlRoutes ~ statusRoutes

  implicit def myExceptionHandler(implicit log: LoggingContext) = ExceptionHandler {
    case e: MalformedURLException => complete(StatusCodes.BadRequest, s"Malformed URL - ${e.getMessage}")
  }

  def receive = runRoute(routes)
}
