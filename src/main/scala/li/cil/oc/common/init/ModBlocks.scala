package li.cil.oc.common.init

import li.cil.oc.Constants
import li.cil.oc.common.block.{CapacitorBlock, LegacyCompatBlock, OCPlainBlock}
import li.cil.oc.common.init.ModLegacyContent.{LegacyBlockDefinition, PlainBlockDefinition}
import li.cil.oc.util.RegistryIds
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.registries.DeferredHolder

import scala.collection.mutable

object ModBlocks {
  private val legacyHolders = mutable.LinkedHashMap.empty[String, DeferredHolder[Block, LegacyCompatBlock]]
  private val plainHolders = mutable.LinkedHashMap.empty[String, DeferredHolder[Block, OCPlainBlock]]

  val CAPACITOR: DeferredHolder[Block, CapacitorBlock] =
    ModRegistries.BLOCKS.register(RegistryIds.normalize(Constants.BlockName.Capacitor), () => new CapacitorBlock())

  ModLegacyContent.legacyBlocks.foreach { defn =>
    legacyHolders += defn.id -> ModRegistries.BLOCKS.register(
      RegistryIds.normalize(defn.id),
      () => new LegacyCompatBlock(defn.id, defn.properties)
    )
  }

  ModLegacyContent.plainBlocks.foreach { defn =>
    plainHolders += defn.id -> ModRegistries.BLOCKS.register(
      RegistryIds.normalize(defn.id),
      () => new OCPlainBlock(defn.properties)
    )
  }

  def legacy(id: String): DeferredHolder[Block, LegacyCompatBlock] = legacyHolders(id)

  def plain(id: String): DeferredHolder[Block, OCPlainBlock] = plainHolders(id)

  /** @deprecated use [[legacy]] */
  def CABLE: DeferredHolder[Block, LegacyCompatBlock] = legacy(Constants.BlockName.Cable)

  def allBlockEntries: Seq[(String, DeferredHolder[Block, ? <: Block])] =
    (Constants.BlockName.Capacitor -> CAPACITOR) +: (legacyHolders.toSeq ++ plainHolders.toSeq)

  def allBlockHolders: Iterable[DeferredHolder[Block, ? <: Block]] =
    allBlockEntries.map(_._2)

  def register(): Unit = ()

  def byName(name: String): Option[Block] = name match {
    case Constants.BlockName.Capacitor => Some(CAPACITOR.get())
    case id if legacyHolders.contains(id) => Some(legacyHolders(id).get())
    case id if plainHolders.contains(id) => Some(plainHolders(id).get())
    case _ => None
  }
}
