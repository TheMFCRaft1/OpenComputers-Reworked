package li.cil.oc.common.init

import li.cil.oc.OpenComputers
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

/** Central DeferredRegister holders for NeoForge 1.21.1. Content registration migrates here from GameRegistry. */
object ModRegistries {
  val BLOCKS: DeferredRegister[Block] =
    DeferredRegister.create(Registries.BLOCK, OpenComputers.ID)

  val ITEMS: DeferredRegister[Item] =
    DeferredRegister.create(Registries.ITEM, OpenComputers.ID)

  val BLOCK_ENTITY_TYPES: DeferredRegister[BlockEntityType[_]] =
    DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, OpenComputers.ID)

  val ENTITY_TYPES: DeferredRegister[EntityType[_]] =
    DeferredRegister.create(Registries.ENTITY_TYPE, OpenComputers.ID)

  val MENU_TYPES: DeferredRegister[MenuType[_]] =
    DeferredRegister.create(Registries.MENU, OpenComputers.ID)

  val CREATIVE_MODE_TABS: DeferredRegister[CreativeModeTab] =
    DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OpenComputers.ID)

  def register(modEventBus: IEventBus): Unit = {
    BLOCKS.register(modEventBus)
    ITEMS.register(modEventBus)
    BLOCK_ENTITY_TYPES.register(modEventBus)
    ENTITY_TYPES.register(modEventBus)
    MENU_TYPES.register(modEventBus)
    CREATIVE_MODE_TABS.register(modEventBus)
  }
}
