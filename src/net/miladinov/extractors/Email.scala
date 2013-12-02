package net.miladinov.extractors

object Email {
  // The (optional) injection method
  def apply (user: String, domain: String) = s"$user@$domain"

  // The (mandatory) extraction method
  def unapply (email: String): Option[(String, String)] = {
    email split "@" match {
      case Array(user, domain) => Some(user, domain)
      case _ => None
    }
  }
}
