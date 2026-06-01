package li.cil.oc.util

import li.cil.oc.compat.ItemStackCompat.Ops
import net.minecraft.world.item.ItemStack
import li.cil.oc.compat.vanilla.nbt.NBTTagCompound

/**
  * @author asie, Vexatos
  */
object ItemColorizer {
  /**
    * Return whether the specified armor ItemStack has a color.
    */
  def hasColor(stack: ItemStack): Boolean = stack.hasTagCompound && stack.getTagCompound.hasKey("display") && stack.getTagCompound.getCompoundTag("display").hasKey("color")

  /**
    * Return the color for the specified armor ItemStack.
    */
  def getColor(stack: ItemStack): Int = {
    val tag = new Ops(stack).getTagCompound
    if (tag != null) {
      val displayTag = tag.getCompoundTag("display")
      if (displayTag == null) -1 else if (displayTag.hasKey("color")) displayTag.getInteger("color") else -1
    }
    else -1
  }

  def removeColor(stack: ItemStack): Unit = {
    val tag = new Ops(stack).getTagCompound
    if (tag != null) {
      val displayTag = tag.getCompoundTag("display")
      if (displayTag.hasKey("color")) displayTag.removeTag("color")
    }
  }

  def setColor(stack: ItemStack, color: Int): Unit = {
    val ops = new Ops(stack)
    var tag = ops.getTagCompound
    if (tag == null) {
      tag = new NBTTagCompound
      ops.setTagCompound(tag)
    }
    val displayTag = tag.getCompoundTag("display")
    if (!tag.hasKey("display")) {
      tag.setTag("display", displayTag)
    }
    displayTag.setInteger("color", color)
  }
}
