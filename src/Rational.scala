
class Rational(n: Int, d: Int) {
  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numerator = n / g
  val denominator = d / g

  def this(n: Int) = this(n, 1)

  override def toString = numerator + "/" + denominator

  def + (that: Rational): Rational =
    new Rational(
      numerator * that.denominator + that.numerator * denominator,
      denominator * that.denominator
    )

  def * (that: Rational): Rational =
    new Rational(numerator * that.numerator, denominator * that.denominator)

  def lessThan(that: Rational) =
    numerator * that.denominator < that.numerator * denominator

  def max(that: Rational) =
    if (lessThan(that)) that else this

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}
