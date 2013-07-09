package net.miladinov.patternmatching

object PatternMatchExamples {
  def wildcardPattern(expr: Expression) = {
    expr match {
      case BinaryOperator(op, left, right) =>
        println(expr +" is a binary operation")
      case _ =>
    }
  }

  def embeddedWildCardPattern(expr: Expression) = {
    expr match {
      case BinaryOperator(_, _, _) => println(expr + " is a binary operation")
      case _ => println("It's something else")
    }
  }
}
