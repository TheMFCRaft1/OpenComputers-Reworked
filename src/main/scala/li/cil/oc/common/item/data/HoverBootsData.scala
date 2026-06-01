package li.cil.oc.common.item.data

import li.cil.oc.Constants
import li.cil.oc.Settings
import net.minecraft.item.ItemStack
import li.cil.oc.compat.vanilla.nbt.NBTTagCompound

class HoverBootsData extends ItemData(Constants.ItemName.HoverBoots) {
  def this(stack: ItemStack): Unit = {
    this()
    load(stack)
  }

  var charge = 0.0

  override def load(nbt: NBTTagCompound): Unit = {
    charge = nbt.getDouble(Settings.namespace + "charge")
  }

  override def save(nbt: NBTTagCompound): Unit = {
    nbt.setDouble(Settings.namespace + "charge", charge)
  }
}
