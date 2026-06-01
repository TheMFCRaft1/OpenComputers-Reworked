package li.cil.oc.compat

import net.minecraft.core.component.DataComponents
import net.minecraft.core.HolderLookup
import li.cil.oc.compat.vanilla.nbt.NBTTagCompound
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData

object ItemStackCompat {
  implicit class Ops(val stack: ItemStack) {
    def stackSize: Int = stack.getCount

    def stackSize_=(count: Int): Unit = stack.setCount(count)

    def hasTagCompound: Boolean = stack.has(DataComponents.CUSTOM_DATA)

    def getTagCompound: NBTTagCompound =
      if (hasTagCompound) new NBTTagCompound(stack.get(DataComponents.CUSTOM_DATA).copyTag())
      else null

    def setTagCompound(tag: NBTTagCompound): Unit =
      if (tag == null) stack.remove(DataComponents.CUSTOM_DATA)
      else stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag.unwrap()))

    def writeToNBT(tag: NBTTagCompound)(implicit registries: HolderLookup.Provider): Unit =
      stack.save(registries, tag.unwrap())

    def readFromNBT(tag: NBTTagCompound)(implicit registries: HolderLookup.Provider): Unit =
      ItemStack.parse(registries, tag.unwrap()).ifPresent { parsed =>
        stack.setCount(parsed.getCount)
        if (parsed.has(DataComponents.CUSTOM_DATA)) {
          stack.set(DataComponents.CUSTOM_DATA, parsed.get(DataComponents.CUSTOM_DATA))
        } else {
          stack.remove(DataComponents.CUSTOM_DATA)
        }
      }
  }
}
