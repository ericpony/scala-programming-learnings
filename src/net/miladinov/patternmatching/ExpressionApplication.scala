package net.miladinov.patternmatching

class ExpressionApplication extends App {
  val f = new ExpressionFormatter

  val expression1 = BinaryOperator(
    "*",
    BinaryOperator("/", Number(1), Number(2)),
    BinaryOperator("+", Variable("x"), Number(1))
  )

  val expression2 = BinaryOperator(
    "+",
    BinaryOperator("/", Variable("x"), Number(2)),
    BinaryOperator("/", Number(1.5), Variable("x"))
  )

  val expression3 = BinaryOperator("/", expression1, expression2)

  def show(e: Expression) = println(f.format(e) + "\n\n")

  for (e <- Array(expression1, expression2, expression3)) show(e)
}
