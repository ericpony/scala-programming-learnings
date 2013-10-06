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

// These queries do their job, but they are not exactly trivial to write or
// understand. Is there a simpler way? In fact, there is. Remember the for
// expressions from before? Using a for expression, the same example can be
// written as follows:

for (parent <- persons; if !parent.isMale; child <- parent.children)
  yield (parent.name, child.name)

// Generally, a for expression is of the form:
// for ( seq ) yield expr
// Here, seq is a sequence of generators, definitions, and filters, with
// semicolons between successive elements. An example is the for expression:

for (p <- persons; n = p.name; if n startsWith "To")
  yield n

// This for expression contains one generator, one definition, and one filter.
// As mentioned before, you can also enclose the sequence in braces instead of
// parentheses. Then the semicolons become optional:

for {
  p <- persons          // a generator
  n = p.name            // a definition
  if n startsWith "To"  // a filter
} yield n

// A generator is of the form:
//
// pat <- expr
//
//  The expression expr typically returns a list, even though you will see
// later that this can be generalized. The pattern pat gets matched
// one-by-one against all elements of that list. If the match succeeds, the
// variables in the pattern get bound to the corresponding parts of the
// element, just the way it is described in Chapter 15. But if the match
// fails, no MatchError is thrown. Instead, the element is simply discarded
// from the iteration.
// In the most common case, the pattern pat is just a variable x, as in
// x <- expr. In that case, the variable x simply iterates over all elements
// returned by expr.
