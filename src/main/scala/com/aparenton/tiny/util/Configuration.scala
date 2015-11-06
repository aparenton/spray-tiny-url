package com.aparenton.tiny.util

import java.util.concurrent.TimeUnit

import com.typesafe.config.ConfigFactory

object Configuration {
  private val config = ConfigFactory.load

  // Http
  val httpHost = config.getString("spray-tiny-url.http.host")
  val httpPort = config.getInt("spray-tiny-url.http.port")

  // Redis
  val redisHost = config.getString("spray-tiny-url.redis.host")
  val redisPort = config.getInt("spray-tiny-url.redis.port")
  val redisDb   = config.getInt("spray-tiny-url.redis.database")

  val urlExpiration: Long = config.getLong("spray-tiny-url.url.expiration")
  val urlTimeUnit: TimeUnit = TimeUnit.valueOf(config.getString("spray-tiny-url.url.timeunit"))

  val secure = config.getBoolean("spray-tiny-url.secure")
}
