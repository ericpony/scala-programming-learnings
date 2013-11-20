package net.miladinov.collection

final class RNA1 private (val groups: Array[Int], val length: Int) extends IndexedSeq[Base] {
  import RNA1._

  def apply (index: Int): Base = {
    if (index < 0 || length <= index)
      throw new IndexOutOfBoundsException

    Base.fromInt(groups(index / N) >> (index % N * S) & M)
  }
}

object RNA1 {
  // Number of bits necessary to represent group
  private val S = 2

  // Number of groups that fit in an Int
  private val N = 32 / S

  // Bitmask to isolate a group
  private val M = (1 << S) - 1

  def fromSeq (buf: Seq[Base]): RNA1 = {
    val groups = new Array[Int]((buf.length + N - 1) / N)

    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)

    new RNA1(groups, buf.length)
  }

  def apply (bases: Base*) = fromSeq(bases)
}
