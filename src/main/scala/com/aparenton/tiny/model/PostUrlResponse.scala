package com.aparenton.tiny.model

import spray.json.DefaultJsonProtocol

object PostUrlResponse extends DefaultJsonProtocol {
  implicit def postUrlResponseFormat = jsonFormat1(PostUrlResponse.apply)
}

case class PostUrlResponse(tinyUrl: String)
