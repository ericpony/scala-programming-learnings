package net.miladinov.typeParameterization

object BasicQueue {
  def apply[T](elements: T*): FunctionalQueue[T] = new BasicQueueImplementation(elements.toList, Nil)

  private class BasicQueueImplementation[+T] (
    private val leading: List[T],
    private val trailing: List[T]
  ) extends FunctionalQueue[T] {

    private def mirror =
      if (leading.isEmpty)
        new BasicQueueImplementation(trailing.reverse, Nil)
      else
        this

    def head: T = mirror.leading.head

    def tail: FunctionalQueue[T] = {
      val q = mirror
      new BasicQueueImplementation(q.leading.tail, q.trailing)
    }

    def enqueue[U >: T](element: U): FunctionalQueue[U] = new BasicQueueImplementation[U](leading, element :: trailing)
  }
}
