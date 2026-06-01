package li.cil.oc.common.init

import li.cil.oc.Constants
import li.cil.oc.common.blockentity.{CapacitorBlockEntity, LegacyStubBlockEntity}
import li.cil.oc.util.RegistryIds
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.neoforge.registries.DeferredHolder

import scala.collection.mutable

object ModBlockEntities {
  private val legacyTypes =
    mutable.LinkedHashMap.empty[String, DeferredHolder[BlockEntityType[?], BlockEntityType[LegacyStubBlockEntity]]]

  val CAPACITOR: DeferredHolder[BlockEntityType[?], BlockEntityType[CapacitorBlockEntity]] =
    ModRegistries.BLOCK_ENTITY_TYPES.register(
      RegistryIds.normalize(Constants.BlockName.Capacitor),
      () =>
        BlockEntityType.Builder
          .of(
            (pos, state) => new CapacitorBlockEntity(CAPACITOR.get(), pos, state),
            ModBlocks.CAPACITOR.get()
          )
          .build(null)
    )

  ModLegacyContent.legacyBlocks.foreach { defn =>
    legacyTypes += defn.id -> ModRegistries.BLOCK_ENTITY_TYPES.register(
      RegistryIds.normalize(defn.id),
      () =>
        BlockEntityType.Builder
          .of[LegacyStubBlockEntity](
            (pos, state) =>
              new LegacyStubBlockEntity(
                legacyTypes(defn.id).get(),
                pos,
                state,
                defn.id
              ),
            ModBlocks.legacy(defn.id).get()
          )
          .build(null)
    )
  }

  def legacyType(id: String): DeferredHolder[BlockEntityType[?], BlockEntityType[LegacyStubBlockEntity]] =
    legacyTypes(id)

  /** @deprecated use [[legacyType]] */
  def CABLE: DeferredHolder[BlockEntityType[?], BlockEntityType[LegacyStubBlockEntity]] =
    legacyType(Constants.BlockName.Cable)

  def register(): Unit = ()
}
