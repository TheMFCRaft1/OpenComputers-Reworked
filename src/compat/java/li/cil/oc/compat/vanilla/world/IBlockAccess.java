package li.cil.oc.compat.vanilla.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import li.cil.oc.compat.vanilla.tileentity.TileEntity;

/** Legacy block access wrapper. */
public interface IBlockAccess {
    BlockState getBlockState(int x, int y, int z);

    default Block getBlock(int x, int y, int z) {
        return getBlockState(x, y, z).getBlock();
    }

    default int getBlockMetadata(int x, int y, int z) {
        return 0;
    }

    default boolean isAirBlock(int x, int y, int z) {
        return getBlockState(x, y, z).isAir();
    }

    default int getLightBrightnessForSkyBlocks(int x, int y, int z, int minBrightness) {
        return minBrightness;
    }

    TileEntity getTileEntity(int x, int y, int z);

    static IBlockAccess of(BlockGetter getter) {
        return new IBlockAccess() {
            @Override
            public BlockState getBlockState(int x, int y, int z) {
                return getter.getBlockState(new BlockPos(x, y, z));
            }

            @Override
            public TileEntity getTileEntity(int x, int y, int z) {
                var be = getter.getBlockEntity(new BlockPos(x, y, z));
                return be instanceof TileEntity te ? te : null;
            }
        };
    }
}
