package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
public class NBTTagList implements NBTBase {
    private final ListTag tag;

    public NBTTagList() {
        this.tag = new ListTag();
    }

    public NBTTagList(ListTag tag) {
        this.tag = tag == null ? new ListTag() : tag;
    }

    public ListTag unwrap() {
        return tag;
    }

    public void appendTag(Tag value) {
        tag.add(value);
    }

    public int tagCount() {
        return tag.size();
    }

    public Tag get(int index) {
        return tag.get(index);
    }

    public NBTTagCompound getCompoundTagAt(int index) {
        return new NBTTagCompound(tag.getCompound(index));
    }
}
