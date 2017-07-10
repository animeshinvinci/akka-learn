package io.akka.learn


import akka.actor.{Actor, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.duration._
import scala.io.StdIn
/**
  * Created by animesh on 7/10/17.
  */
object AskActorPerRequest extends App {
  object RequestHandler {
    case object Handle
    case class Result(data: String)
  }
  class RequestHandler extends Actor {
    import RequestHandler._
    def receive = {
      case Handle =>
        sender ! Result("ok")
        context.stop(self)
    }
  }

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  import akka.pattern.ask
  import system.dispatcher // ask needs a dispatcher

  val route =
    pathEndOrSingleSlash {
      get {
        implicit val askTimeout: Timeout = 3.seconds // and a timeout
        val actor = system.actorOf(Props[RequestHandler])
        onSuccess((actor ? RequestHandler.Handle).mapTo[RequestHandler.Result]) { result =>
          complete(result.data)
        }
      }
    }

  Http().bindAndHandle(route, "localhost", 8080)

  println("ENTER to terminate")
  StdIn.readLine()
  system.terminate()
}
