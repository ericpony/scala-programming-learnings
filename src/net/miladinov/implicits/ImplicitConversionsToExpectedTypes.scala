package net.miladinov.implicits

/**
 * Implicit conversion to an expected type is the first place the compiler will use implicits.
 * The rule is simple. Whenever the compiler sees an X, but needs a Y, it will look for
 * an implicit function that converts X to Y.
 */
object ImplicitConversionsToExpectedTypes {
  /**
   * For example, normally a double cannot be used as an integer, because it loses precision:
   *
   * scala>  val example1: Int = 3.5
   * <console>:7: error: type mismatch;
   *  found   : Double(3.5)
   *  required: Int
   *        val example1: Int = 3.5
   *                            ^
   *
   * However, you can define an implicit conversion to smooth this over:
   *
   * scala>  import net.miladinov.implicits.ImplicitConversionsToExpectedTypes.doubleToInt
   * import net.miladinov.implicits.ImplicitConversionsToExpectedTypes.doubleToInt
   *
   * scala>  val example2: Int = 3.4
   * example2: Int = 3
   *
   * @param x The double you want to convert to int
   * @return The double converted to int
   */
  implicit def doubleToInt(x: Double): Int = x.toInt
}
