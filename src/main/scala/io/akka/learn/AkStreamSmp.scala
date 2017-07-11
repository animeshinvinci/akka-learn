package io.akka.learn

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source

/**
  * Created by animesh on 7/11/17.
  */
object AkStreamSmp extends App {
  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()

  Source(0 to 20000000)
    .map(_.toString)
    .runForeach(println)


}
