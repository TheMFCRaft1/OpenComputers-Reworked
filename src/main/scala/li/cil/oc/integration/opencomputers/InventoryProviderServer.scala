package li.cil.oc.integration.opencomputers

import li.cil.oc.api.driver.InventoryProvider
import li.cil.oc.common.inventory.ServerInventory
import li.cil.oc.compat.vanilla.entity.player.EntityPlayer
import li.cil.oc.compat.vanilla.inventory.IInventory
import net.minecraft.item.ItemStack

object InventoryProviderServer extends InventoryProvider {
  override def worksWith(stack: ItemStack, player: EntityPlayer): Boolean = DriverServer.worksWith(stack)

  override def getInventory(stack: ItemStack, player: EntityPlayer): IInventory = new ServerInventory {
    override def container: ItemStack = stack
  }
}
