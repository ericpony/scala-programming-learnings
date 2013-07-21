package net.miladinov.patternmatching

import _root_.Element
import _root_.Element.elem

class ExpressionFormatter {
  // Contains operators in groups of increasing precedence
  private val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("ˆ"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
    )

  // A mapping from operators to their precedence
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i)
      } yield op -> i
    assocs.toMap
  }

  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

  private def format(e: Expression, enclosingPrecedence: Int): Element = e match {
      case Variable(name) => elem(name)

      case Number(num) =>
        def stripDot(s: String) =
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s
        elem(stripDot(num.toString))

      case UnaryOperator(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)

      case BinaryOperator("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val fraction = top above line above bot
        if (enclosingPrecedence != fractionPrecedence) fraction
        else elem(" ") beside fraction beside elem(" ")

      case BinaryOperator(op, left, right) =>
        val operatorPrecedence = precedence(op)
        val l = format(left, operatorPrecedence)
        val r = format(right, operatorPrecedence + 1)
        val operator = l beside elem(" "+ op +" ") beside r
        if (enclosingPrecedence <= operatorPrecedence) operator
        else elem("(") beside operator beside elem(")")
    }
  def format(e: Expression): Element = format(e, 0)
}
