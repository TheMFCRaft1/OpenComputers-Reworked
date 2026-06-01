package li.cil.oc.compat.vanilla.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/** Legacy entity base mapped to modern entities. */
public class Entity extends net.minecraft.world.entity.Entity {
    public Entity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {}

    @Override
    protected void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {}
}
