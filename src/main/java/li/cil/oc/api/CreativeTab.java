package li.cil.oc.api;

import net.minecraft.world.item.CreativeModeTab;

/**
 * Allows access to the creative tab used by OpenComputers.
 */
public final class CreativeTab {
    /**
     * The creative tab used by OpenComputers.
     * Set at runtime when the mod loads on NeoForge 1.21+.
     */
    public static CreativeModeTab instance = null;

    private CreativeTab() {
    }
}
