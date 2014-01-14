package net.miladinov.xml.patternMatching

object BewareWhitespace extends App {
  // Another tip, be aware that XML patterns work very nicely with for expressions as a way to iterate
  // through some parts of an XML tree while ignoring other parts. For example, suppose you wish to skip
  // over the white space between records in the following XML structure:

  val catalog =
    <catalog>
      <cctherm>
        <description>hot dog #5</description>
        <yearMade>1952</yearMade>
        <dateObtained>March 14, 2006</dateObtained>
        <bookPrice>2199</bookPrice>
        <purchasePrice>500</purchasePrice>
        <condition>9</condition>
      </cctherm>
      <cctherm>
        <description>Sprite Boy</description>
        <yearMade>1964</yearMade>
        <dateObtained>April 28, 2003</dateObtained>
        <bookPrice>1695</bookPrice>
        <purchasePrice>595</purchasePrice>
        <condition>5</condition>
      </cctherm>
    </catalog>

  // Visually, it looks like there are two nodes inside the <catalog> element. Actually, though, there are five.
  // There is white space before, after, and between the two elements! If you do not consider this white space,
  // you might incorrectly process the thermometer records as follows:

  catalog match {
    case <catalog>{therms @ _*}</catalog> =>
      for (therm <- therms)
        println("processing: "+
          (therm \ "description").text)
  }

  // processing:
  // processing: hot dog #5
  // processing:
  // processing: Sprite Boy
  // processing:

  // Notice all of the lines that try to process white space as if it were a true thermometer record. What you would
  // really like to do is ignore the white space and process only those sub-nodes that are inside a <cctherm> element.
  // You can describe this subset using the pattern <cctherm>{_*}</cctherm>, and you can restrict the for expression to
  // iterating over items that match that pattern:

  catalog match {
    case <catalog>{therms @ _*}</catalog> =>
      for (therm @ <cctherm>{_*}</cctherm>  <-  therms)
        println("processing: "+
          (therm \ "description").text)
  }

  // processing: hot dog #5
  // processing: Sprite Boy
}
