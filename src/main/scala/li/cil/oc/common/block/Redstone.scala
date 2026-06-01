package li.cil.oc.common.block

import java.util

import li.cil.oc.common.tileentity
import li.cil.oc.integration.Mods
import li.cil.oc.util.Tooltip
import li.cil.oc.compat.vanilla.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import li.cil.oc.compat.vanilla.world.World

class Redstone extends RedstoneAware {
  override protected def customTextures = Array(
    Some("RedstoneBottom"),
    Some("RedstoneTop"),
    Some("RedstoneNorth"),
    Some("RedstoneSouth"),
    Some("RedstoneWest"),
    Some("RedstoneEast")
  )

  // ----------------------------------------------------------------------- //

  override protected def tooltipTail(metadata: Int, stack: ItemStack, player: EntityPlayer, tooltip: util.List[String], advanced: Boolean): Unit = {
    super.tooltipTail(metadata, stack, player, tooltip, advanced)
    if (Mods.ProjectRedTransmission.isAvailable) {
      tooltip.addAll(Tooltip.get("RedstoneCard.ProjectRed"))
    }
    if (Mods.RedLogic.isAvailable) {
      tooltip.addAll(Tooltip.get("RedstoneCard.RedLogic"))
    }
    if (Mods.MineFactoryReloaded.isAvailable) {
      tooltip.addAll(Tooltip.get("RedstoneCard.RedNet"))
    }
  }

  // ----------------------------------------------------------------------- //

  override def createTileEntity(world: World, metadata: Int) = new tileentity.Redstone()
}
