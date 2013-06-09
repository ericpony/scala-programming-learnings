
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEndingIn(query: String) =
    filesMatching(_.endsWith(query))

  def filesContaining(query: String) =
    filesMatching(_.contains(query))

  def filesMatchingRegex(query: String) =
    filesMatching(_.matches(query))

  def filesMatching(matcher: String => Boolean) = {
    for (file <- filesHere; if matcher(file.getName))
      yield file
  }
}
