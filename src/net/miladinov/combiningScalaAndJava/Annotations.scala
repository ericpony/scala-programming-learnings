package net.miladinov.combiningScalaAndJava

object Annotations {

  // Additional effects from standard annotations
  // Several annotations cause the compiler to emit extra information when targeting the Java platform.
  // When the compiler sees such an annotation, it first processes it according to the general Scala rules,
  // and then it does something extra for Java.

  object Deprecation {
    // For any method or class marked @deprecated, the compiler will add Java's own deprecation annotation
    // to the emitted code. Because of this, Java compilers can issue deprecation warnings when Java code accesses
    // deprecated Scala methods.
  }
}
