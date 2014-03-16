package net.miladinov.parserCombinators

// We’ll start with an example. Say you want to construct a parser for arithmetic expressions consisting of
// floating-point numbers, parentheses, and the binary operators +, -, *, and /. The first step is always to
// write down a grammar for the language to be parsed. Here’s the grammar for arithmetic expressions:
//
// expr ::= term {"+" term | "-" term}.
// term ::= factor {"*" factor | "/" factor}.
// factor ::= floatingPointNumber | "(" expr ")".
//
// Here, | denotes alternative productions, and { . . . } denotes repetition (zero or more times). And although
// there's no use of it in this example, [ . . . ] denotes an optional occurrence.
// This context-free grammar defines formally a language of arithmetic expressions.
// Every expression (represented by expr) is a term, which can be followed by a sequence of + or - operators and
// further terms. A term is a factor, possibly followed by a sequence of * or / operators and further factors.
// A factor is either a numeric literal or an expression in parentheses. Note that the grammar already encodes
// the relative precedence of operators.
//
// For instance, * binds more tightly than +, because a * operation gives a term, whereas a + operation gives an expr,
// and exprs can contain terms but a term can contain an expr only when the latter is enclosed in parentheses.
// Now that we have defined the grammar, what’s next? If you use Scala's combinator parsers, you are basically done!
// You only need to perform some systematic text replacements and wrap the parser in a class, as shown here:

import scala.util.parsing.combinator.JavaTokenParsers

class Arithmetic extends JavaTokenParsers {
  def expr: Parser[Any] = term~rep("+"~term | "-"~term)
  def term: Parser[Any] = factor~rep("*"~factor | "/"~factor)
  def factor: Parser[Any] = floatingPointNumber | "("~expr~")"
}

// The parsers for arithmetic expressions are contained in a class that inherits from the trait JavaTokenParsers.
// This trait provides the basic machinery for writing a parser and also provides some primitive parsers that
// recognize some word classes: identifiers, string literals and numbers. In the example above you need only the
// primitive floatingPointNumber parser, which is inherited from this trait.

// The three definitions in class Arithmetic represent the productions for arithmetic expressions. As you can see,
// they follow very closely the productions of the context-free grammar. In fact, you could generate this part
// automatically from the context-free grammar, by performing a number of simple text replacements:

// 1. Every production becomes a method, so you need to prefix it with def.
// 2. The result type of each method is Parser[Any], so you need to change the ::= symbol to ": Parser[Any] =".
//    You’ll find out later what the type Parser[Any] signifies, and also how to make it more precise.
// 3. In the grammar, sequential composition was implicit, but in the program it is expressed by an explicit
//    operator: ~. So you need to insert a ~ between every two consecutive symbols of a production.
//    In the example above we chose not to write any spaces around the ~ operator. That way, the parser code keeps
//    closely to the visual appearance of the grammar — it just replaces spaces by ~ characters.
// 4. Repetition is expressed rep( . . . ) instead of { . . . }. Analogously (though not shown in the example),
//    option is expressed opt( ... ) instead of [ ... ].
// 5. The period at the end of each production is omitted — you can, however, write a semicolon if you prefer.

// That's all there is to it. The resulting class Arithmetic defines three parsers, expr, term and factor,
// which can be used to parse arithmetic expressions and their parts.
