package net.miladinov.extractors

object Email {
  // The (optional) injection method
  def apply (user: String, domain: String) = s"$user@$domain"

  // The (mandatory) extraction method
  def unapply (email: String): Option[(String, String)] = {
    email split "@" match {
      // N.B. you can leave off one pair of parentheses when passing a tuple to a function taking 1 argument
      // this means that Some(user, domain) is equivalent to Some((user, domain))
      case Array(user, domain) => Some(user, domain)
      case _ => None
    }
  }
}
