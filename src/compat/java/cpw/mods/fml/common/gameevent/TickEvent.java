package cpw.mods.fml.common.gameevent;

import cpw.mods.fml.common.eventhandler.Event;

public class TickEvent extends Event {
    public enum Type {
        WORLD,
        PLAYER,
        CLIENT,
        SERVER
    }

    public enum Phase {
        START,
        END
    }
}
