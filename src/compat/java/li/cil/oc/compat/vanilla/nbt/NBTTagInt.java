package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.IntTag;

public class NBTTagInt implements NBTBase {
    private final IntTag tag;

    public NBTTagInt(int value) {
        this.tag = IntTag.valueOf(value);
    }

    public IntTag unwrap() {
        return tag;
    }
}
