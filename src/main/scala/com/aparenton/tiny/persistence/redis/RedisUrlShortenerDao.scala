package com.aparenton.tiny.persistence.redis

import java.net.URL

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration
import com.aparenton.tiny.util.Configuration._

object RedisUrlShortenerDao extends RedisUrlShortenerDao

class RedisUrlShortenerDao {
  private def key(suffix: String): String = s"url-$suffix"

  def create(id: String, url: URL): Future[Boolean] = {
    RedisMaster.redis.set(
      key(id),
      url.toString,
      Some(FiniteDuration.apply(urlExpiration, urlTimeUnit))
    )
  }

  def get(id: String): Future[Option[String]] = {
    RedisMaster.redis.get(key(id))
  }
}