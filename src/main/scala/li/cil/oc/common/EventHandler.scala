package li.cil.oc.common

import li.cil.oc.api.Network
import li.cil.oc.util.SideTracker
import li.cil.oc.compat.vanilla.tileentity.TileEntity

import scala.collection.mutable

/** Minimal event scheduling for the NeoForge port (replaces legacy FML EventHandler). */
object EventHandler {
  private val pendingServer = mutable.Buffer.empty[() => Unit]
  private val pendingClient = mutable.Buffer.empty[() => Unit]
  private val machines = mutable.Set.empty[AnyRef]

  def scheduleServer(tileEntity: TileEntity): Unit =
    if (SideTracker.isServer) scheduleServer(() => Network.joinOrCreateNetwork(tileEntity))

  def scheduleServer(f: () => Unit): Unit = pendingServer.synchronized { pendingServer += f }

  def scheduleClient(f: () => Unit): Unit = pendingClient.synchronized { pendingClient += f }

  def scheduleFMP(tileEntity: () => TileEntity): Unit =
    scheduleServer(() => Network.joinOrCreateNetwork(tileEntity()))

  def scheduleClose(machine: AnyRef): Unit = machines += machine

  def unscheduleClose(machine: AnyRef): Unit = machines -= machine

  def onServerTick(): Unit = {
    val tasks = pendingServer.synchronized {
      val copy = pendingServer.toArray
      pendingServer.clear()
      copy
    }
    tasks.foreach(_.apply())
  }

  def onClientTick(): Unit = {
    val tasks = pendingClient.synchronized {
      val copy = pendingClient.toArray
      pendingClient.clear()
      copy
    }
    tasks.foreach(_.apply())
  }

  def scheduleAE2Add(x: Any): Unit = ()
  def scheduleIC2Add(x: Any): Unit = ()
  def scheduleWirelessRedstone(x: Any): Unit = ()
  def isItTime: Boolean = false
  def onRobotStart(robot: Any): Unit = ()
  def onRobotStopped(robot: Any): Unit = ()
  def addKeyboard(keyboard: Any): Unit = ()
  def place(player: Any): Unit = ()
}
