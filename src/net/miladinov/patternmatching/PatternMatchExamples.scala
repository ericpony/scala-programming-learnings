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

  def thisPatternNowCausesMoreWarnings() = {
    import math.E
    val pi = math.Pi

    E match {
      // Warning: patterns after a variable pattern cannot match (SLS 8.1.1)
      case pi => "strange math? Pi = " + pi
      // Warning: unreachable code due to variable pattern 'pi' on line 50
      case _ => "OK"
    }
  }

  def ifYouInsistOnLowerCaseConstantPatternsYouCanUseBackticks() = {
    import math.E
    val pi = math.Pi

    E match {
      case `pi` => "strange math? Pi = " + pi
      case _ => "OK"
    }
  }

  def constructorPatternsForDeepMatches(expr: Expression) = {
    expr match {
      case BinaryOperator("+", e, Number(0)) => println("a deep match")
      case _ =>
    }
  }

  def sequencePatternsLetYouSpecifyAnyNumberOfPatternElements(expr: Any) = {
    expr match {
      case List(0, _, _) => println("Found it!")
      case _ =>
    }
  }

  def sequencePatternsWithAnUnspecifiedNumberOfPatternElements(expr: Any) = {
    expr match {
      case List(0, _*) => println("Found it!")
      case _ =>
    }
  }

  def tuplePatterns(expr: Any) = {
    expr match {
      case (a, b, c) => println("matched " + a + b + c)
      case _ =>
    }
  }

  def generalSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size
    case _ => -1
  }

  def generalSizeWithoutPatternMatching(x: Any) = {
    if (x.isInstanceOf[String]) {
      val s = x.asInstanceOf[String]
      s.length
    } else if (x.isInstanceOf[Map[_, _]]) {
      val m = x.asInstanceOf[Map[_, _]]
      m.size
    } else {
      -1
    }
  }

  def isAnIntMap(x: Any) = x match {
    case m: Map[Int, Int] => true
    case _ => false
  }

  def isAStringArray(x: Any) = x match {
    case a: Array[String] => "yes"
    case _ => "no"
  }

  def variableBindingPattern(expr: Any) = {
    expr match {
      case UnaryOperator("abs", e @ UnaryOperator("abs", _)) => e
      case _ =>
    }
  }

  def simplifyAdd(e: Expression) = e match {
    case BinaryOperator("+", x, y) if x == y => BinaryOperator("*", x, Number(2))
    case _ => e
  }

  def incrementPositiveIntegers(x: Any) = x match {
    case n: Int if n > 0 => n + 1
    case _ =>
  }

  def onlyStringsThatStartWithTheLetterA(x: Any) = x match {
    case s: String if s(0) == 'a' => s + " starts with a"
    case _ => x
  }
}
