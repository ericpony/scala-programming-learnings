abstract class Expression
  case class Variable(name: String) extends Expression
  case class Number(number: Double) extends Expression
  case class UnaryOperator(operator: String, arg: Expression) extends Expression
  case class BinaryOperator(operator: String, left: Expression, right: Expression) extends Expression
