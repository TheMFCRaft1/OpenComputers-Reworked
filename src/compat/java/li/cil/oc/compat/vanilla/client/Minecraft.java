package li.cil.oc.compat.vanilla.client;

import li.cil.oc.compat.vanilla.entity.player.EntityPlayer;

/** Legacy client singleton stub for @SideOnly code paths. */
public class Minecraft {
    private static final Minecraft INSTANCE = new Minecraft();

    public EntityPlayer thePlayer;
    public Object currentScreen;

    public static Minecraft getMinecraft() {
        return INSTANCE;
    }

    public void displayGuiScreen(Object screen) {
        currentScreen = screen;
    }
}
