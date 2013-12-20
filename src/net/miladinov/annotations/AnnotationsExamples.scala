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
}
