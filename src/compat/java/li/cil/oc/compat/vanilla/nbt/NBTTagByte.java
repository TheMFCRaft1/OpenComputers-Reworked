package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.ByteTag;

public class NBTTagByte implements NBTBase {
    private final ByteTag tag;

    public NBTTagByte(byte value) {
        this.tag = ByteTag.valueOf(value);
    }

    public ByteTag unwrap() {
        return tag;
    }
}
