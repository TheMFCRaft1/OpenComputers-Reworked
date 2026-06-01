package net.minecraftforge.oredict;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Minimal ore dictionary stub — tags migration TODO. */
public final class OreDictionary {
    private static final Map<String, List<ItemStack>> ORES = new HashMap<>();

    private OreDictionary() {}

    public static void registerOre(String name, Block block) {
        registerOre(name, new ItemStack(block));
    }

    public static void registerOre(String name, ItemStack stack) {
        ORES.computeIfAbsent(name, k -> new ArrayList<>()).add(stack);
    }

    public static List<ItemStack> getOres(String name) {
        return ORES.getOrDefault(name, List.of());
    }
}
