package net.miladinov.collection

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

final class RNA2 private (
  val groups: Array[Int],
  val length: Int
) extends IndexedSeq[Base] with mutable.IndexedSeqLike[Base, RNA2] {
  import RNA2._

  override def newBuilder: mutable.Builder[Base, RNA2] = new ArrayBuffer[Base] mapResult fromSeq

  def apply (index: Int): Base = {
    if (index < 0 || length <= index)
      throw new IndexOutOfBoundsException

    Base.fromInt(groups(index / N) >> (index % N * S) & M)
  }

  def update(idx: Int, elem: Base): Unit = {
    groups(idx / N) |= Base.toInt(elem) << (idx % N * S)
  }
}

object RNA2 {
  // Number of bits necessary to represent group
  private val S = 2

  // Number of groups that fit in an Int
  private val N = 32 / S

  // Bitmask to isolate a group
  private val M = (1 << S) - 1

  def fromSeq (buf: Seq[Base]): RNA2 = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    val rna2 = new RNA2(groups, buf.length)

    for (i <- 0 until buf.length) rna2.update(i, buf(i))

    rna2
  }

  def apply (bases: Base*) = fromSeq(bases)
}
