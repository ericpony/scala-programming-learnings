package net.miladinov.typeParameterization

// If Queue were a mutable implementation, the enqueue operation  would affect
// the contents of the original queue. But for a functional queue, the appended value
// shows up only in the result, not in the queue, being operated on.

// Purely functional queues also have some similarity with lists. Both are so called fully
// persistent data structures, where old versions remain available even after extensions
// or modifications. Both support head and tail operations. But where a list is usually
// extended at the front, using a :: operation, a queue is extended at the end, using
// enqueue.

// How can this be implemented efficiently? Ideally, a functional (immutable) queue should
// not have a fundamentally higher overhead than an imperative (mutable) one. That is, all
// three operations head, tail, and enqueue should operate in constant time.

// One simple approach to implement a functional queue would be to use a list as
// representation type. Then head and tail would just translate into the same operations
// on the list, whereas enqueue would be concatenation. This would give the following
// implementation:

class SlowAppendQueue[T](elements: List[T]) extends FunctionalQueue[T] {
  def head: T = elements.head
  def tail: SlowAppendQueue[T] = new SlowAppendQueue[T](elements.tail)
  def enqueue(element: T): SlowAppendQueue[T] =  new SlowAppendQueue[T](elements ::: List(element))
}
