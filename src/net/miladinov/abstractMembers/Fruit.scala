package net.miladinov.abstractMembers

abstract class Fruit {
  val v: String

  // v for val
  def m: String // m for method
}

abstract class Apple extends Fruit {
  val v: String
  val m: String // OK to override parameter-less def with val
}

/* This would not compile:

abstract class BadApple extends Fruit {
  def v: String // Error: overriding v in class Fruit of type String;
                //        method v needs to be a stable, immutable value
  def m: String
}

*/
