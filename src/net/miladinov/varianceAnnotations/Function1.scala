package net.miladinov.varianceAnnotations

trait Function1[-S, +T] {
  def apply(x: S): T
}
