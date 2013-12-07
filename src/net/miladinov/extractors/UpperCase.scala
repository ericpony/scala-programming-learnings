package net.miladinov.extractors

// It's also possible that an extractor pattern does not bind any variables. In that case the corresponding unapply
// method returns a boolean true for success and false for failure. For instance, this extractor object characterizes
// strings consisting of all uppercase characters:

object UpperCase {
  def unapply (s: String): Boolean = s.toUpperCase == s
}
