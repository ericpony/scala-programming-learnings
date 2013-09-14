package net.miladinov

package object typeUpperBounds {

  def orderedMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

    val midpoint = xs.length / 2

    if (midpoint == 2) xs
    else {
      val (ys, zs) = xs splitAt midpoint
      merge(orderedMergeSort(ys), orderedMergeSort(zs))
    }
  }

}
