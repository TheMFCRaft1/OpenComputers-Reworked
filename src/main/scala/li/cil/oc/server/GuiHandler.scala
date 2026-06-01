package li.cil.oc.server

import li.cil.oc.common.{GuiHandler => CommonGuiHandler}
import li.cil.oc.compat.vanilla.entity.player.EntityPlayer
import li.cil.oc.compat.vanilla.world.World

object GuiHandler extends CommonGuiHandler {
  override def getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int) = null
}
