package cpw.mods.fml.common.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

/** Legacy GameRegistry stub — new content uses DeferredRegister. */
public final class GameRegistry {
    public enum Type {
        BLOCK, ITEM
    }

    private GameRegistry() {}

    public static void registerBlock(Block block, String name) {
        // Registered via DeferredRegister during port
    }

    public static void registerItem(Item item, String name) {
        // Registered via DeferredRegister during port
    }

    public static void registerTileEntity(Class<?> clazz, String name) {
        // Registered via BlockEntityType DeferredRegister during port
    }

    public static void registerTileEntityWithAlternatives(Class<?> clazz, String name, String... alternatives) {
        registerTileEntity(clazz, name);
    }

    public static Block findBlock(String modId, String name) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(modId, name));
    }

    public static Item findItem(String modId, String name) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(modId, name));
    }
}
