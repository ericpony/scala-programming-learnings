package net.miladinov.xml

object DestructuringXml {
  // Among the many methods available for the XML classes, there are three in particular that you should be aware of.
  // They allow you to take apart XML without thinking too much about the precise way XML is represented in Scala.
  // These methods are based on the XPath language for processing XML. As is common in Scala, you can write them
  // directly in Scala code instead of needing to invoke an external tool.

  // Extracting text
  // By calling the text method on any XML node you retrieve all of the text within that node, minus any element tags
  val fragment1 = <a>Sounds <tag /> good</a>

  // scala> import net.miladinov.xml.DestructuringXml

  // DestructuringXml.fragment1.text
  // scala> res0: String = Sounds  good

  // Any encoded characters are decoded automatically:
  val fragment2 = <a> input ---&gt; output </a>
  // DestructuringXml.fragment2.text
  // scala> res0: String = " input ---> output "

  // Extracting sub-elements
  // If you want to find a sub-element by tag name, simply call \ with the name of the tag
  val fragment3 = <a><b><c>hello</c></b></a>
  // DestructuringXml.fragment3 \ "b"
  // scala> res0: scala.xml.NodeSeq = NodeSeq(<b><c>hello</c></b>)

  // You can do a "deep search" and look through sub-sub-elements, etc., by using \\ instead of the \ operator:
  // scala> DestructuringXml.fragment3 \ "c"
  // res1: scala.xml.NodeSeq = NodeSeq()

  // scala> DestructuringXml.fragment3 \\ "c"
  // res2: scala.xml.NodeSeq = NodeSeq(<c>hello</c>)

  // scala> DestructuringXml.fragment3 \ "a"
  // res3: scala.xml.NodeSeq = NodeSeq()

  // scala> DestructuringXml.fragment3 \\ "a"
  // res4: scala.xml.NodeSeq = NodeSeq(<a><b><c>hello</c></b></a>)

  // Note: Scala uses \ and \\ instead of XPath’s / and //. The reason is that // starts a comment in Scala!
  // Thus, some other symbol has to be used, and using the other kind of slashes works well.
}
