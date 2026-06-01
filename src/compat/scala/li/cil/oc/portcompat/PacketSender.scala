package li.cil.oc.server

/** Networking stub until CustomPacketPayload migration is complete. */
object PacketSender {
  def sendComputerState(computer: Any): Unit = ()

  def sendColorChange(tileEntity: Any): Unit = ()

  def sendAbstractBusState(tileEntity: Any): Unit = ()
}
