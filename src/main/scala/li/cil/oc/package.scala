package li.cil

import li.cil.oc.compat.ItemStackCompat.Ops

package object oc {
  implicit def toItemStackOps(stack: net.minecraft.world.item.ItemStack): Ops =
    new Ops(stack)
}
