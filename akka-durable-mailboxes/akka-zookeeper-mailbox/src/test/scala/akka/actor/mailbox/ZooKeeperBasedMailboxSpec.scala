package akka.actor.mailbox

import akka.actor.{ Actor, LocalActorRef }
import akka.cluster.zookeeper._
import org.I0Itec.zkclient._
import akka.dispatch.MessageDispatcher
import akka.dispatch.CustomMailboxType
import akka.actor.ActorRef

@org.junit.runner.RunWith(classOf[org.scalatest.junit.JUnitRunner])
class ZooKeeperBasedMailboxSpec extends DurableMailboxSpec("ZooKeeper",
  new CustomMailboxType("akka.actor.mailbox.ZooKeeperBasedMailbox")) {

  val dataPath = "_akka_cluster/data"
  val logPath = "_akka_cluster/log"

  var zkServer: ZkServer = _

  override def atStartup() {
    zkServer = AkkaZooKeeper.startLocalServer(dataPath, logPath)
    super.atStartup()
  }

  override def atTermination() {
    zkServer.shutdown()
    super.atTermination()
  }
}
