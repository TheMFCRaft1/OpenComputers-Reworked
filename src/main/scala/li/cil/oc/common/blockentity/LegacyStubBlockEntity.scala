package li.cil.oc.common.blockentity

import li.cil.oc.compat.vanilla.tileentity.TileEntity as LegacyTileEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

/** Placeholder block entity until legacy {@code li.cil.oc.common.tileentity} classes are migrated. */
class LegacyStubBlockEntity(
  beType: BlockEntityType[?],
  pos: BlockPos,
  state: BlockState,
  val legacyId: String
) extends LegacyTileEntity(beType, pos, state)
