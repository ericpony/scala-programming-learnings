package net.miladinov.extractors

import scala.util.matching.Regex

object RegexExtractors {
  // Both java and scala let you write regex like this
  val Decimal = new Regex("(-)?(\\d+)(\\.\\d*)")
}
