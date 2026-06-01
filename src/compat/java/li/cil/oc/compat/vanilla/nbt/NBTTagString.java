package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.StringTag;
public class NBTTagString implements NBTBase {
    private final StringTag tag;

    public NBTTagString(String value) {
        this.tag = StringTag.valueOf(value);
    }

    public StringTag unwrap() {
        return tag;
    }
}
