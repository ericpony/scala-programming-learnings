package net.miladinov.xml

// You have now seen enough of Scala's XML support to write the first part of a serializer: conversion from
// internal data structures to XML. All you need for this are XML literals and their brace escapes.

// As an example, suppose you are implementing a database to keep track of your extensive collection of vintage
// Coca-Cola thermometers. You might make the following internal class to hold entries in the catalog:

abstract class CocaColaThermometer {
  val description: String
  val yearMade: Int
  val dateObtained: String
  val bookPrice: Int      // in US cents
  val purchasePrice: Int  // in US cents
  val condition: Int      // 1 to 10

  override def toString = description

  // This is a straightforward, data-heavy class that holds various pieces of information such as when
  // the thermometer was made, when you got it, and how much you paid for it.
  // To convert instances of this class to XML, simply add a toXML method
  // that uses XML literals and brace escapes, like this:

  def toXML =
    <cctherm>
      <description>{description}</description>
      <yearMade>{yearMade}</yearMade>
      <dateObtained>{dateObtained}</dateObtained>
      <bookPrice>{bookPrice}</bookPrice>
      <purchasePrice>{purchasePrice}</purchasePrice>
      <condition>{condition}</condition>
    </cctherm>

  // Here is the method in action:
  // scala> import net.miladinov.xml.CocaColaThermometer

  // val therm = new CocaColaThermometer {
  //   override val dateObtained: String = "March 14, 2006"
  //   override val yearMade: Int = 1952
  //   override val purchasePrice: Int = 500
  //   override val bookPrice: Int = 2199
  //   override val description: String = "hot dog #5"
  //   override val condition: Int = 9
  // }
  // import net.miladinov.xml.CocaColaThermometer
  //
  // scala>      |      |      |      |      |      |      | therm: net.miladinov.xml.CocaColaThermometer = hot dog #5
  //
  // scala> therm.toXML
  // res0: scala.xml.Elem =
  // <cctherm>
  //       <description>hot dog #5</description>
  //       <yearMade>1952</yearMade>
  //       <dateObtained>March 14, 2006</dateObtained>
  //       <bookPrice>2199</bookPrice>
  //       <purchasePrice>500</purchasePrice>
  //       <condition>9</condition>
  //     </cctherm>
}

object CocaColaThermometer {
  // Using methods for destructuring XML, you can now write the dual of a serializer, a parser from XML back into your
  // internal data structures. For example, you can parse back a CocaColaThermometer instance by using the following code:
  def fromXML (node: scala.xml.Node): CocaColaThermometer =
    new CocaColaThermometer {
      override val dateObtained: String = (node \ "dateObtained").text
      override val yearMade: Int = (node \ "yearMade").text.toInt
      override val purchasePrice: Int = (node \ "purchasePrice").text.toInt
      override val bookPrice: Int = (node \ "bookPrice").text.toInt
      override val description: String = (node \ "description").text
      override val condition: Int = (node \ "condition").text.toInt
    }

  // This code searches through an input XML node, named node, to find each of the six pieces of data needed to specify
  // a CocaColaThermometer. The data that is text is extracted with .text and left as is. Here is the method in action:

  // scala> val node = therm.toXML
  // node: scala.xml.Elem =
  // <cctherm>
  //   <description>hot dog #5</description>
  //   <yearMade>1952</yearMade>
  //   <dateObtained>March 14, 2006</dateObtained>
  //   <bookPrice>2199</bookPrice>
  //   <purchasePrice>500</purchasePrice>
  //   <condition>9</condition>
  // </cctherm>

  // scala> CocaColaThermometer.fromXML(node)
  // res0: net.miladinov.xml.CocaColaThermometer = hot dog #5
}

