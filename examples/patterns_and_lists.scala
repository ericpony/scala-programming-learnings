// Start with a list of strings
val fruit: List[String] = List("apples", "oranges", "pears")

// List destructuring is a pattern match operation
val List(a, b, c) = fruit
// a: Strings = "apples"
// b: Strings = "oranges"
// c: Strings = "pears"

// The above pattern only matches on lists of exactly size 3.
// If you don't know the size beforehand, then use the following instead:
val d :: e :: rest = fruit
// d: String = apples
// e: String = oranges
// rest: List[String] = List(pears)



