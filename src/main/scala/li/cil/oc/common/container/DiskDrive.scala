package li.cil.oc.common.container

import li.cil.oc.common.Slot
import net.minecraft.entity.player.InventoryPlayer
import li.cil.oc.compat.vanilla.inventory.IInventory

class DiskDrive(playerInventory: InventoryPlayer, drive: IInventory) extends Player(playerInventory, drive) {
  addSlotToContainer(80, 35, Slot.Floppy)
  addPlayerInventorySlots(8, 84)
}
