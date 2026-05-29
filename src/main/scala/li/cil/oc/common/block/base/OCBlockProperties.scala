package li.cil.oc.common.block.base

import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour

/** Shared block properties for OpenComputers blocks during the NeoForge port. */
object OCBlockProperties {
  def metal(): BlockBehaviour.Properties =
    BlockBehaviour.Properties.of()
      .strength(2.0f, 5.0f)
      .sound(SoundType.METAL)

  def machine(): BlockBehaviour.Properties =
    metal().noOcclusion()
}
