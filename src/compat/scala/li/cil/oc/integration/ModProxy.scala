package li.cil.oc.integration

trait ModProxy {
  def getMod: AnyRef = null
  def initialize(): Unit = ()
}
