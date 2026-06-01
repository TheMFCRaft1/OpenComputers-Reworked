package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.IntArrayTag;
public class NBTTagIntArray extends IntArrayTag implements NBTBase {
    public NBTTagIntArray(int[] value) {
        super(value);
    }
}
