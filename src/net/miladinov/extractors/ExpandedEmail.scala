package net.miladinov.extractors

// It’s also possible to return some fixed elements from an unapplySeq together with the variable part.
// This is expressed by returning all elements in a tuple, where the variable part comes last, as usual.
// As an example, here is an extractor for emails where the domain part is already expanded into a sequence:
object ExpandedEmail {
  def unapplySeq (email: String): Option[(String, Seq[String])] = {
    val parts = email split "@"

    if (parts.length == 2)
      Some(parts(0), parts(1).split("\\.").reverse)
    else
      None
  }
}
