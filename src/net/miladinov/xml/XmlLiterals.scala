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
}
