package li.cil.oc.common.tileentity

import li.cil.oc.api.network.Node
import li.cil.oc.server.component
import li.cil.oc.compat.vanilla.nbt.NBTTagCompound

class MotionSensor extends traits.Environment {
  val motionSensor = new component.MotionSensor(this)

  def node: Node = motionSensor.node

  override def canUpdate = isServer

  override def updateEntity(): Unit = {
    super.updateEntity()
    motionSensor.update()
  }

  override def readFromNBTForServer(nbt: NBTTagCompound): Unit = {
    super.readFromNBTForServer(nbt)
    motionSensor.load(nbt)
  }

  override def writeToNBTForServer(nbt: NBTTagCompound): Unit = {
    super.writeToNBTForServer(nbt)
    motionSensor.save(nbt)
  }
}
