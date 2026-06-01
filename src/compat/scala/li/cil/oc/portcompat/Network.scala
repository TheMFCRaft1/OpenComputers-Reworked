package li.cil.oc.server.network

import li.cil.oc.api.network.Node
import li.cil.oc.compat.vanilla.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection

/** Minimal network stub until full {@code server/network} is migrated. */
object Network {
  def joinOrCreateNetwork(tileEntity: TileEntity): Unit = ()

  def getNetworkNode(tileEntity: TileEntity, side: ForgeDirection): Node = null
}
