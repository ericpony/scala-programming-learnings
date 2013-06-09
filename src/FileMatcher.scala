
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEndingIn(query: String) =
    for (file <- filesHere; if file.getName.endsWith(query))
      yield file

  def filesContaining(query: String) =
    for (file <- filesHere; if file.getName.contains(query))
      yield file

  def filesMatchingRegex(query: String) =
    for (file <- filesHere; if file.getName.matches(query))
      yield file

  def filesMatching(query: String, matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }
}
