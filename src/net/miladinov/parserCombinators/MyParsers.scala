package net.miladinov.parserCombinators

import scala.util.parsing.combinator.RegexParsers

// Arithmetic, the parser for arithmetic expressions made use of another parser, named floatingPointNumber.
// This parser, which was inherited from Arithmetic's supertrait, JavaTokenParsers, recognizes a floating point number
// in the format of Java. But what do you do if you need to parse numbers in a format that’s a bit different from
// Java's? In this situation, you can use a regular expression parser.

// The idea is that you can use any regular expression as a parser. The regular expression parses all strings that it
// can match. Its result is the parsed string. For instance, the regular expression parser shown here describes Java's
// identifiers:

object MyParsers extends RegexParsers {
  val ident: Parser[String] = """[a-zA-Z_]\w*""".r
}

// This object inherits from the trait RegexParsers, whereas Arithmetic inherited from JavaTokenParsers. Scala’s parsing
// combinators are arranged in a hierarchy of traits, which are all contained in package scala.util.parsing.combinator.
// The top-level trait is Parsers, which defines a very general parsing framework for all sorts of input.
// One level below is trait RegexParsers, which requires that the input is a sequence of characters and provides for
// regular expression parsing. Even more specialized is the trait JavaTokenParsers, which implements parsers for basic
// classes of words (or tokens) as they are defined in Java.
