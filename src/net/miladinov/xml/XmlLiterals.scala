package net.miladinov.xml

object XmlLiterals {
  // The result of this expression is of type Elem, meaning it is an XML element with a label ("a") and children
  // ("This is some XML...," etc.)
  val elem = <a>
    This is some XML.
    Here is a tag: <atag/>
  </a>
}
