package li.cil.oc.common.init

import li.cil.oc.Constants
import li.cil.oc.common.blockentity.CapacitorBlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.neoforge.registries.DeferredHolder

object ModBlockEntities {
  val CAPACITOR: DeferredHolder[BlockEntityType[_], BlockEntityType[CapacitorBlockEntity]] =
    ModRegistries.BLOCK_ENTITY_TYPES.register(
      Constants.BlockName.Capacitor,
      () =>
        BlockEntityType.Builder
          .of(
            (pos, state) => new CapacitorBlockEntity(CAPACITOR.get(), pos, state),
            ModBlocks.CAPACITOR.get()
          )
          .build(null)
    )

  def register(): Unit = {
    // DeferredHolder entries are registered when accessed above.
    ()
  }
}
