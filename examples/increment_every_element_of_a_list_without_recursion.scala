// Here's a very inefficient approach to incrementing every element in a list
// of arbitrary size (as much as the heap allows):
import scala.collection.mutable.ListBuffer

def incrementAllIteratively(xs: List[Int]): List[Int] = {
  val buffer = new ListBuffer[Int]
  for (x <- xs) buffer += x + 1
  buffer.toList
}
