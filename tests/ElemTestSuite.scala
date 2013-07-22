import net.miladinov.layout.Element
import org.scalatest.Suite
import Element.elem

class ElemTestSuite extends Suite {
  def testUniformElement() {
    val ele = elem('x', 2, 3)
    assert(ele.width == 2)
  }
}
