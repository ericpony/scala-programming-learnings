package net.miladinov.forExpressions

// We have previously shown that for expressions can be translated into applications
// of the higher-order functions map, flatMap, and withFilter. In fact, you could
// equally well go the other way: every application of a map, flatMap, or filter can
// be represented as a for expression. Here are implementations of the three methods
// in terms of for expressions. The methods are contained in an object Demo, to
// distinguish them from the standard operations on Lists. To be concrete, the three
// functions all take a List as parameter, but the translation scheme would work
// just as well with other collection types:

object Demo {
  def map [A, B] (xs: List[A], f: A => B): List[B] =
    for (x <- xs) yield f(x)

  def flatMap [A, B] (xs: List[A], f: A => List[B]): List[B] =
    for (x <- xs; y <- f(x)) yield y

  def filter [A] (xs: List[A], p: A => Boolean): List[A] =
    for (x <- xs; if p(x)) yield x
}

// Not surprisingly, the translation of the for expression used in the body of
// Demo.map will produce a call to map in class List. Similarly, Demo.flatMap and
// Demo.filter translate to flatMap and withFilter in class List.
// So this little demonstration has shown that for expressions really are equivalent
// in their expressiveness to applications of the three functions map, flatMap,
// and withFilter.
