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
