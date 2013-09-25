package net.miladinov.abstractMembers

// The following trait declares one of each kind of abstract member: an abstract
// type (T), method (transform), val (initial), and var (current):
trait Abstract {
  type T

  def transform(x: T): T

  val initial: T
  var current: T
}

// A concrete implementation of Abstract needs to fill in definitions for each
// of its abstract members. Here is an example implementation that provides these definitions:
class Concrete extends Abstract {
  type T = String

  def transform(x: String) = x + x

  val initial = "hi"
  var current = initial
}

// The implementation gives a concrete meaning to the type name T by defining it as an alias
// of type String. The transform operation concatenates a given string with itself, and the initial
// and current values are both set to "hi". This example gives you a rough first idea of what kinds
// of abstract members exist in Scala. The remainder of the chapter will present the details and explain
// what the new forms of abstract members, as well as type members in general, are good for.
