package net.miladinov.varianceAnnotations

class Cell[T](init: T) {
  private[this] var current = init

  def get = current

  def set(x: T) {
    current = x
  }
}
