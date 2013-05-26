
class ChecksumAccumulator {
  private var sum = 0
  def add (b: Byte) { sum += b }
  def checksum(): Int = ~(sum & 0xFF) + 1
  def f(): Unit = "this String gets lost"
  def g() { "this String gets lost too" }
  def h() = { "this String gets returned!" }
}
