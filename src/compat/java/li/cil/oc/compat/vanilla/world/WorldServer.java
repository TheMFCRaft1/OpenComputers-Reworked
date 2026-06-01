package li.cil.oc.compat.vanilla.world;

/** Legacy server world type alias. */
public class WorldServer extends World {
    public WorldServer(net.minecraft.server.level.ServerLevel level) {
        super(level);
    }
}
