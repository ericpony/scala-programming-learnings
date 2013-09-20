package net.miladinov.enumerations

// Some other languages, including Java and C#, have enumerations as a built-in language construct
// to define new types. Scala does not need special syntax for enumerations. Instead,
// there's a class in its standard library, scala.Enumeration. To create a new enumeration,
// you define an object that extends this class, as in the following example, which defines
// a new enumeration of Colors:
object Color extends Enumeration {
  val Red = Value
  val Green = Value
  val Blue = Value
}
