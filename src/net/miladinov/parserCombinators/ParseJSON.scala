package net.miladinov.parserCombinators

// JSON, the JavaScript Object Notation, is a popular data interchange format. Let's write a parser for it.

// Here's a grammar that describes the syntax of JSON:
// value ::= obj | arr | stringLiteral | floatingPointNumber | "null" | "true" | "false".
// obj ::= "{" [ members ] "}".
// arr ::= "[" [ values ] "]".
// members ::= member {"," member}.
// member ::= stringLiteral ":" value.
// values ::= value {"," value}.

// A JSON value is an object, array, string, number, or one of the three reserved words null, true, or false.
// A JSON object is a (possibly empty) sequence of members separated by commas and enclosed in braces. Each member
// is a string/value pair where the string and the value are separated by a colon. Finally, a JSON array is a sequence
// of values separated by commas and enclosed in square brackets. As an example, resources/address_book.json contains
// an address-book formatted as a JSON object.

// Parsing such data is straightforward when using Scala's parser combinators. The complete parser is shown below.
// This parser follows the same structure as the arithmetic expression parser. It is again a straightforward mapping
// of the productions of the JSON grammar. The productions use one shortcut that simplifies the grammar: The repsep
// combinator parses a (possibly empty) sequence of terms that are separated by a given separator string. For instance,
// repsep(member, ",") parses a comma-separated sequence of member terms. Otherwise, the productions in the parser
// correspond exactly to the productions in the grammar, as was the case for the arithmetic expression parsers.

import scala.io.Source
import scala.util.parsing.combinator._

class JSON extends JavaTokenParsers {
  def value: Parser[Any] = (
      obj
    | arr
    | stringLiteral
    | floatingPointNumber ^^ (_.toDouble)
    | "null"  ^^ (_ => null)
    | "true"  ^^ (_ => true)
    | "false" ^^ (_ => false)
  )
  def obj: Parser[Map[String, Any]] = "{" ~> repsep(member, ",") <~ "}" ^^ (Map() ++ _)
  def arr: Parser[List[Any]] = "[" ~> repsep(value, ",") <~ "]"
  def member: Parser[(String, Any)] = stringLiteral ~ ":" ~ value ^^ { case name ~ ":" ~ value => (name, value) }
}

// To try out the JSON parsers, we'll change the framework a bit, so that the parser operates on a resource file
// instead of on the command line:

object ParseJSON extends JSON {
  def main(args: Array[String]) {
    val addressBookContents = Source.fromURL(getClass.getResource("address_book.json")).mkString
    println(parseAll(value, addressBookContents))
  }
}

// The main method in this program first loads the contents of address_book.json into addressBookContents.
// It then parses the characters in that string according to the value production of the JSON grammar.
// Note that parseAll and parse exist in overloaded variants: both can take a character sequence or alternatively an
// input reader as second argument.

// This is now a full JSON parser that returns meaningful results. If you run this parser on the address-book.json
// file, you will get the following result (after adding some newlines and indentation):
// [14.2] parsed:
// Map(
//    "address book" -> Map(
//        "name" -> "John Smith",
//        "address" -> Map(
//            "street" -> "10 Market Street",
//            "city" -> "San Francisco, CA",
//            "zip" -> "94111"),
//        "phone numbers" -> List("408 338-4238", "408 111-6892")
//    )
// )

