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

  object VolatileFields {
    // Likewise, any field marked @volatile in Scala is given the Java volatile modifier in the emitted code.
    // Thus, volatile fields in Scala behave exactly according to Java's semantics, and accesses to volatile fields
    // are sequenced precisely according to the rules specified for volatile fields in the Java memory model.
  }
}
