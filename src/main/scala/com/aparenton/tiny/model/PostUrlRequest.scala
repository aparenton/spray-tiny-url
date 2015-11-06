package com.aparenton.tiny.model

import spray.json.DefaultJsonProtocol

object PostUrlRequest extends DefaultJsonProtocol {
  implicit def postUrlRequestFormat = jsonFormat1(PostUrlRequest.apply)
}

case class PostUrlRequest(url: String)