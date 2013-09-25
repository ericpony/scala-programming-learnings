package net.miladinov.typeParameterization

object BasicQueue {
  def apply[T](elements: T*): FunctionalQueue[T] = new ImprovedBasicQueueImplementation(elements.toList, Nil)

  private class ImprovedBasicQueueImplementation[+T] (
    private[this] var leading: List[T],
    private[this] var trailing: List[T]
  ) extends FunctionalQueue[T] {

    private def mirror =
      if (leading.isEmpty) {
        while (!trailing.isEmpty) {
          leading = trailing.head :: leading
          trailing = trailing.tail
        }
      }

    def head: T = {
      mirror
      leading.head
    }

    def tail: FunctionalQueue[T] = {
      mirror
      new ImprovedBasicQueueImplementation(leading.tail, trailing)
    }

    def enqueue[U >: T](element: U): FunctionalQueue[U] = new ImprovedBasicQueueImplementation[U](leading, element :: trailing)
  }

}
