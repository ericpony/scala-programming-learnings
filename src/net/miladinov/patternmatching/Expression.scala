package net.miladinov.patternmatching

abstract class Expression {
  def simplifyTop(expr: Expression): Expression = expr match {
    case UnaryOperator("-", UnaryOperator("-", e))  => e // Double negation
    case BinaryOperator("+", e, Number(0)) => e          // Adding zero
    case BinaryOperator("*", e, Number(1)) => e          // Multiplying by one
    case _ => expr
  }
}

case class Variable(name: String) extends Expression
case class Number(number: Double) extends Expression
case class UnaryOperator(operator: String, arg: Expression) extends Expression
case class BinaryOperator(operator: String, left: Expression, right: Expression) extends Expression
