package com.aparenton.tiny.routing

import java.net.URL
import java.util.concurrent.TimeUnit

import com.aparenton.tiny.model.{PostUrlResponse, PostUrlRequest}
import com.aparenton.tiny.service.UrlShortenerService
import com.typesafe.scalalogging.LazyLogging
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService
import com.aparenton.tiny.util.Configuration._

trait TinyUrlRouting extends HttpService with SprayJsonSupport with LazyLogging {
  implicit def ec = actorRefFactory.dispatcher
  implicit val timeout = akka.util.Timeout(10, TimeUnit.SECONDS)

  val urlShortenerService: UrlShortenerService = UrlShortenerService

  val tinyUrlRoutes =
    path("url") {
      hostName { host =>
        post {
          entity(as[PostUrlRequest]) { request =>
            val url = new URL(request.url)
            onSuccess(urlShortenerService.create(url)) { path =>
              val protocol = if (secure) "https" else "http"
              val base = host match {
                case "localhost" => s"localhost:$httpPort"
                case host => host
              }
              complete(PostUrlResponse(s"$protocol://$base/$path"))
            }
          }
        }
      }
    } ~
    path(Segment) { id =>
      get {
        onSuccess(urlShortenerService.get(id)) {
          case Some(url: String) => {
            logger.info(s"Redirecting to resolved URL for id: $id -> $url")
            redirect(url, StatusCodes.PermanentRedirect)
          }
          case _ => complete(StatusCodes.NotFound)
        }
      }
    }
}