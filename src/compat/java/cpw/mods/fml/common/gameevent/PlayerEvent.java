package cpw.mods.fml.common.gameevent;

import cpw.mods.fml.common.eventhandler.Event;
import li.cil.oc.compat.vanilla.entity.player.EntityPlayer;

public class PlayerEvent extends Event {
    public final EntityPlayer entityPlayer;

    public PlayerEvent(EntityPlayer player) {
        this.entityPlayer = player;
    }

    public static class PlayerLoggedInEvent extends PlayerEvent {
        public PlayerLoggedInEvent(EntityPlayer player) {
            super(player);
        }
    }

    public static class PlayerLoggedOutEvent extends PlayerEvent {
        public PlayerLoggedOutEvent(EntityPlayer player) {
            super(player);
        }
    }
}
