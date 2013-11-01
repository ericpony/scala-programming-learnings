// Any time you define a val or a var,  you can use a pattern
// instead of a simple identifier. For example, you can use this
// to take apart a tuple and assign each of its parts
// to its own variable, as shown here:
val myTuple = (123, "abc")
// myTuple: (Int, java.lang.String) = (123,abc)

val (number, string) = myTuple
// number: Int = 123
// string: java.lang.String = abc

// This construct is quite useful when working with case classes.
// If you know the precise case class you are working with,
// then you can deconstruct it with a pattern. Here's an example:
import net.miladinov.patternmatching.{BinaryOperator, Number}

val expression = new BinaryOperator("*", Number(5), Number(1))
// exp: BinaryOperator = BinaryOperator(*,Number(5.0),Number(1.0))

val BinaryOperator(op, left, right) = expression
// op: String = *
// left: Expr = Number(5.0)
// right: Expr = Number(1.0)

