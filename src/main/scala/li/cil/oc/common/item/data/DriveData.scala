package li.cil.oc.common.item.data

import li.cil.oc.Settings
import net.minecraft.item.ItemStack
import li.cil.oc.compat.vanilla.nbt.NBTTagCompound
import li.cil.oc.server.fs
import li.cil.oc.compat.vanilla.entity.player.EntityPlayer

class DriveData extends ItemData(null) {
  def this(stack: ItemStack): Unit = {
    this()
    load(stack)
  }

  var isUnmanaged = false
  var lockInfo: String = ""

  def isLocked: Boolean = {
    lockInfo != null && !lockInfo.isEmpty
  }

  private val UnmanagedKey = Settings.namespace + "unmanaged"
  private val LockKey = Settings.namespace + "lock"

  override def load(nbt: NBTTagCompound): Unit = {
    isUnmanaged = nbt.getBoolean(UnmanagedKey)
    lockInfo = if (nbt.hasKey(LockKey)) {
      nbt.getString(LockKey)
    } else ""
  }

  override def save(nbt: NBTTagCompound): Unit = {
    nbt.setBoolean(UnmanagedKey, isUnmanaged)
    nbt.setString(LockKey, lockInfo)
  }
}

object DriveData {
  def lock(stack: ItemStack, player: EntityPlayer): Unit = {
    val key = player.getDisplayName
    val data = new DriveData(stack)
    if (!data.isLocked) {
      data.lockInfo = key match {
        case name: String if name != null && name.nonEmpty => name
        case _ => "notch" // meaning: "unknown"
      }
      data.save(stack)
    }
  }

  def setUnmanaged(stack: ItemStack, unmanaged: Boolean): Unit = {
    val data = new DriveData(stack)
    if (data.isUnmanaged != unmanaged) {
      fs.FileSystem.removeAddress(stack)
      data.lockInfo = ""
    }
    data.isUnmanaged = unmanaged
    data.save(stack)
  }
}
