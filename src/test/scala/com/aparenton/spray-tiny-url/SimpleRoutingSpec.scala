package com.aparenton.spray-tiny-url.routing

import org.scalatest._
import spray.http._
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest

class SimpleRoutingSpec extends FlatSpec
with ScalatestRouteTest
with HttpService
with SimpleRouting
with Matchers {
  def actorRefFactory = system  // Connect the service API to the test ActorSystem

  "Simple Routes" should "work" in {
    Get("/hello") ~> simpleRoutes ~> check {
      assert(status.intValue == 200)
    }
  }
}
