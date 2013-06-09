
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEndingIn(query: String) =
    filesMatching(query, _.endsWith(_))

  def filesContaining(query: String) =
    filesMatching(query, _.contains(_))

  def filesMatchingRegex(query: String) =
    filesMatching(query, _.matches(_))

  def filesMatching(query: String, matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }
}
