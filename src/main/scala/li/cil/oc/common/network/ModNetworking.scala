package li.cil.oc.common.network

import li.cil.oc.OpenComputers
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent

/** NeoForge CustomPacketPayload registration (replaces FML Event Channel / SimpleNetworkWrapper). */
@EventBusSubscriber(modid = OpenComputers.ID, bus = EventBusSubscriber.Bus.MOD)
object ModNetworking {
  @SubscribeEvent
  def registerPayloads(event: RegisterPayloadHandlersEvent): Unit = {
    // TODO Phase 3: register all packet types as CustomPacketPayload records
    // val registrar = event.registrar(OpenComputers.ID).versioned("1")
    OpenComputers.log.debug("ModNetworking payload registration stub (migration pending)")
  }
}
