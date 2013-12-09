package net.miladinov.extractors

object Domain {
  // optional injection method
  def apply (parts: String*): String = parts.reverse.mkString(".")

  // mandatory extraction method
  def unapplySeq (whole: String): Option[Seq[String]] = {
    Some(whole.split("\\.").reverse)
  }

  def example (dom: String) = dom match {
    case Domain("org", "acm") => println("acm.org")
    case Domain("com", "sun", "java") => println("java.sun.com")
    case Domain("net", _*) => println("a .net domain")
  }

  // You can use the Domain extractor to get more detailed information out of email strings.
  // For instance, to search for an email address named "tom" in some ".com" domain, you could write
  // the following function:

  def isTomInDotCom(s: String): Boolean = s match {
    case Email("tom", Domain("com", _*)) => true
    case _ => false
  }
}
