package net.minecraftforge.common;

import li.cil.oc.compat.vanilla.world.World;

/** Legacy dimension lookup stub. */
public final class DimensionManager {
    private DimensionManager() {}

    public static World getWorld(int dimensionId) {
        return null;
    }

    public static java.io.File getCurrentSaveRootDirectory() {
        return new java.io.File(".");
    }
}
