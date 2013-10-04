// The typical access pattern for a list is recursive. For instance, to increment
// every element of a list without using map you could write:

def incrementAllElementsOf(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case x :: xs1 => x + 1 :: incrementAllElementsOf(xs1)
}

// Unfortunately, it's not tail-recursive.

