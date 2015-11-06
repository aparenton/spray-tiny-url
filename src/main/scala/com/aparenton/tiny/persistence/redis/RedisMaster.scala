package com.aparenton.tiny.persistence.redis

import com.aparenton.tiny.util.Configuration._
import scredis.Redis

object RedisMaster {
  val redis = Redis(host = redisHost, port = redisPort, database = redisDb)
}

