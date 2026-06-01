package li.cil.oc.util

import li.cil.oc.compat.vanilla.nbt.{NBTTagCompound, NBTTagList}

/** Minimal NBT helpers for legacy tile entities during port. */
object ExtendedNBT {
  implicit class RichNBTTagCompound(val nbt: NBTTagCompound) {
    def setNewCompoundTag(key: String, value: => NBTTagCompound): Unit =
      nbt.setTag(key, value)

    def setNewTagList(key: String, value: => NBTTagList): Unit =
      nbt.setTag(key, value)
  }
}
