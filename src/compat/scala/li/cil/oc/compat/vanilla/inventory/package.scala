package li.cil.oc.compat.vanilla.inventory

import net.minecraft.world.Container
import net.minecraft.world.item.ItemStack

/** Legacy container / inventory API surface. */
trait IInventory extends Container {
  def getSizeInventory: Int = getContainerSize
  def getStackInSlot(index: Int): ItemStack = getItem(index)
  def decrStackSize(index: Int, count: Int): ItemStack = removeItem(index, count)
  def setInventorySlotContents(index: Int, stack: ItemStack): Unit = setItem(index, stack)
  def getInventoryStackLimit: Int = getMaxStackSize
  def markDirty(): Unit = setChanged()
  def isUseableByPlayer(player: li.cil.oc.compat.vanilla.entity.player.EntityPlayer): Boolean = true
  def openInventory(): Unit = ()
  def closeInventory(): Unit = ()
}
