package net.miladinov.implicits

object ImplicitConversionsOfReceiverTypes {
  class DoubleColonAssoc [A] (key: A) {
    def :: [B] (value: B): (A, B) = (key, value)
  }

  implicit def toDoubleColonAssoc [A] (x: A): DoubleColonAssoc[A] = new DoubleColonAssoc(x)
}
