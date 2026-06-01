package li.cil.oc.integration.bloodmagic

import li.cil.oc.api.Driver
import li.cil.oc.integration.{Mods, ModProxy}

object ModBloodMagic extends ModProxy {
  override def getMod = Mods.BloodMagic

  override def initialize(): Unit = {
    Driver.add(new DriverBloodAltar)
    Driver.add(new DriverMasterRitualStone)

    Driver.add(new ConverterBloodOrb)
  }
}
