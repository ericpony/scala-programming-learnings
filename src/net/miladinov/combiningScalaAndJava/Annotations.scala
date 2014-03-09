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

  object Serialization {
    // Scala's three standard serialization annotations are all translated to Java equivalents. A @serializable
    // class has Java's Serializable interface added to it. A @SerialVersionUID(1234L) annotation is converted
    // to the following Java field definition:

    // Java serial version marker
    // private final static long SerialVersionUID = 1234L

    // Any variable marked @transient is given the Java transient modifier.
  }

  object ExceptionsThrown {
    // Scala does not check that thrown exceptions are caught. That is, Scala has no equivalent to Java's throws
    // declarations on methods. All Scala methods are translated to Java methods that declare no thrown exceptions.
    // The reason it all works is that the Java bytecode verifier does not check the declarations, anyway!
    // The Java compiler checks, but not the verifier.

    // The reason this feature is omitted from Scala is that the Java experience with it has not been purely positive.
    // Because annotating methods with throws clauses is a heavy burden, too many developers write code that swallows
    // and drops exceptions, just to get the code to compile without adding all those throws clauses. They may intend
    // to improve the exception handling later, but experience shows that all too often time-pressed programmers will
    // never come back and add proper exception handling. The twisted result is that this well-intentioned feature
    // often ends up making code less reliable. A large amount of production Java code swallows and hides runtime
    // exceptions, and the reason it does so is to satisfy the compiler.

    // Sometimes when interfacing to Java, however, you may need to write Scala code that has Java-friendly
    // annotations describing which exceptions your methods may throw. For example, each method in an RMI remote
    // interface is required to mention java.io.RemoteException in its throws clause. Thus, if you wish to write an
    // RMI remote interface as a Scala trait with abstract methods, you would need to list RemoteException in the
    // throws clauses for those methods. To accomplish this, all you have to do is mark your methods with @throws
    // annotations. For example, the Scala class shown below has a method marked as throwing IOException.

    class Reader(fileName: String) {
      import java.io._
      private val in = new BufferedReader(new FileReader(fileName))

      @throws(classOf[IOException])
      def read() = in.read()
    }

    // Here is how it looks from Java:
    // $ javap Reader

    // Compiled from "Reader.scala"
    // public class Reader extends java.lang.Object implements
    //   scala.ScalaObject{
    //     public Reader(java.lang.String);
    //     public int read() throws java.io.IOException;
    //     public int $tag();
    //   }
    // $

    // Note that the read method indicates with a Java throws clause that it may throw an IOException.
  }
}
