import org.scalatest.FunSuite
import Element.elem

class ElementSuite extends FunSuite {
  test("elem result should have passed width") {
    val element = elem('x', 2, 3)
    assert(element.width == 2)
  }

  test("Triple equal operator gives informative failure reports") {
    val element = elem('x', 4, 5)
    assert(element.width === 4)
  }

  test("The expectResult function is another way to do assertions") {
    val element = elem('x', 5, 6)
    expectResult(5) {
      element.width
    }
  }

  test("Should throw an IllegalArgumentException when passed a negative number") {
    intercept[IllegalArgumentException] {
      elem('y', -2, 3)
    }
  }
}
