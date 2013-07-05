import org.scalatest.FunSuite
import Element.elem

class ElementSuite extends FunSuite {
  test("elem result should have passed width") {
    val element = elem('x', 2, 3)
    assert(element.width == 2)
  }
}
