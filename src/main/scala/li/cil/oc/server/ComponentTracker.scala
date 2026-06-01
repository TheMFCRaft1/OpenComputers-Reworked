package li.cil.oc.server

import li.cil.oc.common
import li.cil.oc.compat.vanilla.world.World

object ComponentTracker extends common.ComponentTracker {
  override protected def clear(world: World) = if (!world.isRemote) super.clear(world)
}
