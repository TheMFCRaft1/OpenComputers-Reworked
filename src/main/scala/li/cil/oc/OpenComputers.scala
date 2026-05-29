package li.cil.oc

import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.{FMLClientSetupEvent, FMLCommonSetupEvent, FMLLoadCompleteEvent}
import net.neoforged.fml.loading.FMLEnvironment
import net.neoforged.neoforge.common.NeoForge
import li.cil.oc.common.{ModBootstrap, ModEventHandler}
import li.cil.oc.common.init.{ModRegistries, ModRegistration}
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(OpenComputers.ID)
class OpenComputers(modEventBus: IEventBus) {
  OpenComputers.logger = LogManager.getLogger(OpenComputers.Name)
  OpenComputers.modEventBus = modEventBus

  ModBootstrap.loadConfig()
  ModRegistries.register(modEventBus)
  ModRegistration.registerContent()

  NeoForge.EVENT_BUS.register(ModEventHandler)

  modEventBus.addListener(OpenComputers.onCommonSetup(_))
  modEventBus.addListener(OpenComputers.onLoadComplete(_))

  if (FMLEnvironment.dist == Dist.CLIENT) {
    modEventBus.addListener(OpenComputers.onClientSetup(_))
  }
}

object OpenComputers {
  /** Lowercase mod id required by NeoForge; was `OpenComputers` on Forge 1.7.10. */
  final val ID = "opencomputers"

  /** Historical mod id used in 1.7.10 saves, IMC, and some resource paths. */
  final val LEGACY_ID = "OpenComputers"

  final val Name = "OpenComputers"

  final val McVersion = "1.21.1-neoforge"

  /** Kept in sync with mod_version in gradle.properties. */
  final val Version = "1.8.0-snapshot"

  var logger: Logger = LogManager.getLogger(Name)

  var modEventBus: IEventBus = null

  def log: Logger = logger

  private[oc] def onCommonSetup(event: FMLCommonSetupEvent): Unit =
    ModBootstrap.commonSetup(event)

  private[oc] def onClientSetup(event: FMLClientSetupEvent): Unit =
    ModBootstrap.clientSetup(event)

  private[oc] def onLoadComplete(event: FMLLoadCompleteEvent): Unit =
    ModBootstrap.loadComplete(event)
}
