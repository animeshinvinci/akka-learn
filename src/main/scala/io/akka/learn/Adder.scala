package io.akka.learn

/**
  * Created by animesh on 6/28/17.
  */
import akka.actor._
import akka.event.LoggingReceive

object Adder {
  sealed trait Command
  case object Add extends Command
  case object Subtract extends Command
  case object GetValue extends Command
  def props = Props[Adder]
}

class Adder extends Actor {
  import Adder._
  def receive: Receive = amount(0)

  def amount(value: Long): Receive = LoggingReceive {
    case Add      ⇒ context.become(amount(value + 1))
    case Subtract ⇒ context.become(amount(value - 1))
    case GetValue ⇒ sender() ! value
  }
}