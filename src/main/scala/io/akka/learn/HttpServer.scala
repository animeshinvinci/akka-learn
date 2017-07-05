package io.akka.learn

import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.util.Timeout
import akka.pattern.ask
import akka.io.IO
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.scaladsl.Flow
import akka.stream.FlowMaterializer

/**
  * Created by animesh on 7/2/17.
  * Created by animesh on 7/2/17.
  */

object HttpServer {
  implicit val askTimeout: Timeout = 1000.millis

  def bindServer(port: Int)(handler: (HttpRequest) => HttpResponse)(implicit system: ActorSystem, materializer: FlowMßaterializer) {
    implicit val ec = system.dispatcher
    val bindingFuture = IO(Http) ? Http.Bind(interface = "localhost", port = port)
    bindingFuture foreach {
      case Http.ServerBinding(localAddress, connectionStream) =>
        Flow(connectionStream).foreach({
          case Http.IncomingConnection(remoteAddress, requestProducer, responseConsumer) =>
            println("Accepted new connection from " + remoteAddress)
            Flow(requestProducer).map(handler).produceTo(responseConsumer)
        })
    }
  }

}
