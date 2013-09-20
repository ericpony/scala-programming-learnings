package net.miladinov.enumerations

// Some other languages, including Java and C#, have enumerations as a built-in language construct
// to define new types. Scala does not need special syntax for enumerations. Instead,
// there's a class in its standard library, scala.Enumeration. To create a new enumeration,
// you define an object that extends this class, as in the following example, which defines
// a new enumeration of Colors:
object Color extends Enumeration {
  val Red, Green, Blue = Value
}

// This object definition provides three values: Color.Red, Color.Green, and Color.Blue.
// You could also import everything in Color with:
//
// import Color._
//
// and then just use Red, Green, and Blue. But what is the type of these values? Enumeration
// defines an inner class named Value, // and the same-named parameter-less Value method returns
// a fresh instance of that class. This means that a value such as Color.Red is of type
// Color.Value. Color.Value is the type of all enumeration values defined in object Color.
// It's a path-dependent type, with Color being the path and Value being the dependent type.
// What's significant about this is that it is a completely new type,
// different from all other types.
