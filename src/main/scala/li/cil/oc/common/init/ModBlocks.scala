package li.cil.oc.common.init

import li.cil.oc.Constants
import li.cil.oc.common.block.CapacitorBlock
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.registries.DeferredBlock

object ModBlocks {
  val CAPACITOR: DeferredBlock[CapacitorBlock] =
    ModRegistries.BLOCKS.register(Constants.BlockName.Capacitor, () => new CapacitorBlock())

  // TODO Phase 3+: migrate remaining blocks from Blocks.init() / GameRegistry

  def register(): Unit = {
    // DeferredBlock entries are registered when accessed above.
    ()
  }

  /** Lookup helper for migration from legacy descriptor maps. */
  def byName(name: String): Option[Block] = name match {
    case Constants.BlockName.Capacitor => Some(CAPACITOR.get())
    case _ => None
  }
}
