package io.akka.learn


import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

case object AskNameMessage

/**
  * Created by animesh on 6/25/17.
  */


class TestActor extends Actor {
  def receive = {
    case AskNameMessage => // respond to the 'ask' request
      sender ! "animesh"
    case _ => println("that was unexpected")
  }
}


object AskTestApp extends App {

  // create the system and actor
  val system = ActorSystem("AskTestApp")
  val myActor = system.actorOf(Props[TestActor], name = "myActor")


  // a- this is one way to "ask" another actor for information

  implicit val timeout = Timeout(5 seconds)
  val future = myActor ? AskNameMessage
  val result = Await.result(future, timeout.duration).asInstanceOf[String]
  println(result)

  // b-  a slightly different way to ask another actor for information
  val future2: Future[String] = ask(myActor, AskNameMessage).mapTo[String]
  val result2 = Await.result(future2, 1 second)
  println(result2)

 // c- this is 3rd way of doing it
  val future3: Future[String] = (myActor ? AskNameMessage).mapTo[String]
  val result3 = Await.result(future3, 1 second)
  println(result3)


  system.shutdown
}
