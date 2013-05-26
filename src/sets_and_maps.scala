// Scala provides a base trait for sets, then two subtraits,
// one for mutable sets and another for immutable sets.
// They all share the same simple name, Set, but their fully qualified names differ,
// as each trait resides in a different package. Concrete set classes in the API,
// such as the HashSet classes, extend either the mutable or immutable Set trait.
// Thus, if you want to use a HashSet, you can choose between mutable and immutable
// varieties depending on your needs. The default is an immutable set:
var jetSet = Set("Boeing", "Airbus")
// Adding an element to an immutable set creates and returns a new set with the element added
// Although mutable sets offer an actual += method, immutable sets do not. In this case, the
// following line is essentially a shorthand for jetSet = jetSet + "Lear"
jetSet += "Lear"
println(jetSet.contains("Cessna"))

// If you want a mutable set, you'll need to use an import:

import scala.collection.mutable.Set

// I have to use the fully qualified name because I'm using
// both mutable and immutable sets in the same file.
val movieSet = scala.collection.mutable.Set("Hitch", "Poltergeist")
movieSet += "Shrek"
println(movieSet)

// If you need a particular implementation of set, for example an immutable HashSet:
import scala.collection.immutable.HashSet

val hashSet = HashSet("Tomatoes", "Chilies")
println(hashSet + "Coriander")

// Some mutable Maps in action:
import scala.collection.mutable.Map

// without any parameters passed to the factory method, you're required to specify the map type,
// in this case, Map[Int, String]
val treasureMap = Map[Int, String]()
treasureMap += (1 -> "Go to island.")
treasureMap += (2 -> "Find big X on ground.")
treasureMap += (1 -> "Dig.")
println(treasureMap(2))

// Immutable maps require no import:
// romanNumeral's type was inferred to also be Map[Int, String]
val romanNumeral = Map(
  1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V"
)
println(romanNumeral(4))
