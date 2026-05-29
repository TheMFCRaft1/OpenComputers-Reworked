package li.cil.oc.common.init

import li.cil.oc.ModCreativeTabs

/** Wires all DeferredRegister content suppliers. Called from the mod constructor. */
object ModRegistration {
  def registerContent(): Unit = {
    ModBlocks.register()
    ModBlockEntities.register()
    ModItems.register()
    ModCreativeTabs.register()
  }
}
