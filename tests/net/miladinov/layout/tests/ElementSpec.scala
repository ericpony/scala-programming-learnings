package net.miladinov.layout.tests

import net.miladinov.layout.Element
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import Element.elem

class ElementSpec extends FlatSpec with ShouldMatchers {
  "A UniformElement" should "have a width equal to the passed value" in {
    val element = elem('x', 2, 3)
    element.width should be (2)
  }

  it should "have a height equal to the passed value" in {
    val element = elem('x', 2, 3)
    element.height should be (3)
  }

  it should "throw an IAE if passed a negative width" in {
    evaluating {
      elem('x', -2, 3)
    } should produce [IllegalArgumentException]
  }
}
