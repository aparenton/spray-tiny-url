package com.aparenton.tiny

import com.aparenton.tiny.routing.{StatusResponse, StatusRouting}
import org.scalatest._
import spray.testkit.ScalatestRouteTest
import spray.http.StatusCodes

class StatusRoutingTest extends FunSuite with ScalatestRouteTest with StatusRouting {

  def actorRefFactory = system

  test(s"GET /status returns 200 OK") {
    Get(s"/status") ~> statusRoutes ~> check {
      assert(status == StatusCodes.OK)
    }
  }

  test(s"GET /status returns healthy response") {
    Get(s"/status") ~> statusRoutes ~> check {
      assert(responseAs[StatusResponse].status == "healthy")
    }
  }
}
