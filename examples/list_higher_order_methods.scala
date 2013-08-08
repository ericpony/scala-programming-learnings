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

// The takeWhile and dropWhile operators also take a predicate as their right operand.
// The operation xs takeWhile p takes the longest prefix of list xs such that every element
// in the prefix satisfies p. Analogously, the operation xs dropWhile p removes the
// longest prefix from list xs such that every element in the prefix satisfies p.
// Here are some examples:
List(1, 2, 3, -4, 5) takeWhile (_ > 0)
// returns: List[Int] = List(1, 2, 3)

words dropWhile (_ startsWith "t")
// returns: List[String] = List(quick, brown, fox)

// The span method combines takeWhile and dropWhile in one operation, just like splitAt
// combines take and drop. It returns a pair of two lists, defined by the equality:
//
// xs span p equals (xs takeWhile p, xs dropWhile p)
//
// Like splitAt, span avoids traversing the list xs twice:
List(1, 2, 3, -4, 5) span (_ > 0)
// returns: (List[Int], List[Int]) = (List(1, 2, 3), List(-4, 5))

// The operation xs forall p takes as arguments a list xs and a predicate p.
// Its result is true if all elements in the list satisfy p. Conversely, the operation
// xs exists p returns true if there is an element in xs that satisfies the predicate p.
// For instance, to find out whether a matrix represented as a list of lists has
// a row with only zeroes as elements:
val diagonalZeroes = List(
  List(0, 1, 1),
  List(1, 0, 1),
  List(1, 1, 0)
)

val horizontalZeroes = List(
  List(1, 1, 1),
  List(0, 0, 0),
  List(1, 1, 1)
)

def hasZeroRow(matrix: List[List[Int]]) = matrix exists (row => row forall (_ == 0))

hasZeroRow(diagonalZeroes)
// returns: Boolean = false

hasZeroRow(horizontalZeroes)
// returns: Boolean = true

// Another common kind of operation combines the elements of a list with some operator.
// For instance:
//
// sum(List(a, b, c)) equals 0 + a + b + c
//
// This is a special instance of a fold operation:
def sum(xs: List[Int]): Int = (0 /: xs) (_ + _)
// returns: sum: (xs: List[Int])Int

// Similarly:
//
// product(List(a, b, c)) equals 1 * a * b * c
//
// is a special instance of this fold operation:
def product(xs: List[Int]): Int = (1 /: xs) (_ * _)
// returns: product: (xs: List[Int])Int

// A fold left operation "(z /: xs) (op)" involves three objects: a start value z,
// a list xs, and a binary operation op. The result of the fold is op applied between
// successive elements of the list prefixed by z. For instance:
//
// (z /: List(a, b, c)) (op) equals op(op(op(z, a), b), c)

// Here’s another example that illustrates how /: is used. To concatenate all words in a
// list of strings with spaces between them and in front, you can write this:
("" /: words) (_ + " " + _)
// returns: String =  " the quick brown fox"

// This gives an extra space at the beginning. To remove the space, you can use this
// slight variation:
(words.head /: words.tail)  (_ + " " + _)
// returns: String = "the quick brown fox"

// The /: operator produces left-leaning operation trees (its syntax with the slash
// rising forward is intended to be a reflection of that). The operator has :\ as an
// analog that produces right-leaning trees. For instance:
//
// (List(a, b, c) :\ z) (op) equals op(a, op(b, op(c, z)))
//
// The :\ operator is pronounced fold right. It involves the same three operands as fold
// left, but the first two appear in reversed order: The first operand is the list to fold,
// the second is the start value. For associative operations, fold left and fold right are
// equivalent, but there might be a difference in efficiency. Consider for instance
// an operation corresponding to the flatten method, which concatenates all elements
// in a list of lists. This could be implemented with either fold left or fold right:
def flattenLeft[T](xss: List[List[T]]) =
  (List[T]() /: xss) (_ ::: _)

def flattenRight[T](xss: List[List[T]]) =
  (xss :\ List[T]()) (_ ::: _)

// Because list concatenation, xs ::: ys, takes time proportional to its first argument xs,
// the implementation in terms of fold right in flattenRight is more efficient than the
// fold left implementation in flattenLeft. The problem is that flattenLeft(xss) copies
// the first element list xss.head n − 1 times, where n is the length of the list xss.

// Note that both versions of flatten require a type annotation on the empty list that is
// the start value of the fold. This is due to a limitation in Scala’s type inferencer,
// which fails to infer the correct type of the list automatically. If you try to leave
// out the annotation, you get the following:
// def flattenRight[T](xss: List[List[T]]) =
//   (xss :\ List()) (_ ::: _)
// <console>:8: error: type mismatch;
//   found   : List[T]
//   required: List[Nothing]
//   (xss :\ List()) (_ ::: _)
//                      ^
//
// Lastly, although the /: and :\ operators have the advantage that the direction of the
// slash resembles the graphical depiction of their respective left or right-leaning trees,
// and the associativity of the colon character places the start value in the same position
// in the expression as it is in the tree, some may find the resulting code less than
// intuitive. If you prefer, you can alternatively use the methods named foldLeft and
// foldRight, which are also defined on class List.

def reverseLeft[T](xs: List[T]) = (List[T]() /: xs) { (ys, y) => y :: ys }

// The operation xs sortWith before, where "xs" is a list and "before" is a function that
// can be used to compare two elements, sorts the elements of list xs. The expression
// x before y should return true if x should come before y in the intended ordering for
// the sort. For instance:
List(1, -3, 4, 2, 6) sortWith (_ < _)
// returns: List[Int] = List(-3, 1, 2, 4, 6)

words sortWith (_.length > _.length)
// returns: List[java.lang.String] = List(quick, brown, the, fox)

// Note that sortWith performs a merge sort similar to the merge_sort algorithm shown in
// merge_sort.scala, but sortWith is a method of class List whereas merge_sort was defined
// outside lists.
