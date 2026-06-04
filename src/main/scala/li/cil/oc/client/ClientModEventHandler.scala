package li.cil.oc.client

import li.cil.oc.Constants
import li.cil.oc.OpenComputers
import li.cil.oc.common.init.ModBlocks
import li.cil.oc.util.Color
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent

/** Client-only mod-bus registration (replaces @SidedProxy client.Proxy). */
@EventBusSubscriber(modid = OpenComputers.ID, bus = EventBusSubscriber.Bus.MOD, value = Array(Dist.CLIENT))
object ClientModEventHandler {
  private def caseTint(block: net.minecraft.world.level.block.Block): Int =
    if (block == ModBlocks.legacy(Constants.BlockName.CaseTier1).get()) Color.byTier(0)
    else if (block == ModBlocks.legacy(Constants.BlockName.CaseTier2).get()) Color.byTier(1)
    else if (block == ModBlocks.legacy(Constants.BlockName.CaseTier3).get()) Color.byTier(2)
    else if (block == ModBlocks.legacy(Constants.BlockName.CaseCreative).get()) Color.byTier(3)
    else 0xFFFFFF

  @SubscribeEvent
  def onClientSetup(event: FMLClientSetupEvent): Unit = {
    event.enqueueWork(() => {
      // TODO Phase 3+: api.API.manual, client commands, Icons, rendering, packets, GuiHandler
      OpenComputers.log.info("OpenComputers client mod-bus setup (Phase 2 bootstrap)")
    })
  }

  @SubscribeEvent
  def onRegisterBlockColors(event: RegisterColorHandlersEvent.Block): Unit = {
    event.register(
      (state, world, pos, tintIndex) =>
        if (tintIndex >= 0) caseTint(state.getBlock) else 0xFFFFFF,
      ModBlocks.legacy(Constants.BlockName.CaseTier1).get(),
      ModBlocks.legacy(Constants.BlockName.CaseTier2).get(),
      ModBlocks.legacy(Constants.BlockName.CaseTier3).get(),
      ModBlocks.legacy(Constants.BlockName.CaseCreative).get()
    )
  }

  @SubscribeEvent
  def onRegisterItemColors(event: RegisterColorHandlersEvent.Item): Unit = {
    event.register(
      (stack, tintIndex) =>
        if (tintIndex >= 0) caseTint(net.minecraft.world.level.block.Block.byItem(stack.getItem)) else 0xFFFFFF,
      ModBlocks.legacy(Constants.BlockName.CaseTier1).get(),
      ModBlocks.legacy(Constants.BlockName.CaseTier2).get(),
      ModBlocks.legacy(Constants.BlockName.CaseTier3).get(),
      ModBlocks.legacy(Constants.BlockName.CaseCreative).get()
    )
  }
}
