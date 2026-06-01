package li.cil.oc.util

/** Config stub until internet filtering is ported. */
class InternetFilteringRule(rule: String) {
  def invalid(): Boolean = false

  def matches(_address: String): Boolean = false
}
