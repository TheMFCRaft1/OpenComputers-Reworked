package li.cil.oc.integration

/** Minimal mod list stub for optional integrations during port. */
object Mods {
  object IDs {
    final val AppliedEnergistics2 = "appliedenergistics2"
    final val ForgeMultipart = "ForgeMultipart"
    final val RedLogic = "RedLogic"
    final val ProjectRedTransmission = "ProjRed|Transmission"
  }

  final val Minecraft: ModProxy = new ModProxy {}

  def init(): Unit = ()
}
