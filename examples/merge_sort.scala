def merge_sort [T] (less: (T, T) => Boolean) (xs: List[T]): List[T] = {
  def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
    case (Nil, _) => ys
    case (_, Nil) => xs
    case (x :: xs1, y :: ys1) =>
      if (less(x, y)) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
  }

  val midpoint = xs.length / 2

  if (midpoint == 0) xs
  else {
    val (ys, zs) = xs splitAt midpoint
    merge(merge_sort(less)(ys), merge_sort(less)(zs))
  }
}
