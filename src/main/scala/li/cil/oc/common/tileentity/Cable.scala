package li.cil.oc.common.tileentity

import li.cil.oc.api
import li.cil.oc.api.network.Visibility
import li.cil.oc.common
import li.cil.oc.util.Color
import li.cil.oc.util.ItemColorizer
import li.cil.oc.compat.vanilla.item.Item as LegacyItem
import net.minecraft.world.item.ItemStack

class Cable extends traits.Environment with traits.NotAnalyzable with traits.ImmibisMicroblock with traits.Colored {
  /** Full OC node wiring is restored when {@code API.network} is migrated. */
  val node: api.network.Node = null

  color = Color.LightGray

  def createItemStack() = {
    val stack = new ItemStack(LegacyItem.getItemFromBlock(getBlockType))
    if (color != Color.LightGray) {
      ItemColorizer.setColor(stack, color)
    }
    stack
  }

  def fromItemStack(stack: ItemStack): Unit = {
    if (ItemColorizer.hasColor(stack)) {
      color = ItemColorizer.getColor(stack)
    }
  }

  override def consumesDye = true

  override protected def onColorChanged(): Unit = {
    super.onColorChanged()
    if (world != null && isServer) {
      api.Network.joinOrCreateNetwork(this)
    }
  }

  canUpdate = false
}
