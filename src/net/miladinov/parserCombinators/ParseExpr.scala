package net.miladinov.parserCombinators

// You can exercise your parser with the following small program:
object ParseExpr extends Arithmetic {
  def main(args: Array[String]) {
    println("input : " + args(0))
    println(parseAll(expr, args(0)))
  }
}

// The ParseExpr object defines a main method that parses the first commandline argument passed to it. It prints the
// original input arguments, and then prints its parsed version. Parsing is done by the expression:
//
// parseAll(expr, input)
//
// This expression applies the parse, expr, to the given input. It expects that all of the input matches, i.e., that
// there are no characters trailing a parsed expression. There's also a method parse, which allows you to parse an
// input prefix, leaving some remainder unread.
//
// If you run the arithmetic parser with the following command:
//
// $ scala ParseExpr "2 * (3 + 7)"
//
// You'll get something like the following output:
//
// input :  2 * (3 + 7)
// [1.13] parsed: ((2~List((*~(((~((3~List())~List((+~(7~List())))))~)))))~List())
//
// The output tells you that the parser successfully analyzed the input string up to position [1.13]. That means the
// first line and the thirteenth column — in other words, the whole input string — was parsed. Disregard for the moment
// the result after "parsed:". It is not very useful, and you will find out later how to get more specific parser results.
//
// You can also try to introduce some input string that is not a legal expression. For instance, you could write one
// closing parenthesis too many:
//
// $ scala ParseExpr "2 * (3 + 7))"
// input :  2 * (3 + 7))
// [1.13] failure: string matching regex `\z' expected but `)' found
//
// 2 * (3 + 7))
//            ^
//
// Here, the expr parser parsed everything until the final closing parenthesis, which does not form part of the
// arithmetic expression. The parseAll method then issued an error message, which said that it failed a regex match.
// You'll find out later why it produced this particular error message, and how you can improve it.
