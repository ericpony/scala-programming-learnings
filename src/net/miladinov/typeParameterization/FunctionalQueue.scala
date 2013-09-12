package net.miladinov.typeParameterization

// A functional queue is a data structure with three operations:
trait FunctionalQueue[+T] {
  // Returns the first element of the queue
  def head: T

  // Returns a queue without its first element
  def tail: FunctionalQueue[T]

  // Returns a new queue with a given element appended at the end
  def enqueue[U >: T](element: U): FunctionalQueue[U]
}

// Unlike a mutable queue, a functional queue does not change its contents when
// an element is appended. Instead, a new queue is returned that contains the element.
// The goal of this chapter will be to create a class, which we’ll name Queue, that works
// like this:

// val q = Queue(1, 2, 3)
// returns: q: Queue[Int] = Queue(1, 2, 3)

// val q1 = q enqueue 4
// returns: q1: Queue[Int] = Queue(1, 2, 3, 4)

// q
// returns: Queue[Int] = Queue(1, 2, 3)
