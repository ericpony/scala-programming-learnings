package net.miladinov.extractors

import scala.util.matching.Regex

object RegexExtractors {
  // Both java and scala let you write regex like this
  val Decimal = new Regex("(-)?(\\d+)(\\.\\d*)")

  // Scala raw strings let you write regex patterns with fewer escape slashes
  val RawStringDecimal = new Regex("""(-)?(\d+)(\.\d*)""")

  // An even shorter way to write regex patterns: use the .r method on StringOps
  val RawStringOpsDecimal = """(-)?(\d+)(\.\d*)""".r

  def extractParts (decimal: String): (String, String, String) = {
    val RawStringOpsDecimal(sign, integerPart, fractionPart) = decimal
    (sign, integerPart, fractionPart)
  }
}
