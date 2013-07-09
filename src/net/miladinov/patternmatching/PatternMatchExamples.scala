package net.miladinov.patternmatching

object PatternMatchExamples {
  def wildcardPattern(expr: Expression) = {
    expr match {
      case BinaryOperator(op, left, right) =>
        println(expr +" is a binary operation")
      case _ =>
    }
  }
}
