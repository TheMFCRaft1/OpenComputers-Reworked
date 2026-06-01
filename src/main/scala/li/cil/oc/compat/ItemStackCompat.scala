package li.cil.oc.compat

import net.minecraft.core.HolderLookup
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

object ItemStackCompat {
  implicit class Ops(val stack: ItemStack) {
    def stackSize: Int = stack.getCount

    def stackSize_=(count: Int): Unit = stack.setCount(count)

    def hasTagCompound: Boolean = stack.hasTag

    def getTagCompound: NBTTagCompound =
      if (stack.hasTag) new NBTTagCompound(stack.getTag) else null

    def setTagCompound(tag: NBTTagCompound): Unit =
      stack.setTag(if (tag == null) null else tag.unwrap())

    def writeToNBT(tag: NBTTagCompound)(implicit registries: HolderLookup.Provider): Unit =
      stack.save(registries, tag.unwrap())

    def readFromNBT(tag: NBTTagCompound)(implicit registries: HolderLookup.Provider): Unit = {
      val copy = stack.copy()
      copy.load(registries, tag.unwrap())
      stack.setCount(copy.getCount)
      stack.setTag(copy.getTag)
    }
  }
}
