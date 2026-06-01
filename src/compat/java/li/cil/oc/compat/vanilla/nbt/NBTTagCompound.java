package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.Set;
import java.util.UUID;

/** Legacy NBT compound wrapper. */
public class NBTTagCompound implements NBTBase {
    private final CompoundTag tag;

    public NBTTagCompound() {
        this.tag = new CompoundTag();
    }

    public NBTTagCompound(CompoundTag tag) {
        this.tag = tag == null ? new CompoundTag() : tag;
    }

    public CompoundTag unwrap() {
        return tag;
    }

    public boolean hasNoTags() {
        return tag.isEmpty();
    }

    public boolean hasKey(String key) {
        return tag.contains(key);
    }

    public void removeTag(String key) {
        tag.remove(key);
    }

    public void setTag(String key, Tag value) {
        tag.put(key, value);
    }

    public void setTag(String key, NBTTagCompound value) {
        tag.put(key, value.unwrap());
    }

    public void setTag(String key, NBTTagList value) {
        tag.put(key, value.unwrap());
    }

    public void setString(String key, String value) {
        tag.putString(key, value);
    }

    public String getString(String key) {
        return tag.getString(key);
    }

    public void setBoolean(String key, boolean value) {
        tag.putBoolean(key, value);
    }

    public boolean getBoolean(String key) {
        return tag.getBoolean(key);
    }

    public void setByte(String key, byte value) {
        tag.putByte(key, value);
    }

    public byte getByte(String key) {
        return tag.getByte(key);
    }

    public void setShort(String key, short value) {
        tag.putShort(key, value);
    }

    public short getShort(String key) {
        return tag.getShort(key);
    }

    public void setInteger(String key, int value) {
        tag.putInt(key, value);
    }

    public int getInteger(String key) {
        return tag.getInt(key);
    }

    public void setLong(String key, long value) {
        tag.putLong(key, value);
    }

    public long getLong(String key) {
        return tag.getLong(key);
    }

    public void setFloat(String key, float value) {
        tag.putFloat(key, value);
    }

    public float getFloat(String key) {
        return tag.getFloat(key);
    }

    public void setDouble(String key, double value) {
        tag.putDouble(key, value);
    }

    public double getDouble(String key) {
        return tag.getDouble(key);
    }

    public void setByteArray(String key, byte[] value) {
        tag.putByteArray(key, value);
    }

    public byte[] getByteArray(String key) {
        return tag.getByteArray(key);
    }

    public void setIntArray(String key, int[] value) {
        tag.putIntArray(key, value);
    }

    public int[] getIntArray(String key) {
        return tag.getIntArray(key);
    }

    public NBTTagCompound getCompoundTag(String key) {
        return new NBTTagCompound(tag.getCompound(key));
    }

    public NBTTagList getTagList(String key, int type) {
        return new NBTTagList(tag.getList(key, type));
    }

    public Set<String> getKeySet() {
        return tag.getAllKeys();
    }

    public UUID getUuid(String key) {
        return tag.getUUID(key);
    }

    public void setUuid(String key, UUID uuid) {
        tag.putUUID(key, uuid);
    }
}
