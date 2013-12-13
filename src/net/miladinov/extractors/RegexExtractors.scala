package net.miladinov.extractors

import scala.util.matching.Regex

object RegexExtractors {
  // Both java and scala let you write regex like this
  val Decimal = new Regex("(-)?(\\d+)(\\.\\d*)")

  // Scala raw strings let you write regex patterns with fewer escape slashes
  val RawStringDecimal = new Regex("""(-)?(\d+)(\.\d*)""")
}
