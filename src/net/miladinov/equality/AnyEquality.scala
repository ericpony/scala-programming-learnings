package net.miladinov.equality

object AnyEquality {
  // As mentioned before, the definition of equality is different in Scala and Java. Java has two equality comparisons:
  // the == operator, which is the natural equality for value types and object identity for reference types, and the
  // equals method, which is (user-defined) canonical equality for reference types. This convention is problematic,
  // because the more natural symbol, ==, does not always correspond to the natural notion of equality.

  // When programming in Java, a common pitfall for beginners is to compare objects with == when they should have been
  // compared with equals. For instance, comparing two strings x and y using "x == y" might well yield false in Java,
  // even if x and y have exactly the same characters in the same order.

  // Scala also has an equality method signifying object identity, but it is not used much. That kind of equality,
  // written "x eq y", is true if x and y reference the same object. The == equality is reserved in Scala for the
  // "natural" equality of each type. For value types, == is value comparison, just like in Java. For reference types,
  // == is the same as equals in Scala. You can redefine the behavior of == for new types by overriding the equals
  // method, which is always inherited from class Any. The inherited equals, which takes effect unless overridden,
  // is object identity, as is the case in Java. So equals (and with it, ==) is by default the same as eq,
  // but you can change its behavior by overriding the equals method in the classes you define. It is not possible
  // to override == directly, as it is defined as a final method in class Any.

  // That is, Scala treats == as if it were defined as follows in class Any:
  final def == (that: Any): Boolean =
    if (null eq this) {null eq that} else {this equals that}
}
