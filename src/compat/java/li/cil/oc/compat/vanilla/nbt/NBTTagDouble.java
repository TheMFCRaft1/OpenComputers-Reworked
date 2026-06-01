package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.DoubleTag;
public class NBTTagDouble implements NBTBase {
    private final DoubleTag tag;

    public NBTTagDouble(double value) {
        this.tag = DoubleTag.valueOf(value);
    }

    public DoubleTag unwrap() {
        return tag;
    }
}
