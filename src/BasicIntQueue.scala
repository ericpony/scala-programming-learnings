import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buffer = new ArrayBuffer[Int]
  def get() = buffer.remove(0)
  def put(x: Int) { buffer += x }
}
