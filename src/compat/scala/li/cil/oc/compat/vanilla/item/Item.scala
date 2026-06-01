package li.cil.oc.compat.vanilla.item

import net.minecraft.world.level.block.Block

/** Legacy item helpers. */
object Item {
  def getItemFromBlock(block: Block): Item =
    block.asItem()

  def getItemById(id: Int): Item =
    net.minecraft.world.item.Item.byId(id)
}
