package li.cil.oc.common.init

import li.cil.oc.Constants
import li.cil.oc.common.block.base.OCBlockProperties
import net.minecraft.world.level.block.state.BlockBehaviour

/** Metadata for legacy tile-entity blocks registered through {@link LegacyCompatBlock}. */
object ModLegacyContent {
  case class LegacyBlockDefinition(
    id: String,
    properties: BlockBehaviour.Properties = OCBlockProperties.machine()
  )

  /** Blocks that only need a NeoForge shell without a legacy tile entity. */
  case class PlainBlockDefinition(
    id: String,
    properties: BlockBehaviour.Properties = OCBlockProperties.metal()
  )

  val legacyBlocks: Seq[LegacyBlockDefinition] = Seq(
    LegacyBlockDefinition(Constants.BlockName.AccessPoint),
    LegacyBlockDefinition(Constants.BlockName.Adapter),
    LegacyBlockDefinition(Constants.BlockName.Assembler),
    LegacyBlockDefinition(Constants.BlockName.Cable),
    LegacyBlockDefinition(Constants.BlockName.CarpetedCapacitor),
    LegacyBlockDefinition(Constants.BlockName.CaseTier1),
    LegacyBlockDefinition(Constants.BlockName.CaseTier2),
    LegacyBlockDefinition(Constants.BlockName.CaseTier3),
    LegacyBlockDefinition(Constants.BlockName.CaseCreative),
    LegacyBlockDefinition(Constants.BlockName.Charger),
    LegacyBlockDefinition(Constants.BlockName.Disassembler),
    LegacyBlockDefinition(Constants.BlockName.DiskDrive),
    LegacyBlockDefinition(Constants.BlockName.Geolyzer),
    LegacyBlockDefinition(Constants.BlockName.HologramTier1),
    LegacyBlockDefinition(Constants.BlockName.HologramTier2),
    LegacyBlockDefinition(Constants.BlockName.Keyboard),
    LegacyBlockDefinition(Constants.BlockName.Microcontroller),
    LegacyBlockDefinition(Constants.BlockName.MotionSensor),
    LegacyBlockDefinition(Constants.BlockName.NetSplitter),
    LegacyBlockDefinition(Constants.BlockName.PowerConverter),
    LegacyBlockDefinition(Constants.BlockName.PowerDistributor),
    LegacyBlockDefinition(Constants.BlockName.Print),
    LegacyBlockDefinition(Constants.BlockName.Printer),
    LegacyBlockDefinition(Constants.BlockName.Raid),
    LegacyBlockDefinition(Constants.BlockName.Redstone),
    LegacyBlockDefinition(Constants.BlockName.Relay),
    LegacyBlockDefinition(Constants.BlockName.Robot),
    LegacyBlockDefinition(Constants.BlockName.ScreenTier1),
    LegacyBlockDefinition(Constants.BlockName.ScreenTier2),
    LegacyBlockDefinition(Constants.BlockName.ScreenTier3),
    LegacyBlockDefinition(Constants.BlockName.Rack),
    LegacyBlockDefinition(Constants.BlockName.Switch),
    LegacyBlockDefinition(Constants.BlockName.Transposer),
    LegacyBlockDefinition(Constants.BlockName.Waypoint)
  )

  val plainBlocks: Seq[PlainBlockDefinition] = Seq(
    PlainBlockDefinition(Constants.BlockName.Endstone, OCBlockProperties.metal().strength(3.0f, 15.0f)),
    PlainBlockDefinition(Constants.BlockName.ChameliumBlock, OCBlockProperties.metal()),
    PlainBlockDefinition(
      Constants.BlockName.RobotAfterimage,
      OCBlockProperties.machine().noCollission().air().strength(0.0f).noOcclusion()
    )
  )

  def legacyById(id: String): Option[LegacyBlockDefinition] =
    legacyBlocks.find(_.id == id)

}
