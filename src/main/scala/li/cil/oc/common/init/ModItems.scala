package li.cil.oc.common.init

import li.cil.oc.Constants
import li.cil.oc.api.detail.ItemAPI
import li.cil.oc.api.detail.ItemInfo
import li.cil.oc.api.fs.FileSystem
import li.cil.oc.common.OCItem
import li.cil.oc.util.RegistryIds
import net.minecraft.world.item.{BlockItem, Item, ItemStack}
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.registries.DeferredHolder

import java.util.concurrent.Callable
import scala.collection.mutable

object ModItems extends ItemAPI {
  private val descriptors = mutable.Map.empty[String, ItemInfo]
  private val names = mutable.Map.empty[AnyRef, String]

  private val blockItems = mutable.LinkedHashMap.empty[String, DeferredHolder[Item, BlockItem]]
  private val simpleItems = mutable.LinkedHashMap.empty[String, DeferredHolder[Item, OCItem]]

  ModBlocks.allBlockEntries.foreach { case (id, holder) =>
    blockItems += id -> registerBlockItem(id, holder)
  }

  ModLegacyItems.allItemIds.foreach { id =>
    if (!blockItems.contains(id)) {
      simpleItems += id -> registerSimpleItem(id)
    }
  }

  def CAPACITOR: DeferredHolder[Item, BlockItem] = blockItems(Constants.BlockName.Capacitor)
  def CABLE: DeferredHolder[Item, BlockItem] = blockItems(Constants.BlockName.Cable)

  def register(): Unit = ()

  def bindNames(): Unit = {
    blockItems.foreach { case (id, holder) =>
      names += holder.get() -> id
      ModBlocks.byName(id).foreach(block => names += block -> id)
    }
    simpleItems.foreach { case (id, holder) =>
      names += holder.get() -> id
    }
  }

  def allItems: Iterable[DeferredHolder[Item, ? <: Item]] =
    blockItems.values ++ simpleItems.values

  override def get(name: String): ItemInfo = descriptors.get(name).orNull

  override def get(stack: ItemStack): ItemInfo =
    if (stack == null || stack.isEmpty) null
    else names.get(stack.getItem) match {
      case Some(name) => get(name)
      case None => null
    }

  override def registerFloppy(name: String, color: Int, factory: Callable[FileSystem]): ItemStack = null

  override def registerFloppy(
    name: String,
    color: Int,
    factory: Callable[FileSystem],
    doRecipeCycling: Boolean
  ): ItemStack = null

  override def registerEEPROM(
    name: String,
    code: Array[Byte],
    data: Array[Byte],
    readonly: Boolean
  ): ItemStack = null

  private def registerBlockItem(id: String, blockHolder: DeferredHolder[Block, ? <: Block]): DeferredHolder[Item, BlockItem] = {
    val registryId = RegistryIds.normalize(id)
    val deferredItem = ModRegistries.ITEMS.register(registryId, () => new BlockItem(blockHolder.get(), new Item.Properties()))
    descriptors += id -> new ItemInfo {
      override def name: String = id
      override def block: Block = blockHolder.get()
      override def item: Item = deferredItem.get()
      override def createItemStack(size: Int): ItemStack = new ItemStack(deferredItem.get(), size)
    }
    deferredItem
  }

  private def registerSimpleItem(id: String): DeferredHolder[Item, OCItem] = {
    val registryId = RegistryIds.normalize(id)
    val deferredItem = ModRegistries.ITEMS.register(registryId, () => new OCItem(new Item.Properties()))
    descriptors += id -> new ItemInfo {
      override def name: String = id
      override def block: Block = null
      override def item: Item = deferredItem.get()
      override def createItemStack(size: Int): ItemStack = new ItemStack(deferredItem.get(), size)
    }
    deferredItem
  }
}
