// There are also a number of methods in the globally accessible object scala.List, which is the
// companion object of class List. Some of these operations are factory methods that create lists.
// Others are operations that work on lists of some specific shape. Both kinds of methods will be
// presented in this example file.

// You've already seen on several occasions list literals such as List(1, 2, 3).
// There’s nothing special about their syntax. A literal like List(1, 2, 3) is simply
// the application of the object List to the elements 1, 2, 3. That is, it is equivalent
// to List.apply(1, 2, 3):
List.apply(1, 2, 3)
// returns: List[Int] = List(1, 2, 3)

// The range method creates a list consisting of a range of numbers. Its simplest form
// is List.range(from, until), which creates a list of all numbers starting at from and going
// up to until minus one. So the end value, until, does not form part of the range.
// There's also a version of range that takes a step value as third parameter.
// This operation will yield list elements that are step values apart, starting at from.
// The step can be positive or negative:
List.range(1, 5)
// returns: List[Int] = List(1, 2, 3, 4)
List.range(1, 9, 2)
// returns: List[Int] = List(1, 3, 5, 7)
List.range(9, 1, -3)
// returns: List[Int] = List(9, 6, 3)

// The fill method creates a list consisting of zero or more copies of the same element.
// It takes two parameters: the length of the list to be created, and the element to be repeated.
// Each parameter is given in a separate list:
List.fill(5)('a')
// returns: List[Char] = List(a, a, a, a, a)

List.fill(3)("hello")
// returns: List[String] = List(hello, hello, hello)

// If fill is given more than two arguments, then it will make multidimensional lists.
// That is, it will make lists of lists, lists of lists of lists, etc.
// The additional arguments go in the first argument list.
List.fill(2, 3)('b')
// returns: List[List[Char]] = List(List(b, b, b), List(b, b, b))

// The tabulate method creates a list whose elements are computed according to a supplied function.
// Its arguments are just like those of List.fill: the first argument list gives the dimensions
// of the list to create, and the second describes the elements of the list. The only difference
// is that instead of the elements being fixed, they are computed from a function:
val squares = List.tabulate(5)(n => n * n)
// returns: squares: List[Int] = List(0, 1, 4, 9, 16)

val multiplication = List.tabulate(5,5)(_ * _)
// returns: multiplication: List[List[Int]] = List(List(0, 0, 0, 0, 0),
// List(0, 1, 2, 3, 4), List(0, 2, 4, 6, 8), List(0, 3, 6, 9, 12),
// List(0, 4, 8, 12, 16))

// The concat method concatenates a number of element lists. The lists to be concatenated are
// supplied as direct arguments to concat:
List.concat(List('a', 'b'), List('c'))
// returns: List[Char] = List(a, b, c)

List.concat(List(), List('b'), List('c'))
// returns : List[Char] = List(b, c)

List.concat()
// returns: List[Nothing] = List()
