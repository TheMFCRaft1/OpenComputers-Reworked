package li.cil.oc.common

import li.cil.oc.OpenComputers
import li.cil.oc.Settings
import li.cil.oc.common.init.ModRegistries
import net.neoforged.fml.event.lifecycle.{FMLClientSetupEvent, FMLCommonSetupEvent, FMLLoadCompleteEvent}
import net.neoforged.fml.loading.FMLPaths

/** NeoForge lifecycle bootstrap — replaces legacy Proxy preInit/init/postInit wiring. */
object ModBootstrap {
  def loadConfig(): Unit = {
    val configFile = FMLPaths.CONFIGDIR.get().resolve("OpenComputers.cfg").toFile
    Settings.load(configFile)
    OpenComputers.log.debug("Loaded OpenComputers configuration from {}", configFile.getAbsolutePath)
  }

  /** Called from the mod constructor after DeferredRegister holders are registered on the mod bus. */
  def registerContent(): Unit = {
    // TODO Phase 3: migrate Blocks.init() / Items.init() to DeferredRegister suppliers on ModRegistries
    OpenComputers.log.debug(
      "DeferredRegister holders ready ({} block entries, {} item entries registered so far)",
      Integer.valueOf(ModRegistries.BLOCKS.getEntries.size()),
      Integer.valueOf(ModRegistries.ITEMS.getEntries.size())
    )
  }

  def commonSetup(event: FMLCommonSetupEvent): Unit = {
    event.enqueueWork(() => {
      // TODO Phase 3: API bootstrap (Proxy.preInit), OreDictionary → tags, Loot, Mods, Recipes
      // TODO Phase 3: ModNetworking payload handlers
      OpenComputers.log.info("OpenComputers common setup complete (Phase 2 bootstrap)")
    })
  }

  def clientSetup(event: FMLClientSetupEvent): Unit = {
    event.enqueueWork(() => {
      // TODO Phase 3+: client.Proxy.init — renderers, key bindings, GuiHandler
      OpenComputers.log.info("OpenComputers client setup complete (Phase 2 bootstrap)")
    })
  }

  def loadComplete(event: FMLLoadCompleteEvent): Unit = {
    // TODO Phase 3: driver.Registry.locked = true (Proxy.postInit)
    OpenComputers.log.info("OpenComputers load complete (Phase 2 bootstrap)")
  }
}
