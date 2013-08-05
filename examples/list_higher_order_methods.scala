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

// Another map-like operation is foreach. Unlike map and flatMap, however, foreach
// takes a procedure (a function with result type Unit) as right operand. It simply applies
// the procedure to each list element. The result of the operation itself is again Unit;
// no list of results is assembled. As an example, here is a concise way of summing up all
// numbers in a list:
var sum = 0
// returns: sum: Int = 0
List(1, 2, 3, 4, 5) foreach (sum += _)

sum
// returns: Int = 15

// The operation "xs filter p" takes as operands a list xs of type List[T] and
// a predicate function p of type T => Boolean. It yields the list of all elements
// x in xs for which p(x) is true. For instance:
List(1, 2, 3, 4, 5) filter (_ % 2 == 0)
// returns: List[Int] = List(2, 4)

words filter (_.length == 3)
// returns: List[String] = List(the, fox)

// The partition method is like filter, but it returns a pair of lists.
// One list contains all elements for which the predicate is true, while the other list
// contains all elements for which the predicate is false. It is defined by the equality:
//
//  xs partition p equals (xs filter p, xs filter (!p(_)))
//
// Here's an example:
List(1, 2, 3, 4, 5) partition (_ % 2 == 0)
// returns: (List[Int], List[Int]) = (List(2, 4),List(1, 3, 5))

// The find method is also similar to filter but it returns the first element satisfying
// a given predicate, rather than all such elements. The operation xs find p takes a list
// xs and a predicate p as operands. It returns an optional value. If there is an element
// x in xs for which p(x) is true, Some(x) is returned. Otherwise, p is false
// for all elements, and None is returned. Here are some examples:
List(1, 2, 3, 4, 5) find (_ % 2 == 0)
// returns: Option[Int] = Some(2)

List(1, 2, 3, 4, 5) find (_ <= 0)
// returns: Option[Int] = None
