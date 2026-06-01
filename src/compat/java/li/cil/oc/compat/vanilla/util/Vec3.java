package li.cil.oc.compat.vanilla.util;

public class Vec3 {
    public final double xCoord;
    public final double yCoord;
    public final double zCoord;

    public Vec3(double x, double y, double z) {
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
    }

    public static Vec3 createVectorHelper(double x, double y, double z) {
        return new Vec3(x, y, z);
    }
}
