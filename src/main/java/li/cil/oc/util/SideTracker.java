package li.cil.oc.util;

import net.neoforged.fml.util.thread.SidedThreadGroups;

import java.util.Collections;
import java.util.Set;

public final class SideTracker {
    private static final Set<Thread> serverThreads = Collections.newSetFromMap(new java.util.WeakHashMap<Thread, Boolean>());

    public static void addServerThread() {
        serverThreads.add(Thread.currentThread());
    }

    public static boolean isServer() {
        return Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER
            || serverThreads.contains(Thread.currentThread());
    }

    public static boolean isClient() {
        return !isServer();
    }
}
