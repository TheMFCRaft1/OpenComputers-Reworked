package net.minecraft.util;

import net.minecraft.world.phys.AABB;

/** Legacy AABB wrapper. */
public class AxisAlignedBB {
    public final AABB bb;

    public AxisAlignedBB(AABB bb) {
        this.bb = bb;
    }

    public static AxisAlignedBB getBoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new AxisAlignedBB(new AABB(minX, minY, minZ, maxX, maxY, maxZ));
    }

    public double minX() { return bb.minX; }
    public double minY() { return bb.minY; }
    public double minZ() { return bb.minZ; }
    public double maxX() { return bb.maxX; }
    public double maxY() { return bb.maxY; }
    public double maxZ() { return bb.maxZ; }
}
