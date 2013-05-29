
class Rational(n: Int, d: Int) {
  require(d != 0)
  val numerator: Int = n
  val denominator: Int = d
  override def toString = numerator + "/" + denominator
}
