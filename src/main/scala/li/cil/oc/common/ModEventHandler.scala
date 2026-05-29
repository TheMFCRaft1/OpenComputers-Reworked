package li.cil.oc.common

import li.cil.oc.OpenComputers
import li.cil.oc.Settings
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.server.ServerStartingEvent
import net.neoforged.neoforge.event.server.ServerStoppingEvent

/** Game-bus event handlers (replaces FMLServerStartingEvent / FMLServerStoppedEvent on legacy Proxy). */
@EventBusSubscriber(modid = OpenComputers.ID, bus = EventBusSubscriber.Bus.GAME)
object ModEventHandler {
  @SubscribeEvent
  def onServerStarting(event: ServerStartingEvent): Unit = {
    // TODO Phase 3: CommandHandler.register(event.getServer)
    // TODO Phase 3: ThreadPoolFactory.safePools.foreach(_.newThreadPool())
    logInternetFilteringWarnings(event.getServer.isDedicatedServer)
  }

  @SubscribeEvent
  def onServerStopping(event: ServerStoppingEvent): Unit = {
    // TODO Phase 3: ThreadPoolFactory.safePools.foreach(_.waitForCompletion())
  }

  private def logInternetFilteringWarnings(isDedicated: Boolean): Unit = {
    if (!Settings.get.internetAccessConfigured()) return

    if (Settings.get.internetFilteringRulesInvalid()) {
      OpenComputers.log.warn("####################################################")
      OpenComputers.log.warn("#  Could not parse Internet Card filtering rules!  #")
      OpenComputers.log.warn("#  Review config/OpenComputers.cfg => filteringRules #")
      OpenComputers.log.warn("#  Internet access has been automatically disabled.  #")
      OpenComputers.log.warn("####################################################")
    } else if (!Settings.get.internetFilteringRulesObserved && isDedicated) {
      OpenComputers.log.warn("####################################################")
      OpenComputers.log.warn("#  Dedicated server detected — review Internet Card #")
      OpenComputers.log.warn("#  filtering rules in config/OpenComputers.cfg.       #")
      OpenComputers.log.warn("####################################################")
    } else {
      OpenComputers.log.info(
        "Applied {} Internet Card filtering rules.",
        Integer.valueOf(Settings.get.internetFilteringRules.length)
      )
    }
  }
}
