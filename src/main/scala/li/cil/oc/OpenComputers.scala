package li.cil.oc

import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/** NeoForge 1.21.1 entry point. Legacy init logic lives in Proxy and will be wired in later phases. */
@Mod(OpenComputers.ID)
class OpenComputers(modEventBus: IEventBus) {
  OpenComputers.modEventBus = modEventBus
  OpenComputers.logger = LogManager.getLogger(OpenComputers.Name)

  modEventBus.addListener(OpenComputers.onCommonSetup(_))
  modEventBus.addListener(OpenComputers.onLoadComplete(_))
}

object OpenComputers {
  /** Lowercase mod id required by NeoForge; was "OpenComputers" on Forge 1.7.10. */
  final val ID = "opencomputers"

  final val Name = "OpenComputers"

  final val McVersion = "1.21.1-neoforge"

  /** Kept in sync with mod_version in gradle.properties. */
  final val Version = "1.8.0-snapshot"

  var logger: Logger = LogManager.getLogger(Name)

  var modEventBus: IEventBus = null

  def log: Logger = logger

  private def onCommonSetup(event: FMLCommonSetupEvent): Unit = {
    log.info("OpenComputers common setup (NeoForge 1.21.1 port — migration in progress)")
    // TODO Phase 2+: migrate Proxy.preInit/init (registry, config, API bootstrap)
    event.enqueueWork(() => ())
  }

  private def onLoadComplete(event: FMLLoadCompleteEvent): Unit = {
    log.info("OpenComputers load complete (NeoForge 1.21.1 port — migration in progress)")
    // TODO Phase 2+: migrate Proxy.postInit (recipes, integrations)
  }
}
