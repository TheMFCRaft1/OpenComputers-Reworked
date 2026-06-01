package li.cil.oc.common.tileentity.traits

import li.cil.oc.compat.vanilla.entity.player.EntityPlayer
import li.cil.oc.compat.vanilla.inventory.IInventory
import net.minecraft.world.item.ItemStack

// Used to get notifications from containers when a player changes a slot in
// this inventory. Normally the player causing a setInventorySlotContents is
// unavailable. Using this we gain access to the causing player, allowing for
// some player-specific logic, such as the disassembler working instantaneously
// when used by a player in creative mode.
trait PlayerInputAware extends IInventory {
  def onSetInventorySlotContents(player: EntityPlayer, slot: Int, stack: ItemStack): Unit
}
