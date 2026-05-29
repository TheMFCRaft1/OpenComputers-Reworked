package li.cil.oc.client

import li.cil.oc.OpenComputers
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent

/** Client-only mod-bus registration (replaces @SidedProxy client.Proxy). */
@EventBusSubscriber(modid = OpenComputers.ID, bus = EventBusSubscriber.Bus.MOD, value = Array(Dist.CLIENT))
object ClientModEventHandler {
  @SubscribeEvent
  def onClientSetup(event: FMLClientSetupEvent): Unit = {
    event.enqueueWork(() => {
      // TODO Phase 3+: api.API.manual, client commands, Icons, rendering, packets, GuiHandler
      OpenComputers.log.info("OpenComputers client mod-bus setup (Phase 2 bootstrap)")
    })
  }
}
