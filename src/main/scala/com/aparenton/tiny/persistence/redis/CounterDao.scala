package com.aparenton.tiny.persistence.redis

import scala.concurrent.Future

object CounterDao {
  val CounterKey = "tiny-url:counter"

  def save(value: String): Future[Boolean] = RedisMaster.redis.set(CounterKey, value)

  def get: Future[Option[String]] = RedisMaster.redis.get(CounterKey)
}
