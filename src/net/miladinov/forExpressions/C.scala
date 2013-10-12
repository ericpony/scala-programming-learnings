package net.miladinov.forExpressions

/**
 * Besides lists and arrays, there are also many other types in the Scala standard
 * library that support the same four methods and therefore allow for expressions.
 * Examples are ranges, iterators, streams, and all implementations of sets. It's
 * also perfectly possible for your own data types to support for expressions by
 * defining the necessary methods. To support the full range of for expressions and
 * for loops, you need to define map, flatMap, withFilter, and foreach as methods of
 * your data type. But it's also possible to define a subset of these methods, and
 * thereby support a subset of all possible for expressions or loops. Here are the
 * precise rules:
 *
 * * If your type defines just map, it allows for expressions consisting of
 *   a single generator.
 *
 * * If it defines flatMap as well as map, it allows for expressions consisting of
 *   several generators.
 *
 * * If it defines foreach, it allows for loops (both with single and multiple
 *   generators).
 *
 * * If it defines withFilter, it allows for filter expressions starting with an if
 *   in the for expression.
 *
 * The translation of for expressions happens before type checking. This allows for
 * maximal flexibility, because it is only required that the result of expanding a
 * for expression type checks. Scala defines no typing rules for the for expressions
 * themselves, and does not require that methods map, flatMap, withFilter,
 * or foreach to have any particular type signatures.
 *
 * Nevertheless, there is a typical setup that captures the most common intention of
 * the higher order methods to which for expressions translate. Say you have a
 * parameterized class, C, which typically would stand for some sort of collection.
 * Then it's quite natural to pick the following type signatures for map, flatMap,
 * withFilter, and foreach:
 *
 * @tparam A
 */
abstract class C[A] {
  def map [B] (f: A => B): C[B]
  def flatMap [B] (f: A => C[B]): C[B]
  def withFilter (p: A => Boolean): C[A]
  def foreach (b: A => Unit): Unit
}

// That is, the map function takes a function from the collection's element type A
// to some other type B. It produces a new collection of the same kind C, but with B
// as the element type. The flatMap method takes a function f from A to some
// C-collection of Bs and produces a C-collection of Bs. The withFilter method takes
// a predicate function from the collection's element type A to Boolean. It produces
// a collection of the same type as the one on which it is invoked. Finally, the
// foreach method takes a function from A to Unit, and produces a Unit result.
