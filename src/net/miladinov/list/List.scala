package net.miladinov.list

abstract class List [+T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

case object Nil extends List[Nothing] {
  def isEmpty: Boolean = true

  def head: Nothing = throw new NoSuchElementException("Head of empty list")

  def tail: List[Nothing] = throw new NoSuchElementException("Tail of empty list")
}

// Class ::, pronounced "cons" for "construct," represents non-empty lists. It’s named that way in order
// to support pattern matching with the infix ::. You have seen before that every infix operation in a pattern
// is treated as a constructor application of the infix operator to its arguments. So the pattern x :: xs is
// treated as ::(x, xs) where :: is a case class. Here is the definition of the :: class:

final case class :: [T] (hd: T, tl: List[T]) extends List[T] {
  def isEmpty: Boolean = false

  def head: T = hd

  def tail: List[T] = tl
}