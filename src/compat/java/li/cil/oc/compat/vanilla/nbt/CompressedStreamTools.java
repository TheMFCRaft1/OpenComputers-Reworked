package li.cil.oc.compat.vanilla.nbt;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;

import java.io.*;

/** Legacy compressed NBT I/O mapped to modern tags. */
public final class CompressedStreamTools {
    private CompressedStreamTools() {}

    public static NBTTagCompound read(InputStream input) throws IOException {
        return new NBTTagCompound(NbtIo.readCompressed(input, NbtAccounter.unlimitedHeap()));
    }

    public static void write(NBTTagCompound tag, OutputStream output) throws IOException {
        NbtIo.writeCompressed(tag.unwrap(), output);
    }
}
