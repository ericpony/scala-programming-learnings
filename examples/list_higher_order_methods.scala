// The operation xs map f takes as operands a list xs of type List[T] and
// a function f of type T => U. It returns the list resulting from applying the function
// f to each list element in xs. For instance:
List(1, 2, 3) map (_ + 1)
// returns: List[Int] = List(2, 3, 4)

val words = List("the", "quick", "brown", "fox")
// returns: words: List[String] = List(the, quick, brown, fox)

words map (_.length)
// returns: List[Int] = List(3, 5, 5, 3)

words map (_.reverse.mkString)
// returns: List[String] = List(eht, kciuq, nworb, xof)

// The flatMap operator is similar to map, but it takes a function returning
// a list of elements as its right operand. It applies the function to each list element
// and returns the concatenation of all function results.
// The difference between map and flatMap is illustrated in the following example:
words map (_.toList)
// returns: List[List[Char]] = List(List(t, h, e), List(q, u, i, c, k), List(b, r, o, w, n), List(f, o, x))

words flatMap (_.toList)
// returns: List[Char] = List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x)

// You see that where map returns a list of lists, flatMap returns a single list in which
// all element lists are concatenated. The differences and interplay between map and flatMap
// are also demonstrated by the following expression, which constructs a list
// of all pairs (i, j) such that 1 ≤ j < i < 5:
List.range(1, 5) flatMap (
  i => List.range(1, i) map (j => (i, j))
)

// List.range is a utility method that creates a list of all integers in some range.
// It is used twice in this example: once to generate a list of integers from 1 (including)
// until 5 (excluding), and in a second time to generate a list of integers from 1 until i,
// for each value of i taken from the first list. The map in this expression generates a list
// of tuples (i, j) where j < i. The outer flatMap in this example generates this list
// for each i between 1 and 5, and then concatenates all the results. Note that the same list
// can alternatively be constructed with a for expression:
for (i <- List.range(1, 5); j <- List.range(1, i)) yield (i, j)
