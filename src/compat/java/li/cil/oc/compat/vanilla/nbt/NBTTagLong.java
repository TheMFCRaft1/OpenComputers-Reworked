package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.LongTag;

public class NBTTagLong implements NBTBase {
    private final LongTag tag;

    public NBTTagLong(long value) {
        this.tag = LongTag.valueOf(value);
    }

    public LongTag unwrap() {
        return tag;
    }
}
