package net.miladinov.xml

object XmlLiterals {
  // The result of this expression is of type Elem, meaning it is an XML element with a label ("a") and children
  // ("This is some XML...," etc.)
  val elem = <a>
    This is some XML.
    Here is a tag: <atag/>
  </a>

  // Some other important XML classes are:
  // * Class Node is the abstract superclass of all XML node classes
  // * Class Text is a node holding just text. For example, the "stuff" part of <a>stuff</a> is of class Text.
  // * Class NodeSeq holds a sequence of Nodes. Many methods in the XML library process NodeSeqs in places you might
  //   expect them to process individual Nodes. You can still use such methods with individual nodes, however, since
  //   Node extends from NodeSeq. This may sound weird, but it works out well for XML. You can think of an individual
  //   Node as a one-element NodeSeq.

  // You are not restricted to writing out the exact XML you want, character for character. You can evaluate Scala code
  // in the middle of an XML literal by using curly braces as an escape. Here is a simple example:
  val escapingExample = <a> {"hello" + " from XML!"} </a>

  // A braces escape can include arbitrary Scala content, including further XML literals. Thus, as the nesting level
  // increases, your code can switch back and forth between XML and ordinary Scala code. Here's an example.
  object CarXml {
    val yearMade = 1979

    val yearMadeXml =
      <a> { if (yearMade < 2000) <old> {yearMade} </old>
            else xml.NodeSeq.Empty }
      </a>

    // Evaluating yearMadeXml would return:
    // scala> import net.miladinov.xml.XmlLiterals.CarXml

    // CarXml.yearMadeXml
    // import net.miladinov.xml.XmlLiterals.CarXml

    // scala> res0: scala.xml.Elem =
    // <a> <old> 1979 </old>
    //       </a>

    // If the code inside the curly braces evaluates to either an XML node or a sequence of XML nodes, those nodes are
    // inserted directly as is. In the above example, if yearMade is less than 2000, it is wrapped in <old> tags and
    // added to the <a> element. Otherwise, nothing is added. Note in the above example that "nothing" as an XML node
    // is denoted with xml.NodeSeq.Empty.
  }

  // An expression inside a brace escape does not have to evaluate to an XML node. It can evaluate to any Scala value.
  // In such a case, the result is converted to a string and inserted as a text node:
  // scala> <a> {3 + 4} </a>
  // res3: scala.xml.Elem = <a> 7 </a>

  // Any <, >, and & characters in the text will be escaped if you print the node back out:
  val potentialSecurityHole = <a> {"</a>potential security hole<a>"} </a>
  // scala> import net.miladinov.xml.XmlLiterals

  // XmlLiterals.potentialSecurityHole
  // import net.miladinov.xml.XmlLiterals

  // scala> res0: scala.xml.Elem = <a> &lt;/a&gt;potential security hole&lt;a&gt; </a>

  // To contrast, if you create XML with low-level string operations, you will run into traps such as the following:
  // scala> "<a>" + "</a>potential security hole<a>" + "</a>"
  // res1: String = <a></a>potential security hole<a></a>

  // What happens here is that a user-supplied string has included XML tags of its own, in this case </a> and <a>.
  // This behavior can allow some nasty surprises for the original programmer, because it allows the user to affect
  // the resulting XML tree outside of the space provided for the user inside the <a> element. You can prevent
  // this entire class of problems by always constructing XML using XML literals, not string appends.
}
