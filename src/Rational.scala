
class Rational(n: Int, d: Int) extends Ordered[Rational] {
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

  def + (i: Int): Rational =
    new Rational(numerator + i * denominator, denominator)

  def - (that: Rational): Rational =
    new Rational(
      numerator * that.denominator - that.numerator * denominator,
      denominator * that.denominator
    )

  def - (i: Int): Rational =
    new Rational(numerator - i * denominator, denominator)

  def * (that: Rational): Rational =
    new Rational(numerator * that.numerator, denominator * that.denominator)

  def * (i: Int): Rational =
    new Rational(numerator * i, denominator)

  def / (that: Rational): Rational =
    new Rational(numerator * that.denominator, denominator * that.numerator)

  def / (i: Int): Rational =
    new Rational(numerator, denominator * i)

  def max(that: Rational) =
    if (this < that) that else this

  def compare(that: Rational) =
    (this.numerator * that.denominator) - (that.numerator * this.denominator)

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}
