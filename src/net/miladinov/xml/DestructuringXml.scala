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
}
