package li.cil.oc.compat.vanilla.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import li.cil.oc.compat.vanilla.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import net.minecraft.util.RandomSource;

/** Legacy World wrapper around NeoForge {@link Level}. */
public class World {
    public final RandomSource rand;
    public final WorldProvider provider = new WorldProvider();
    private final Level level;

    protected World(Level level) {
        this.level = level;
        this.rand = level.getRandom();
    }

    public static World of(Level level) {
        return level == null ? null : new World(level);
    }

    public Level unwrap() {
        return level;
    }

    public boolean isRemote() {
        return level.isClientSide;
    }

    public boolean blockExists(int x, int y, int z) {
        return level.hasChunkAt(new BlockPos(x, y, z));
    }

    public TileEntity getTileEntity(int x, int y, int z) {
        BlockEntity be = level.getBlockEntity(new BlockPos(x, y, z));
        return be instanceof TileEntity te ? te : null;
    }

    public Block getBlock(int x, int y, int z) {
        return level.getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    public int getBlockMetadata(int x, int y, int z) {
        return 0;
    }

    public BlockState getBlockState(int x, int y, int z) {
        return level.getBlockState(new BlockPos(x, y, z));
    }

    public void markBlockForUpdate(int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        BlockState state = level.getBlockState(pos);
        level.sendBlockUpdated(pos, state, state, 3);
    }

    public void notifyBlocksOfNeighborChange(int x, int y, int z, Block block) {
        level.updateNeighborsAt(new BlockPos(x, y, z), block);
    }

    public void notifyBlocksOfNeighborChange(BlockPos pos, Block block) {
        level.updateNeighborsAt(pos, block);
    }

    public long getTotalWorldTime() {
        return level.getGameTime();
    }

    public Player getPlayerEntityByName(String name) {
        if (level instanceof ServerLevel server) {
            return server.getServer().getPlayerList().getPlayerByName(name);
        }
        return null;
    }

    public FluidState getFluidState(int x, int y, int z) {
        return level.getFluidState(new BlockPos(x, y, z));
    }

    public int getRedstonePower(int x, int y, int z, int side) {
        return level.getSignal(new BlockPos(x, y, z), Direction.from3DDataValue(side));
    }

    public boolean isSideSolid(int x, int y, int z, ForgeDirection side) {
        BlockPos pos = new BlockPos(x, y, z);
        return level.getBlockState(pos).isFaceSturdy(level, pos, side.toDirection());
    }

    public void markTileEntityChunkModified(int x, int y, int z, TileEntity te) {
        if (te != null) level.blockEntityChanged(new BlockPos(x, y, z));
    }

    public void scheduleBlockUpdate(int x, int y, int z, Block block, int delay) {
        level.scheduleTick(new BlockPos(x, y, z), block, delay);
    }
}
