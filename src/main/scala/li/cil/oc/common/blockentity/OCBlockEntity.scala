package li.cil.oc.common.blockentity

import li.cil.oc.util.SideTracker
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

/** NeoForge base block entity — replaces `tileentity.traits.TileEntity` for new registrations. */
abstract class OCBlockEntity(
  beType: BlockEntityType[_],
  pos: BlockPos,
  state: BlockState
) extends BlockEntity(beType, pos, state) {

  def world: Level = getLevel

  def x: Int = getBlockPos.getX

  def y: Int = getBlockPos.getY

  def z: Int = getBlockPos.getZ

  def isServer: Boolean =
    if (world != null) !world.isClientSide else SideTracker.isServer

  def isClient: Boolean = !isServer

  override def onLoad(): Unit = {
    super.onLoad()
    if (isServer) initialize()
  }

  override def setRemoved(): Unit = {
    dispose()
    super.setRemoved()
  }

  protected def initialize(): Unit = ()

  protected def dispose(): Unit = ()

  protected def readFromNBTForServer(tag: CompoundTag): Unit = ()

  protected def writeToNBTForServer(tag: CompoundTag): Unit = ()

  protected def readFromNBTForClient(tag: CompoundTag): Unit = ()

  protected def writeToNBTForClient(tag: CompoundTag): Unit = ()

  override def loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider): Unit = {
    super.loadAdditional(tag, registries)
    if (isServer) readFromNBTForServer(tag)
  }

  override def saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider): Unit = {
    super.saveAdditional(tag, registries)
    if (isServer) writeToNBTForServer(tag)
  }

  override def getUpdatePacket: ClientboundBlockEntityDataPacket =
    ClientboundBlockEntityDataPacket.create(this)

  override def getUpdateTag(registries: HolderLookup.Provider): CompoundTag = {
    val tag = super.getUpdateTag(registries)
    writeToNBTForClient(tag)
    tag
  }

  override def handleUpdateTag(tag: CompoundTag, registries: HolderLookup.Provider): Unit = {
    super.handleUpdateTag(tag, registries)
    readFromNBTForClient(tag)
  }
}
