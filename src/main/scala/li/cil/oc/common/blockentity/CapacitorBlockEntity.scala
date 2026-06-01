package li.cil.oc.common.blockentity

import li.cil.oc.Constants
import li.cil.oc.Settings
import li.cil.oc.api.driver.DeviceInfo
import li.cil.oc.api.driver.DeviceInfo.DeviceAttribute
import li.cil.oc.api.driver.DeviceInfo.DeviceClass
import net.minecraft.core.{BlockPos, Direction}
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

import java.util
import scala.jdk.CollectionConverters._

/** NeoForge 1.21.1 capacitor block entity (Phase 3 PoC). OC network node wiring follows in Phase 3+. */
class CapacitorBlockEntity(
  beType: BlockEntityType[?],
  pos: BlockPos,
  state: BlockState
) extends OCBlockEntity(beType, pos, state) with DeviceInfo {

  /** Stored energy; full OC node/connector integration is TODO until Network.scala is migrated. */
  var storedEnergy: Double = maxCapacity

  var capacity: Double = maxCapacity

  private final lazy val deviceInfo = Map(
    DeviceAttribute.Class -> DeviceClass.Power,
    DeviceAttribute.Description -> "Battery",
    DeviceAttribute.Vendor -> Constants.DeviceInfo.DefaultVendor,
    DeviceAttribute.Product -> "CapBank3x",
    DeviceAttribute.Capacity -> maxCapacity.toString
  ).asJava

  override def getDeviceInfo: util.Map[String, String] = deviceInfo

  override protected def initialize(): Unit = {
    super.initialize()
    recomputeCapacity(updateSecondGradeNeighbors = true)
  }

  def recomputeCapacity(updateSecondGradeNeighbors: Boolean = false): Unit = {
    if (!isServer) return

    val adjacentBonus = Direction.values().count { side =>
      val neighborPos = getBlockPos.relative(side)
      world.getBlockEntity(neighborPos) match {
        case _: CapacitorBlockEntity => true
        case _ => false
      }
    }

    val indirectBonus = Direction.values().count { side =>
      val neighborPos = getBlockPos.relative(side, 2)
      world.getBlockEntity(neighborPos) match {
        case capacitor: CapacitorBlockEntity =>
          if (updateSecondGradeNeighbors) capacitor.recomputeCapacity()
          true
        case _ => false
      }
    }

    capacity = Settings.get.bufferCapacitor +
      Settings.get.bufferCapacitorAdjacencyBonus * adjacentBonus +
      Settings.get.bufferCapacitorAdjacencyBonus / 2 * indirectBonus

    storedEnergy = math.min(storedEnergy, capacity)
    setChanged()
  }

  def comparatorOutput: Int =
    if (capacity <= 0) 0
    else math.round(15.0 * storedEnergy / capacity).toInt

  override protected def readFromNBTForServer(tag: CompoundTag): Unit = {
    super.readFromNBTForServer(tag)
    if (tag.contains("Energy")) storedEnergy = tag.getDouble("Energy")
    if (tag.contains("Capacity")) capacity = tag.getDouble("Capacity")
  }

  override protected def writeToNBTForServer(tag: CompoundTag): Unit = {
    super.writeToNBTForServer(tag)
    tag.putDouble("Energy", storedEnergy)
    tag.putDouble("Capacity", capacity)
  }

  protected def maxCapacity: Double =
    Settings.get.bufferCapacitor + Settings.get.bufferCapacitorAdjacencyBonus * 9
}
