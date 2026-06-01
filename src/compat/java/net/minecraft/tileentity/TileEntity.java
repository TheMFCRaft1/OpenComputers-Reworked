package net.minecraft.tileentity;

import li.cil.oc.compat.CompatRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/** Legacy tile entity base class mapped to NeoForge {@link BlockEntity}. */
public abstract class TileEntity extends BlockEntity {
    public int xCoord;
    public int yCoord;
    public int zCoord;
    public int blockMetadata;
    public boolean tileEntityInvalid;
    public boolean canUpdate;

    public TileEntity() {
        super(CompatRegistry.placeholderBlockEntityType(), BlockPos.ZERO,
            net.minecraft.world.level.block.Blocks.STONE.defaultBlockState());
        syncCoords();
    }

    protected TileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        syncCoords();
    }

    public World getWorldObj() {
        Level level = getLevel();
        return World.of(level);
    }

    public void setWorldObj(World world) {
        setLevel(world == null ? null : world.unwrap());
    }

    public boolean isInvalid() {
        return tileEntityInvalid || isRemoved();
    }

    public void invalidate() {
        tileEntityInvalid = true;
        setRemoved();
    }

    public void validate() {
        tileEntityInvalid = false;
        onLoad();
    }

    public void updateEntity() {}

    @Override
    public void tick() {
        if (canUpdate) updateEntity();
    }

    public void readFromNBT(net.minecraft.nbt.NBTTagCompound tag) {
        CompoundTag compound = tag.unwrap();
        HolderLookup.Provider registries = registriesOrEmpty();
        loadAdditional(compound, registries);
    }

    public void writeToNBT(net.minecraft.nbt.NBTTagCompound tag) {
        CompoundTag compound = tag.unwrap();
        HolderLookup.Provider registries = registriesOrEmpty();
        saveAdditional(compound, registries);
    }

    public void syncCoords() {
        BlockPos pos = getBlockPos();
        if (pos != null) {
            xCoord = pos.getX();
            yCoord = pos.getY();
            zCoord = pos.getZ();
        }
    }

    @Override
    public void setBlockPos(BlockPos pos) {
        super.setBlockPos(pos);
        syncCoords();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        syncCoords();
    }

    public net.minecraft.world.level.block.Block getBlockType() {
        return getBlockState().getBlock();
    }

    public ClientboundBlockEntityDataPacket getDescriptionPacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private HolderLookup.Provider registriesOrEmpty() {
        Level level = getLevel();
        return level != null ? level.registryAccess() : HolderLookup.Provider.EMPTY;
    }
}
