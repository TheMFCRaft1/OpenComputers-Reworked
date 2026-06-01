package li.cil.oc

import li.cil.oc.common.init.{ModItems, ModRegistries}
import net.minecraft.network.chat.Component
import net.minecraft.world.item.{CreativeModeTab, ItemStack}
import net.neoforged.neoforge.registries.DeferredHolder

object ModCreativeTabs {
  val TAB: DeferredHolder[CreativeModeTab, CreativeModeTab] =
    ModRegistries.CREATIVE_MODE_TABS.register(
      "main",
      () =>
        CreativeModeTab.builder()
          .title(Component.translatable("itemGroup.opencomputers"))
          .icon(() => new ItemStack(ModItems.CAPACITOR.get()))
          .displayItems((_, output) => {
            ModItems.allItems.foreach(holder => output.accept(holder.get()))
          })
          .build()
    )

  def register(): Unit = ()

  /** Call after registries are bound (common setup). */
  def bindApi(): Unit = {
    api.CreativeTab.instance = TAB.get()
  }
}
