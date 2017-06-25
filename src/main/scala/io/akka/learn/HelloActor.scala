package io.akka.learn

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props


/**
  * Created by animesh on 6/25/17.
  */
class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case _       => println("huh?")
  }
}

object   Main extends App {
  val system = ActorSystem("HelloSystem")

  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "animesh hello"
}