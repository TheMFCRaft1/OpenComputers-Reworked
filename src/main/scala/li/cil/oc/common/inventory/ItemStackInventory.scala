package li.cil.oc.common.inventory

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

trait ItemStackInventory extends Inventory {
  // The item stack that provides the inventory.
  def container: ItemStack

  private lazy val inventory = Array.fill[Option[ItemStack]](getSizeInventory)(None)

  override def items = inventory

  // Initialize the list automatically if we have a container.
  if (container != null) {
    reinitialize()
  }

  // Load items from tag.
  def reinitialize(): Unit = {
    for (i <- items.indices) {
      updateItems(i, null)
    }
    if (!container.hasTagCompound) {
      container.setTagCompound(new NBTTagCompound())
    }
    load(container.getTagCompound)
  }

  // Write items back to tag.
  override def markDirty(): Unit = {
    if (!container.hasTagCompound) {
      container.setTagCompound(new NBTTagCompound())
    }
    save(container.getTagCompound)
  }
}
