package net.miladinov.enumerations

// What's significant about this is that it is a completely new type,
// different from all other types. In particular, if you would define another enumeration,
// such as the one below, then Direction.Value would be different from Color.Value because
// the path parts of the two types differ.

object Direction extends Enumeration {
  val North, East, South, West = Value
}
