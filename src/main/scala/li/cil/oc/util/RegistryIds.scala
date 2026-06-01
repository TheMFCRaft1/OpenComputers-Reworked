package li.cil.oc.util

/** Converts legacy camelCase ids to valid NeoForge / Minecraft registry names. */
object RegistryIds {
  def normalize(legacyId: String): String =
    legacyId.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase
}
