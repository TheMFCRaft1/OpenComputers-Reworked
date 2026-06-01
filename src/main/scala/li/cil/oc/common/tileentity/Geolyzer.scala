package li.cil.oc.common.tileentity

import li.cil.oc.server.component
import li.cil.oc.compat.vanilla.nbt.NBTTagCompound

class Geolyzer extends traits.Environment {
  val geolyzer = new component.Geolyzer(this)

  def node = geolyzer.node

  override def canUpdate = false

  override def readFromNBTForServer(nbt: NBTTagCompound): Unit = {
    super.readFromNBTForServer(nbt)
    geolyzer.load(nbt)
  }

  override def writeToNBTForServer(nbt: NBTTagCompound): Unit = {
    super.writeToNBTForServer(nbt)
    geolyzer.save(nbt)
  }
}
