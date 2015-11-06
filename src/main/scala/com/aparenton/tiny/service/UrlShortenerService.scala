package com.aparenton.tiny.service

import java.net.URL

import com.aparenton.tiny.persistence.redis.RedisUrlShortenerDao
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object UrlShortenerService extends UrlShortenerService with LazyLogging {
  val urlShortenerDao = RedisUrlShortenerDao
  val counterService = CounterService

  override def create(url: URL): Future[String] = for {
    id <- counterService.next
    tiny = java.lang.Long.toString(id, 32)
    _ <- urlShortenerDao.create(tiny, url)
  } yield tiny

  override def get(id: String): Future[Option[String]] = urlShortenerDao.get(id)
}

trait UrlShortenerService {
  def create(url: URL): Future[String]

  def get(id: String): Future[Option[String]]
}