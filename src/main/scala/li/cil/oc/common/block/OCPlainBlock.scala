package li.cil.oc.common.block

import com.mojang.serialization.MapCodec
import li.cil.oc.common.block.base.OCBlockProperties
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties

/** Simple NeoForge block without a tile entity (decorative / placeholder blocks). */
class OCPlainBlock(props: Properties) extends Block(props) {
  def this() = this(OCBlockProperties.metal())

  override protected def codec: MapCodec[OCPlainBlock] =
    BlockBehaviour.simpleCodec(new OCPlainBlock(_))
}
