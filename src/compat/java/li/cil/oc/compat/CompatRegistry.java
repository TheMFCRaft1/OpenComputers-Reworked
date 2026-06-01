package li.cil.oc.compat;

import li.cil.oc.OpenComputers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/** Shared compat registrations used by legacy tile entity constructors. */
public final class CompatRegistry {
    private static BlockEntityType<PlaceholderBlockEntity> placeholderType;

    private CompatRegistry() {}

    public static BlockEntityType<?> placeholderBlockEntityType() {
        if (placeholderType == null) {
            placeholderType = BlockEntityType.Builder.of(
                PlaceholderBlockEntity::new,
                Blocks.STONE
            ).build(null);
        }
        return placeholderType;
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, path);
    }

    public static Block block(String path) {
        return BuiltInRegistries.BLOCK.get(id(path));
    }

    private static final class PlaceholderBlockEntity extends BlockEntity {
        PlaceholderBlockEntity(BlockPos pos, BlockState state) {
            super(placeholderType, pos, state);
        }
    }
}
