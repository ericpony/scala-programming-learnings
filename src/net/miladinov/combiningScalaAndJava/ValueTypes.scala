package net.miladinov.combiningScalaAndJava

object ValueTypes {
  // A value type like Int can be translated in two different ways to Java. Whenever possible, the compiler translates
  // a Scala Int to a Java int to get better performance. Sometimes this is not possible, though, because the compiler
  // is not sure whether it is translating an Int or some other data type. For example, a particular List[Any]
  // might hold only Ints, but the compiler has no way to be sure.

  // In cases like this, where the compiler is unsure whether an object is a value type or not, the compiler uses
  // objects and relies on wrapper classes. Wrapper classes such as, for example, java.lang.Integer allow a value type
  // to be wrapped inside a Java object and thereby manipulated by code that needs objects.
}
