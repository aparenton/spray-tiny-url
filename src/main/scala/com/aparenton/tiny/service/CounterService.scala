package com.aparenton.tiny.service

import com.aparenton.tiny.persistence.redis.CounterDao

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object CounterService {
  val InitialValue = 10000000L; // (ten million)

  val counterDao = CounterDao

  def next: Future[Long] = {
    for {
      currentString <- counterDao.get
      current = currentString match {
        case Some(value: String) => value.toLong
        case None => InitialValue
      }
      nextValue = current + 1
      _ <- counterDao.save(nextValue.toString)
    } yield nextValue
  }
}