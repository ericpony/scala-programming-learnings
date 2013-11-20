package net.miladinov.collection

// RNA Bases

abstract class Base
case object A extends Base
case object T extends Base
case object G extends Base
case object U extends Base

object Base {
  // Array is a function; it inherits from the Function1 trait
  val fromInt: Int => Base = Array(A, T, G, U)

  // Map is a function; it inherits from the Function1 trait
  val toInt: Base => Int = Map(A -> 0, T -> 1, G -> 2, U -> 3)
}
