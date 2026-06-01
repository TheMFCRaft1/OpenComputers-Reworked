package li.cil.oc.common.block

import li.cil.oc.common.tileentity
import li.cil.oc.compat.vanilla.world.World

class CarpetedCapacitor extends Capacitor {
  override def createTileEntity(world: World, metadata: Int) = new tileentity.CarpetedCapacitor()

  override protected def customTextures = Array(
    None,
    Some("CarpetCapacitorTop"),
    Some("CapacitorSide"),
    Some("CapacitorSide"),
    Some("CapacitorSide"),
    Some("CapacitorSide")
  )
}
