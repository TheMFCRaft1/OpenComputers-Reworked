package li.cil.oc.common

import li.cil.oc.OpenComputers
import li.cil.oc.Settings
import li.cil.oc.api
import li.cil.oc.common.init.ModItems
import net.neoforged.fml.event.lifecycle.{FMLClientSetupEvent, FMLCommonSetupEvent, FMLLoadCompleteEvent}
import net.neoforged.fml.loading.FMLPaths

/** NeoForge lifecycle bootstrap — replaces legacy Proxy preInit/init/postInit wiring. */
object ModBootstrap {
  def loadConfig(): Unit = {
    val configFile = FMLPaths.CONFIGDIR.get().resolve("OpenComputers.cfg").toFile
    Settings.load(configFile)
    OpenComputers.log.debug("Loaded OpenComputers configuration from {}", configFile.getAbsolutePath)
  }

  def commonSetup(event: FMLCommonSetupEvent): Unit = {
    event.enqueueWork(() => {
      bootstrapApi()
      // TODO Phase 3+: OreDictionary → item/block tags
      // TODO Phase 3+: Loot.init(), Achievement.init(), Mods.init(), Recipes.init()
      // TODO Phase 3+: ModNetworking payload handlers
      OpenComputers.log.info("OpenComputers common setup complete (Phase 3 — Capacitor registered)")
    })
  }

  def clientSetup(event: FMLClientSetupEvent): Unit = {
    event.enqueueWork(() => {
      // TODO Phase 3+: client.Proxy.init — renderers, key bindings, GuiHandler
      OpenComputers.log.info("OpenComputers client setup complete")
    })
  }

  def loadComplete(event: FMLLoadCompleteEvent): Unit = {
    event.enqueueWork(() => {
      // TODO Phase 3+: driver.Registry.locked = true (Proxy.postInit)
      OpenComputers.log.info("OpenComputers load complete")
    })
  }

  private def bootstrapApi(): Unit = {
    OpenComputers.log.info("Initializing OpenComputers API")
    api.API.items = ModItems
    api.API.config = Settings.get.config
    api.API.isPowerEnabled = !Settings.get.ignorePower
    // TODO Phase 3+: driver.Registry, fs.FileSystem, machine.Machine, network.Network, nanomachines
    // TODO Phase 3+: Lua architecture registration once LuaStateFactory is migrated
  }
}
