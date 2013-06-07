
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEndingIn(query: String) =
    for (file <- filesHere; if file.getName.endsWith(query))
      yield file
}
