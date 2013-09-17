package net.miladinov.abstractMembers

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int

  lazy val numerator = numerArg / g
  lazy val denominator = denomArg / g

  override def toString = s"$numerator / $denominator"

  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
