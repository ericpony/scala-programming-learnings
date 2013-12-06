package net.miladinov.extractors

// The unapply method of the previous example returned a pair of element values in the success case. This is easily
// generalized to patterns of more than two variables. To bind N variables, an unapply would return an N-element tuple,
// wrapped in a Some.

// The case where a pattern binds just one variable is treated differently, however. There is no one-tuple in Scala.
// To return just one pattern element, the unapply method simply wraps the element itself in a Some.
// For example, this extractor object defines apply and unapply for strings that consist of the same substring appearing
// twice in a row:

object Twice {
  def apply (s: String): String = s + s

  def unapply (s: String): Option[String] = {
    val length = s.length / 2
    val half = s.substring(0, length)

    if (half == s.substring(length))
      Some(half)
    else
      None
  }
}
