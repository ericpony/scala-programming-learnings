package net.miladinov.enumerations

// What's significant about this is that it is a completely new type,
// different from all other types. In particular, if you would define another enumeration,
// such as the one below, then Direction.Value would be different from Color.Value because
// the path parts of the two types differ.

// Scala's Enumeration class also offers many other features found in the enumeration designs
// of other languages. You can associate names with enumeration values by using a different
// overloaded variant of the Value method:

object Direction extends Enumeration {
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}

// You can iterate over the values of an enumeration via the set returned by the enumeration's
// values method:

// for (d <- Direction.values) print(d + " ")
// prints: North East South West

// Values of an enumeration are numbered from 0, and you can find out the number
// of an enumeration value by its id method:
// Direction.East.id
// returns: Int = 1

// It's also possible to go the other way, from a non-negative integer number to
// the value that has this number as id in an enumeration:

// Direction(1)
// returns: Direction.Value = East
