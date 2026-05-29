package li.cil.oc.common.block

import li.cil.oc.common.block.base.OCBlockProperties
import li.cil.oc.common.blockentity.CapacitorBlockEntity
import li.cil.oc.common.init.ModBlockEntities
import net.minecraft.core.{BlockPos, Direction}
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.{BaseEntityBlock, Block}
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.{BlockBehaviour, BlockState}
import net.minecraft.world.level.block.state.BlockState.Properties

/** NeoForge 1.21.1 capacitor block (Phase 3 proof-of-concept). */
class CapacitorBlock(props: Properties) extends BaseEntityBlock(props) {
  def this() = this(OCBlockProperties.metal().lightLevel(_ => 5))

  override def newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity =
    new CapacitorBlockEntity(ModBlockEntities.CAPACITOR.get(), pos, state)

  override def isRandomlyTicking(state: BlockState): Boolean = true

  override def randomTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource): Unit =
    level.updateNeighborsAt(pos, this)

  override def neighborChanged(
    state: BlockState,
    level: Level,
    pos: BlockPos,
    block: Block,
    fromPos: BlockPos,
    isMoving: Boolean
  ): Unit = {
    super.neighborChanged(state, level, pos, block, fromPos, isMoving)
    if (!level.isClientSide) {
      level.getBlockEntity(pos) match {
        case capacitor: CapacitorBlockEntity => capacitor.recomputeCapacity()
        case _ =>
      }
    }
  }

  override def hasAnalogOutputSignal(state: BlockState): Boolean = true

  override def getAnalogOutputSignal(
    state: BlockState,
    level: Level,
    pos: BlockPos,
    direction: Direction
  ): Int =
    level.getBlockEntity(pos) match {
      case capacitor: CapacitorBlockEntity => capacitor.comparatorOutput
      case _ => 0
    }
}
