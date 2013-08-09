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
