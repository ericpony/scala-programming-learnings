package net.miladinov.abstractMembers

class RationalClass(numerArg: Int, denomArg: Int) extends {
  val n: Int = numerArg
  val d: Int = denomArg
} with RationalTrait {
  def + (that: RationalClass) = new RationalClass(
    n * that.d + that.n * d,
    d * that.d
  )
}
