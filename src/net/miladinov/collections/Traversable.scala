package net.miladinov.collections

trait Traversable {
  type Elem

  /**
   * Executes a function f for every element in xs:
   *
   * xs foreach f
   *
   * @param f
   * @tparam U
   */
  def foreach [U] (f: Elem => U)
}
