package li.cil.oc

import li.cil.oc.common.init.{ModItems, ModRegistries}
import net.minecraft.network.chat.Component
import net.minecraft.world.item.{CreativeModeTab, ItemStack}

object ModCreativeTabs {
  val TAB: CreativeModeTab =
    ModRegistries.CREATIVE_MODE_TABS.register(
      "main",
      () =>
        CreativeModeTab.builder()
          .title(Component.translatable("itemGroup.opencomputers"))
          .icon(() => new ItemStack(ModItems.CAPACITOR.get()))
          .displayItems((_, output) => output.accept(ModItems.CAPACITOR.get()))
          .build()
    )

  def register(): Unit = {
    api.CreativeTab.instance = TAB.get()
  }
}
