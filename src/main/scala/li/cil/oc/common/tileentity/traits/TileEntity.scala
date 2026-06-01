package li.cil.oc.common.tileentity.traits

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import li.cil.oc.Settings
import li.cil.oc.client.Sound
import li.cil.oc.util.BlockPosition
import li.cil.oc.util.SideTracker
import li.cil.oc.compat.vanilla.nbt.NBTTagCompound

trait TileEntity extends li.cil.oc.compat.vanilla.tileentity.TileEntity {
  def world = getWorldObj

  def x = xCoord

  def y = yCoord

  def z = zCoord

  def position = BlockPosition(x, y, z, world)

  def block = getBlockType

  def isClient = !isServer

  def isServer = if (world != null) !world.isRemote else SideTracker.isServer

  // ----------------------------------------------------------------------- //

  override def updateEntity(): Unit = {
    super.updateEntity()
    if (Settings.get.periodicallyForceLightUpdate && world != null && world.getTotalWorldTime % 40 == 0) {
      world.markBlockForUpdate(x, y, z)
    }
  }

  override def validate(): Unit = {
    super.validate()
    initialize()
  }

  override def invalidate(): Unit = {
    super.invalidate()
    dispose()
  }

  override def onChunkUnloaded(): Unit = {
    super.onChunkUnloaded()
    dispose()
  }

  protected def initialize(): Unit = {}

  def dispose(): Unit = {
    if (isClient) {
      // Note: chunk unload is handled by sound via event handler.
      Sound.stopLoop(this)
    }
  }

  // ----------------------------------------------------------------------- //

  def readFromNBTForServer(nbt: NBTTagCompound): Unit = super.readFromNBT(nbt)

  def writeToNBTForServer(nbt: NBTTagCompound): Unit = super.writeToNBT(nbt)

  @SideOnly(Side.CLIENT)
  def readFromNBTForClient(nbt: NBTTagCompound): Unit = {}

  def writeToNBTForClient(nbt: NBTTagCompound): Unit = {}

  // ----------------------------------------------------------------------- //

  override def readFromNBT(nbt: NBTTagCompound): Unit = {
    if (isServer) {
      readFromNBTForServer(nbt)
    }
  }

  override def writeToNBT(nbt: NBTTagCompound): Unit = {
    if (isServer) {
      writeToNBTForServer(nbt)
    }
  }

}
