package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.nbt.ByteArrayTag;
public class NBTTagByteArray extends ByteArrayTag implements NBTBase {
    public NBTTagByteArray(byte[] value) {
        super(value);
    }
}
