package io.akka.learn

/**
  * Created by animesh on 7/5/17.
  */
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.actor.ActorLogging
import akka.actor.Actor

class SimpleClusterListener2 extends Actor with ActorLogging {

  val cluster = Cluster(context.system)

  // subscribe to cluster changes, re-subscribe when restart
  override def preStart(): Unit = {
    //#subscribe
    cluster.subscribe(self, classOf[MemberEvent], classOf[UnreachableMember])
    //#subscribe
  }
  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive = {
    case state: CurrentClusterState =>
      log.info("###Current members: {}", state.members.mkString(", "))
    case MemberUp(member) =>
      log.info("###Member is Up: {}", member.address)
    case UnreachableMember(member) =>
      log.info("###Member detected as unreachable: {}", member)
    case MemberRemoved(member, previousStatus) =>
      log.info("###Member is Removed: {} after {}",
        member.address, previousStatus)
    case "stop" =>
      cluster.down(cluster.selfAddress)
      //context.system.shutdown()
      log.info("###Member is stop! {}",cluster.selfAddress)
    case _: MemberEvent => // ignore
  }
}