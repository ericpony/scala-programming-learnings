// Any time you define a val or a var,  you can use a pattern
// instead of a simple identifier. For example, you can use this
// to take apart a tuple and assign each of its parts
// to its own variable, as shown here:
val myTuple = (123, "abc")
// myTuple: (Int, java.lang.String) = (123,abc)

val (number, string) = myTuple
// number: Int = 123
// string: java.lang.String = abc
