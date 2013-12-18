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
@deprecated class QuickAndDirty {

  // Annotations can also be applied to an expression, as with the @unchecked annotation for pattern matching
  def nonExhaustivePatternMatch (value: Int) {
    (value: @unchecked) match {
      case 10 => // do stuff for 10
      case n if n < 10 => // do stuff for less than 10
      // more non-exhaustive cases here...
    }
  }
}
