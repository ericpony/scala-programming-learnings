package net.miladinov.annotations

/**
 * Annotations are allowed on any kind of declaration or definition, including:
 *
 * val's
 * var's
 * def's
 * class's
 * object's
 * trait's
 * type's
 *
 * The annotation applies to the entirety of the declaration or definition that follows it.
 */
@deprecated object AnnotationsExamples {

  // Annotations can also be applied to an expression, as with the @unchecked annotation for pattern matching
  def nonExhaustivePatternMatch (value: Int) {
    (value: @unchecked) match {
      case 10 => // do stuff for 10
      case n if n < 10 => // do stuff for less than 10
      // more non-exhaustive cases here...
    }
  }

  // Annotations can take arguments, and for some annotations that support them, they can be arbitrary expressions, so
  // long as they type check. Some annotation classes let you refer to other variables that are in scope:
  @cool val normal = "Hello"
  @coolerThan(normal) val fonzy = "Heeyyy"
}

object AnnotationsThatTakeOtherAnnotations {
  // Internally, Scala represents an annotaiton as just a constructor call of an annotation class - replace the '@' with
  // 'new' and you have a valid instance creation expression. This means that named and default annotation arguments are
  // supported naturally, because Scala already has named and default arguments for method and constructor calls.
  // One slightly tricky bit concerns annotations that conceptually take other annotations as arguments, which are
  // required by some frameworks. You cannot write an annotation directly as an argument to an annotation,
  // because annotations are not valid expressions. In such cases you must use 'new' instead of '@',
  // as illustrated here:
  import annotation._

  class strategy (arg: Annotation) extends Annotation
  class delayed extends Annotation

  // You can't say it like this:
  // @strategy(@delayed) def f(){}

  // error: illegal start of simple expression
  // @strategy(@delayed) def f(){}
  //           ^

  // Instead, annotations passed to other annotations have to be written like this:
  @strategy(new delayed) def f(){}
}

object StandardAnnotations {
  // Scala includes several standard annotations. They are for features that are used widely enough to merit putting
  // in the language specification, but that are not fundamental enough to merit their own syntax. Over time, there
  // should be a trickle of new annotations that are added to the standard in just the same way.


  // Deprecation
  // Sometimes you write a class or method that you later wish you had not. Once it is available, though, code written
  // by other people might call the method. Thus, you cannot simply delete the method, because this would cause other
  // people's code to stop compiling.
  // Deprecation lets you gracefully remove a method or class that turns out to be a mistake. You mark the method
  // or class as deprecated, and then anyone who calls that method or class will get a deprecation warning.
  // They had better heed this warning and update their code! The idea is that after a suit- able amount of time
  // has passed, you feel safe in assuming that all reasonable clients will have stopped accessing the deprecated
  // class or method and thus that you can safely remove it.

  // You mark a method as deprecated simply by writing @deprecated be- fore it. For example:
  // @deprecated def bigMistake() = { /* your awful code here */ }

  // Such an annotation will cause the Scala compiler to emit deprecation warnings whenever Scala code
  // accesses the method.

  // If you supply a string as an argument to @deprecated, that string will be emitted along with the error message.
  // Use this message to explain to developers what they should use instead of the deprecated method.
  @deprecated("use newShinyMethod() instead")
  def bigMistake () = { /* Old and busted */ }

  def newShinyMethod () = { /* The new hotness */ }

  // Now any callers will get a message like this:
  // $ scalac -deprecation CodeWithDeprecatedAnnotations.scala
  // CodeWithDeprecatedAnnotations.scala:33: warning: method bigMistake in object
  // CodeWithDeprecatedAnnotations is deprecated: use newShinyMethod() instead
  //      bigMistake()
  //      ^
  // one warning found

  // Volatile fields
  // Concurrent programming does not mix well with shared mutable state. For this reason, the focus of Scala's concurrency
  // support is message passing and a minimum of shared mutable state. Nonetheless, sometimes programmers want to use
  // mutable state in their concurrent programs. The @volatile annotation helps in such cases. It informs the compiler
  // that the variable in question will be used by multiple threads. Such variables are implemented so that reads and
  // writes to the variable are slower, but accesses from multiple threads behave more predictably. The @volatile keyword
  // gives different guarantees on different platforms. On the Java platform, however, you get the same behavior as if
  // you wrote the field in Java code and marked it with the Java volatile modifier.
  @volatile var numMissiles: Int = 15
}

object BinarySerialization {
  // Many languages include a framework for binary serialization. A serialization framework helps you convert objects into
  // a stream of bytes and vice versa. This is useful if you want to save objects to disk or send them over the network.
  // XML can help with the same goals, but it has different tradeoffs regarding speed, space usage, flexibility and
  // portability. Scala does not have its own serialization framework. Instead you should use a framework from your
  // underlying platform. What Scala does is provide three annotations that are useful for a variety of frameworks.
  // Also, the Scala compiler for the Java platform interprets these annotations in the Java way.

  // The first annotation indicates whether a class is serializable at all. Most clases are serializable, but not all.
  // A handle to  socket or GUI window, for example, cannot be serialized. By default, a class is not considered serializable.
  // You could add a @serializable annotation to any class you would like to be serializable.

  // However, as of Scala 2.9.0, this is deprecated.
  @serializable
  class OldAndBustedSerializable (val foo: Int,  val bar: String, val baz: Boolean)

  // instead of `@serializable class C`, use `class C extends Serializable`
  class NewHotnessSerializable (val foo: Int, val bar: String, val baz: Boolean) extends Serializable

  // The second annotation helps deal with serializable classes changing as time goes by. You can attach a serial number
  // to the current version of a class by adding an annotation like @SerialVersionUID(1234), where 1234
  // should be replaced by your serial number of choice. The framework should store this number in the generated
  // byte stream. When you later reload that byte stream and try to convert it to an object, the framework can check
  // that the current version of the class has the same version number as the version in the byte stream.
  // If you want to make a serialization-incompatible change to your class, then you can change the version number.
  // The framework will then automatically refuse to load old instances of the class.
  @SerialVersionUID(1234L)
  case class Datom (entity: String, attribute: String, value: String, time: Long)

  // Finally, Scala provides a @transient annotation for fields that should not be serialized at all. If you mark
  // a field as @transient, then the framework should not save the field even when the surrounding object is serialized.
  // When the object is loaded, the field will be restored to the default value for the type of the field
  // annotated as @transient.


  class WindowHandle (val title: String, @transient val windowManager: IWindowManager) extends Serializable {
    /* stuff here */
  }

  // Window managers probably don't want to be serialized
  trait IWindowManager
}
