package li.cil.oc.common.block

import com.mojang.serialization.MapCodec
import li.cil.oc.common.block.base.OCBlockProperties
import li.cil.oc.common.blockentity.LegacyStubBlockEntity
import li.cil.oc.common.init.{ModBlockEntities, ModLegacyContent}
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.{BlockEntity, BlockEntityTicker, BlockEntityType}
import net.minecraft.world.level.block.{BaseEntityBlock, Block}
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties

/** NeoForge block shell for legacy OpenComputers blocks (stub tile entity until full migration). */
class LegacyCompatBlock(val legacyId: String, props: Properties) extends BaseEntityBlock(props) {

  def this(legacyId: String) =
    this(legacyId, ModLegacyContent.legacyById(legacyId).fold(OCBlockProperties.machine())(_.properties))

  override protected def codec: MapCodec[LegacyCompatBlock] =
    BlockBehaviour.simpleCodec(props => new LegacyCompatBlock(legacyId, props))

  override def newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity =
    new LegacyStubBlockEntity(
      ModBlockEntities.legacyType(legacyId).get(),
      pos,
      state,
      legacyId
    )

  override def getTicker[T <: BlockEntity](
    level: Level,
    state: BlockState,
    blockEntityType: BlockEntityType[T]
  ): BlockEntityTicker[T] = null
}
