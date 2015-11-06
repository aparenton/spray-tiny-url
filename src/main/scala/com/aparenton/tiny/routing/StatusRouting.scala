package com.aparenton.tiny.routing

import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.routing.HttpService

object StatusResponse extends DefaultJsonProtocol {
  implicit val statusResponseFormat = jsonFormat1(StatusResponse.apply)
}

final case class StatusResponse(status: String)

/**
 * Simple route used for healthcheck.
 */
trait StatusRouting extends HttpService with SprayJsonSupport {
  val statusRoutes = {
    path("status") {
      get {
        complete(StatusResponse("healthy"))
      }
    }
  }
}
