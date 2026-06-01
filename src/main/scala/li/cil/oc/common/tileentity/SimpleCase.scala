package li.cil.oc.common.tileentity

import li.cil.oc.api
import li.cil.oc.api.network.Visibility
import li.cil.oc.common.tileentity.traits

/** Minimal computer case for NeoForge port testing (no Lua machine yet). */
class SimpleCase(val tier: Int) extends traits.PowerAcceptor with traits.Environment with traits.Colored {
  val node = api.Network.newNode(this, Visibility.None).create()

  override def energyThroughput: Double = 1000.0

  override def canUpdate = isServer
}
