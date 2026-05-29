package li.cil.oc.common.init

import li.cil.oc.Constants
import li.cil.oc.api.detail.ItemAPI
import li.cil.oc.api.detail.ItemInfo
import net.minecraft.world.item.{BlockItem, Item, ItemStack}
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.registries.{DeferredBlock, DeferredItem}

import scala.collection.mutable

/** NeoForge item registration — replaces imperative `Items.init()` / `GameRegistry.registerItem`. */
object ModItems extends ItemAPI {
  private val descriptors = mutable.Map.empty[String, ItemInfo]
  private val names = mutable.Map.empty[AnyRef, String]

  val CAPACITOR: DeferredItem[BlockItem] =
    registerBlockItem(Constants.BlockName.Capacitor, ModBlocks.CAPACITOR)

  // TODO Phase 3+: migrate remaining items from Items.init()

  def register(): Unit = ()

  override def get(name: String): ItemInfo = descriptors.get(name).orNull

  override def get(stack: ItemStack): ItemInfo =
    if (stack == null || stack.isEmpty) null
    else names.get(stack.getItem) match {
      case Some(name) => get(name)
      case None => null
    }

  private def registerBlockItem(id: String, block: DeferredBlock[? <: Block]): DeferredItem[BlockItem] = {
    val item = ModRegistries.ITEMS.registerSimpleBlockItem(id, block)
    descriptors += id -> new ItemInfo {
      override def name: String = id
      override def block: Block = block.get()
      override def item: Item = item.get()
      override def createItemStack(size: Int): ItemStack = new ItemStack(item.get(), size)
    }
    names += item.get() -> id
    names += block.get() -> id
    item
  }
}
