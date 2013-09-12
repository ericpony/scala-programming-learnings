package net.miladinov.typeParameterization

// The problem with SlowAppendQueue is in the enqueue operation. It takes time proportional to
// the number of elements stored in the queue. If you want  constant time append, you could also try
// to reverse the order of the elements in the representation list, so that the last element that’s
// appended comes first in the list. This would lead to the following implementation:

class SlowHeadQueue[T](elements: List[T]) extends FunctionalQueue[T] {
  def head: T = elements.last
  def tail: FunctionalQueue[T] = new SlowHeadQueue[T](elements.init)
  def enqueue[U >: T](element: U): FunctionalQueue[U] = new SlowHeadQueue[U](element :: elements)
}
