package com.example

import akka.actor._
import akka.util.Timeout
import akka.pattern.ask
import java.text.SimpleDateFormat
import java.util.Date
import scala.concurrent.duration._
import spray.routing.SimpleRoutingApp

/**
 * Lightweight REST service using Spray, and non-blocking!
 */
object Main extends SimpleRoutingApp with App {

  implicit val system = ActorSystem("my-system")
  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(1 seconds)
  val pingActor = system.actorOf(PingActor.props)

  startServer(interface = "localhost", port = 8080) {
    pathPrefix("hello") {
      pathEnd {
        get {
          complete("Got a get")
        } ~
        post {
          complete("Got a post")
        } ~
        put {
          complete("Got a put")
        } ~
        delete {
          complete("Got a delete")
        }
      } ~
      path(Segment) { name =>
        complete(s"Hello: $name")
      }
    } ~
    path("ping") {
      get {
        complete {
          (pingActor ? "ping").mapTo[String]
        }
      }
    }
  }
}

object PingActor {
  def props = Props(new PingActor)
}

class PingActor extends Actor {
  val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
  override def receive: Receive = {
    case _ =>
      sender ! s"pong ${sdf.format(new Date)}"
  }
}

