package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.ShortTag;

public class NBTTagShort implements NBTBase {
    private final ShortTag tag;

    public NBTTagShort(short value) {
        this.tag = ShortTag.valueOf(value);
    }

    public ShortTag unwrap() {
        return tag;
    }
}
