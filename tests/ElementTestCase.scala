import org.junit.Test
import org.junit.Assert.{assertEquals, fail}
import Element.elem

class ElementTestCase {

  @Test(expected = classOf[IllegalArgumentException])
  def uniformElementShouldRejectNegativeWidths() {
    val element = elem('x', 2, 3)
    assertEquals(2, element.width)
    assertEquals(3, element.height)

    elem('x', -2, 3)
    fail()
  }
}
