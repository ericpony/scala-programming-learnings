// Earlier we demonstrated that higher-order functions such as map, flatMap,
// and filter provide powerful constructions for dealing with lists. But
// sometimes the level of abstraction required by these functions makes
// a program a bit hard to understand. Here’s an example. Say you are given
// a list of persons, each defined as an instance of a class Person.
// Class Person has fields indicating the person’s name, whether (s)he is male,
// and his/her children. Here’s the class definition:

// case class Person(name: String, isMale: Boolean, children: Person*)

// Here's a list of some Persons:
val lara = Person("Lara", isMale = false)
val bob = Person("Bob", isMale = true)
val julie = Person("Julie", false, lara, bob)
val persons = List(lara, bob, julie)

// Now, say you want to find out the names of all pairs of mothers and their
// children in that list. Using map, flatMap and filter, you can formulate
// the following query:

persons filter (parent => !parent.isMale) flatMap (parent =>
  parent.children map (children => (parent.name, children.name)))

// We could optimize this example a bit by using a withFilter call instead of
// filter. This would avoid the creation of an intermediate data structure
// for male persons:

persons withFilter (parent => !parent.isMale) flatMap (parent =>
  parent.children map (children => (parent.name, children.name)))
