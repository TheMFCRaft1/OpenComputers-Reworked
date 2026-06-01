package li.cil.oc.compat.vanilla.tileentity;

import li.cil.oc.compat.CompatRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import li.cil.oc.compat.vanilla.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/** Legacy tile entity base class mapped to NeoForge {@link BlockEntity}. */
public abstract class TileEntity extends BlockEntity {
  private static final ThreadLocal<BlockEntityType<?>> PENDING_TYPE = new ThreadLocal<>();
  private static final ThreadLocal<BlockPos> PENDING_POS = new ThreadLocal<>();
  private static final ThreadLocal<BlockState> PENDING_STATE = new ThreadLocal<>();

  public static void runWithContext(BlockEntityType<?> type, BlockPos pos, BlockState state, Runnable action) {
    PENDING_TYPE.set(type);
    PENDING_POS.set(pos);
    PENDING_STATE.set(state);
    try {
      action.run();
    } finally {
      PENDING_TYPE.remove();
      PENDING_POS.remove();
      PENDING_STATE.remove();
    }
  }

  public static <T extends TileEntity> T createWithContext(
      BlockEntityType<?> type, BlockPos pos, BlockState state, java.util.function.Supplier<T> supplier) {
    PENDING_TYPE.set(type);
    PENDING_POS.set(pos);
    PENDING_STATE.set(state);
    try {
      return supplier.get();
    } finally {
      PENDING_TYPE.remove();
      PENDING_POS.remove();
      PENDING_STATE.remove();
    }
  }

    public int xCoord;
    public int yCoord;
    public int zCoord;
    public int blockMetadata;
    public boolean tileEntityInvalid;
    public boolean canUpdate;

    public TileEntity() {
        super(
            PENDING_TYPE.get() != null ? PENDING_TYPE.get() : CompatRegistry.placeholderBlockEntityType(),
            PENDING_POS.get() != null ? PENDING_POS.get() : BlockPos.ZERO,
            PENDING_STATE.get() != null ? PENDING_STATE.get() :
                net.minecraft.world.level.block.Blocks.STONE.defaultBlockState()
        );
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

    public void tick() {
        if (canUpdate) updateEntity();
    }

    public void readFromNBT(li.cil.oc.compat.vanilla.nbt.NBTTagCompound tag) {
        CompoundTag compound = tag.unwrap();
        HolderLookup.Provider registries = registriesOrEmpty();
        loadAdditional(compound, registries);
    }

    public void writeToNBT(li.cil.oc.compat.vanilla.nbt.NBTTagCompound tag) {
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
        return level != null ? level.registryAccess() : net.minecraft.core.RegistryAccess.EMPTY;
    }
}
