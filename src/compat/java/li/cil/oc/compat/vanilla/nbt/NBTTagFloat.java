package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.FloatTag;
public class NBTTagFloat implements NBTBase {
    private final FloatTag tag;

    public NBTTagFloat(float value) {
        this.tag = FloatTag.valueOf(value);
    }

    public FloatTag unwrap() {
        return tag;
    }
}
