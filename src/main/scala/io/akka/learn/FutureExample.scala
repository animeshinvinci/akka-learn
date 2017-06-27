package io.akka.learn

import akka.actor.ActorSystem
import scala.util.Failure
import scala.util.Success
/**
  * Created by animesh on 6/27/17.
  */
object FutureExample extends App{

  import scala.concurrent.{ ExecutionContext, Promise }
  val system = ActorSystem("futures-system")
  implicit val ec = system.dispatcher

  val f = Promise.successful("foo").future

  f.onComplete {
    case Success(result) => println(" :) " + result)
    case Failure(error) => println(" :( " + error)
  }

}