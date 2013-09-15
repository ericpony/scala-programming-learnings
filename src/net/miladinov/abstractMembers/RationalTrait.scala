package net.miladinov.abstractMembers

// Abstract vals sometimes play a role analogous to superclass parameters: they let you provide details
// in a subclass that are missing in a superclass. This is particularly important for traits,
// because traits don’t have a constructor to which you could pass parameters. So the usual notion of
// parameterizing a trait works via abstract vals that are implemented in subclasses. As an example,
// consider a reformulation of class Rational from earlier, as a trait:
trait RationalTrait {
  val n: Int
  val d: Int

  require(d != 0)

  private val g = gcd(n, d)
  val numerator = n / g
  val denominator = d / g

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def toString: String = s"$numerator / $denominator"
}

// The Rational class had two parameters: n for the numerator of the rational number,
// and d for the denominator. The RationalTrait trait given here defines instead two abstract
// vals: numerator and denominator. To instantiate a concrete instance of that trait,
// you need to implement the abstract val definitions. Here’s an example:
/*
new RationalTrait {
  val numerator = 1
  val denominator = 2
}
*/

// Here the keyword new appears in front of a trait name, RationalTrait, which is followed by a class body
// in curly braces. This expression yields an instance of an anonymous class that mixes in the trait and is
// defined by the body. This particular anonymous class instantiation has an effect analogous to the
// instance creation new Rational(1, 2). The analogy is not perfect, however. There’s a subtle difference
// concerning the order in which expressions are initialized. When you write:
/*
new Rational(expr1, expr2)
*/

// the two expressions, expr1 and expr2, are evaluated before class Rational is initialized, so the values
// of expr1 and expr2 are available for the initialization of class Rational. For traits, however, the
// situation is the opposite. When you write:
/*
new RationalTrait {
  val numerator = expr1
  val denominator = expr2
}
*/

// the expressions, expr1 and expr2, are evaluated as part of the initialization of the anonymous class,
// but the anonymous class is initialized after the RationalTrait. So the values of numerator
// and denominator are not available during the initialization of RationalTrait (more precisely,
// a selection of either value would yield the default value for type Int, 0). For the definition
// of RationalTrait given previously, this is not a problem, because the trait's initialization does
// not make use of values numerator or denominator.
