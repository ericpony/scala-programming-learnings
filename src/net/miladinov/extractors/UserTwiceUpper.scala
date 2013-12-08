package net.miladinov.extractors

// Here's an example of a pattern match that combines the previous 3 extractors: Email, Twice and UpperCase:
object UserTwiceUpper {
  def userTwiceUpper (s: String): String = s match {
    case Email(Twice(x @ UpperCase()), domain) => s"match: $x in domain $domain"
    case _ => "no match"
  }
}
