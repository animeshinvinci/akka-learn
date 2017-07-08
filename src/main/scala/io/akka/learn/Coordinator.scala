package io.akka.learn

/**
  * Created by animesh on 7/8/17.
  */
import akka.actor._
import io.akka.learn.Worker._

object Coordinator {
  case class StartMessage(body: String)
  case class WorkerResponseMessage(body: String)
}

class Coordinator extends Actor with ActorLogging {
  import Coordinator._

  def receive = {
    case StartMessage(body) => {
      log.info("Coordinator actor received start message: " + body)
      val workerActorRef = context.actorOf(Props[Worker], "workerActorRef")
      workerActorRef ! WorkerMessage("Greetings from the coordinator: " + body)
    }
    case WorkerResponseMessage(body) =>
      log.info("Coordinator actor received response from worker: " + body)
  }
}