package net.miladinov.patternmatching

object Expression {
  def simplifyTop(expr: Expression): Expression = expr match {
    case UnaryOperator("-", UnaryOperator("-", e))  => e // Double negation
    case BinaryOperator("+", e, Number(0)) => e          // Adding zero
    case BinaryOperator("*", e, Number(1)) => e          // Multiplying by one
    case _ => expr
  }

  def simplifyAll(expr: Expression):Expression = expr match {
    case UnaryOperator("-", UnaryOperator("-", e)) => simplifyAll(e)  // "-" is its own inverse
    case BinaryOperator("+", e, Number(0)) => simplifyAll(e)          // 0 is a neutral element for "+"
    case BinaryOperator("*", e, Number(1)) => simplifyAll(e)          // 1 is a neutral element for "*"
    case UnaryOperator(op, e) => UnaryOperator(op, simplifyAll(e))
    case BinaryOperator(op, l, r) => BinaryOperator(op, simplifyAll(l), simplifyAll(r))
    case _ => expr
  }

  def describe(expr: Expression): String = (expr: @unchecked) match {
    case Number(_) => " a Number"
    case Variable(_) => " a Variable"
  }
}

sealed abstract class Expression
case class Variable(name: String) extends Expression
case class Number(number: Double) extends Expression
case class UnaryOperator(operator: String, arg: Expression) extends Expression
case class BinaryOperator(operator: String, left: Expression, right: Expression) extends Expression
