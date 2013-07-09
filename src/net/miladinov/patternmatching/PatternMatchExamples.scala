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

  def constantPattern(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

  def variablePattern(expr: Any) = {
    expr match {
      case 0 => "zero"
      case somethingElse => "not zero: " + somethingElse
    }
  }

  def variablePatternOrConstantPattern_?() = {
    import math.{E, Pi}

    // will yield "OK", because E does not match PI
    E match {
      case Pi => "strange math ? Pi = " + Pi
      case _ => "OK"
    }
  }
}
