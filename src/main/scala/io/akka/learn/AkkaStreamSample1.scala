package io.akka.learn

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

/**
  * Created by animesh on 7/11/17.
  */
object AkkaStreamSample1 extends App {
  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()

  val source = Source(0 to 20000000)

  val flow = Flow[Int].map(_.toString())

  val sink = Sink.foreach[String](println(_))

  val runnable = source.via(flow).to(sink)

  runnable.run()


}
