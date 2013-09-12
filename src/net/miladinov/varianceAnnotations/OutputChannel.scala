package net.miladinov.varianceAnnotations

trait OutputChannel[-T] {
  def write(x: T)
}
