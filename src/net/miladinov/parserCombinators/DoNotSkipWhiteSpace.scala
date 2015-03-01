package net.miladinov.parserCombinators

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

// The RegexParsers trait takes care of handling white space between symbols. To do this, it calls a method named
// handleWhiteSpace before running a literal or regex parser. The handleWhiteSpace method skips the longest input
// sequence that conforms to the whiteSpace regular expression, which is defined by default as follows:
// protected val whiteSpace = """\s+""".r

object DoNotSkipWhiteSpace extends RegexParsers {
  // If you prefer a different treatment of white space, you can override the whiteSpace val. For instance, if you want
  // white space not to be skipped at all, you can override whiteSpace with the empty regular expression:
  override protected val whiteSpace: Regex = "".r
}
