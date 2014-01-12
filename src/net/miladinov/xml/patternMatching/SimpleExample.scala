package net.miladinov.xml.patternMatching

object SimpleExample {
  // An XML pattern looks just like an XML literal. The main difference is that if you insert a {} escape,
  // then the code inside the {} is not an expression but a pattern. A pattern embedded in {} can use the full
  // Scala pattern language, including binding new variables, performing type tests, and ignoring content using
  // the _ and _* patterns. Here is a simple example:

  def proc (node: scala.xml.Node): String =
    node match {
      case <a>{contents}</a> => "It's an a: " + contents
      case <b>{contents}</b> => "It's a b: " + contents
      case _ => "It's something else"
    }

  // This function has a pattern match with three cases. The first case looks for an <a> element whose contents
  // consist of a single sub-node. It binds those contents to a variable named contents and then evaluates the code
  // to the right of the associated right arrow (=>). The second case does the same thing but looks for a <b>
  // instead of an <a>, and the third case matches anything not matched by any other case. Here is the function in use:

  // scala> import net.miladinov.xml.patternMatching.SimpleExample._
  // import net.miladinov.xml.patternMatching.SimpleExample._
  //
  // scala> proc(<a>apple</a>)
  // res0: String = It's an a: apple
  //
  // scala> proc(<b>banana</b>)
  // res1: String = It's a b: banana
  //
  // scala> proc(<c>cherry</c>)
  // res2: String = It's something else
}
