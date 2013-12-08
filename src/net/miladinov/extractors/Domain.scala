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
}
