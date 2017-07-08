package io.akka.learn

/**
  * Created by animesh on 7/8/17.
  */
import akka.actor._
import io.akka.learn.Coordinator._

object Worker {
  case class WorkerMessage(body: String)
}

class Worker extends Actor with ActorLogging {
  import Worker._

  def receive = {
    case WorkerMessage(body) =>
      log.info("Worker actor received the message: " + body)
      sender ! WorkerResponseMessage("Item processed successfully")
  }
}