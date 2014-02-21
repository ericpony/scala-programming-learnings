package net.miladinov.equality

import scala.annotation.tailrec

object RecipesForEqualsAndHashCodes {

  // Here's the recipe for overriding equals:
  // 1. If you're going to override equals in a non-final class, you should create a canEqual method.
  //    If the inherited definition of equals is from AnyRef (that is, equals was not redefined
  //    higher up in the class hierarchy), the definition of canEqual will be new, otherwise it will
  //    override a previous definition of a method with the same name. The only exception to this
  //    requirement is for final classes that redefine the equals method inherited from AnyRef.
  //    For them the subclass anomalies described earlier cannot arise; consequently they need not
  //    define canEqual. The type of the object passed to canEqual should be Any:
  //
  //    def canEqual (other: Any): Boolean = /* ... */
  //
  // 2. The canEqual method should yield true if the argument object is an instance of the current
  //    class (i.e., the class in which canEqual is defined), false otherwise:
  //
  //    def canEqual (other: Any): Boolean = other.isInstanceOf[Rational]
  //
  // 3. In the equals method, make sure you declare the type of the object passed as Any:
  //
  //    override def equals (other: Any): Boolean =
  //
  // 4. Write the body of the equals method as a single match expression. The selector of the match
  //    should be the object passed to equals:
  //
  //    other match {
  //      /* ... */
  //    }
  //
  // 5. The match expression should have two cases. The first case should declare a typed pattern
  //    for the type of the class on which you’re defining the equals method:
  //
  //    case that: Rational =>
  //
  // 6. In the body of this case, write an expression that logical-ands together the individual
  //    expressions that must be true for the objects to be equal. If the equals method you are
  //    overriding is not that of AnyRef, you will most likely want to include an invocation of
  //    the superclass's equals method:
  //
  //    super.equals(that) &&
  //
  //    If you are defining equals for a class that first introduced canEqual, you should invoke
  //    canEqual on the argument to the equality method, passing this as the argument:
  //
  //    (that canEqual this) &&
  //
  //    Overriding redefinitions of equals should also include the canEqual invocation, unless
  //    they contain a call to super.equals. In the latter case, the canEqual test will already
  //    be done by the superclass call. Lastly, for each field relevant to equality, verify that
  //    the field in this object is equal to the corresponding field in the passed object:
  //
  //    numer == that.numer &&
  //    denom == that.denom
  //
  // 7. For the second case, use a wildcard pattern that yields false:
  //
  //    case _ => false
  //
  // If you adhere to the preceding recipe, equality is guaranteed to be an equivalence relation,
  // as is required by the equals contract.

  // Here's a version of the Rational class, which we've worked with before,
  // altered to demonstrate our equals() recipe in action:

  class Rational (n: Int, d: Int) {
    require(d != 0)

    private val g = gcd(n.abs, d.abs)
    val numer = (if (d < 0) -n else n) / g
    val denom = d.abs / g

    @tailrec
    private def gcd (a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    override def equals (other: Any): Boolean =
      other match {
        case that: Rational => (that canEqual this) && numer == that.numer && denom == that.denom
        case _ => false
      }

    def canEqual (other: Any): Boolean = other.isInstanceOf[Rational]

    override def hashCode: Int = 41 * (41 + numer) + denom

    override def toString = if (denom == 1) numer.toString else numer + "/" + denom
  }

  // For hashCode, you can usually achieve satisfactory results if you use the following recipe, which is similar to a
  // recipe recommended for Java classes in Effective Java. Include in the calculation each field in your object that
  // is used to determine equality in the equals method (the "relevant" fields). For each relevant field, no matter its
  // type, you can calculate a hash code by invoking hashCode on it. To calculate a hash code for the entire object,
  // add 41 to the first field's hash code, multiply that by 41, add the second field's hash code, multiply that by
  // 41, add the third field's hash code, multiply that by 41, and so on, until you've done this for all relevant fields.

  // For example, to implement the hash code for an object that has five relevant fields named a, b, c, d and e, you
  // would write:

  class Example {
    val a, b, c, d, e: Any = "example"

    override def hashCode: Int =
      41 * (
        41 * (
          41 * (
            41 * (
              41 + a.hashCode
            ) + b.hashCode
          ) + c.hashCode
        ) + d.hashCode
      ) + e.hashCode
  }

  // If you wish, you can leave off the hashCode invocation on fields of type Int, Short, Byte and Char. The hash code
  // for an Int is the value of the Int, as are the hash codes of Shorts, Bytes, and Chars when automatically widened to
  // Int. Given numer or denom are Ints, therefore, we implemented Rational's hashCode method like this:

  class RationalExcerpt (n: Int, d: Int) extends Rational(n, d) {
    override def hashCode: Int = 41 * (41 + numer) + denom
  }

}
