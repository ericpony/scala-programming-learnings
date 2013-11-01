// Suppose we have a list we want to sort with merge_sort:
val abcde = List('c', 'a', 'e', 'd', 'b')

def merge_sort [T] (less: (T, T) => Boolean) (xs: List[T]): List[T] = {
  def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
    case (Nil, _) => ys
    case (_, Nil) => xs
    case (x :: xs1, y :: ys1) =>
      if (less(x, y)) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
  }

  val midpoint = xs.length / 2

  if (midpoint == 0) xs
  else {
    val (ys, zs) = xs splitAt midpoint
    merge(merge_sort(less)(ys), merge_sort(less)(zs))
  }
}

// The rules of type inference won't let us write something like this:
merge_sort(_ > _)(abcde)
// The type inferencer can't help here:
// Type mismatch, expected: (NotInferedT, NotInferedT) => Boolean, actual: (Nothing, Nothing) => Any

// Instead, we have to write it with the types filled in:
merge_sort((x: Char, y: Char) => x > y)(abcde)

// Or, we could pass in an explicit type parameter to merge_sort:
merge_sort[Char](_ > _)(abcde)

// Or, we could define merge_sort with its parameter lists swapped out:
def merge_sort_swapped [T] (xs: List[T]) (less: (T, T) => Boolean): List[T] = {
  def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
    case (Nil, _) => ys
    case (_, Nil) => xs
    case (x :: xs1, y :: ys1) =>
      if (less(x, y)) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
  }

  val midpoint = xs.length / 2

  if (midpoint == 0) xs
  else {
    val (ys, zs) = xs splitAt midpoint
    merge(merge_sort_swapped(ys)(less), merge_sort_swapped(zs)(less))
  }
}

// This lets us then take advantage of type inference, since we can infer the type of less'
// function parameters as they flow in from xs:
merge_sort_swapped(abcde)(_ > _)
// works, the underscores correctly get type inferred

// What has happened is that the inferencer used the known type of the first parameter abcde to
// determine the type parameter of merge_sort_swapped. Once the precise type of merge_sort_swapped
// was known, it could be used in turn to infer the type of the second parameter, (_ > _).

// Generally, when tasked to infer the type parameters of a polymorphic method, the type inferencer
// consults the types of all value arguments in the first parameter list but no arguments
// beyond that. Since merge_sort_swapped is a curried method with two parameter lists,
// the second argument (i.e., the function value) did not need to be consulted to determine
// the type parameter of the method.

// This inference scheme suggests the following library design principle: When designing
// a polymorphic method that takes some non-function arguments and a function argument,
// place the function argument last in a curried parameter list by its own.
// That way, the method's correct instance type can be inferred from the non-function arguments,
// and that type can in turn be used to type check the function argument. The net effect is that
// users of the method will be able to give less type information and write function literals in
// more compact ways.
