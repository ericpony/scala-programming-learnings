package net.miladinov.implicits.implicitParameters

// Another thing to know about implicit parameters is that they are perhaps most often used
// to provide information about a type mentioned explicitly in an earlier parameter list,
// similar to the type classes of Haskell. As an example, consider the maxListUpperBound
// function shown here, which returns the maximum element of the passed list:

object FunctionsWithImplicitParameters {

  def maxListImpParm [T] (elements: List[T]) (implicit orderer: T => Ordered[T]): T =
    elements match {
      case List() => throw new IllegalArgumentException("Empty List!")
      case List(x) => x
      case x :: rest => {
        val maxRest = maxListImpParm(rest)(orderer)
        if (orderer(x) > maxRest) x
        else maxRest
      }
    }

  def maxList [T] (elements: List[T]) (implicit orderer: T => Ordered[T]): T =
    elements match {
      case List() => throw new IllegalArgumentException("Empty List!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest) // (orderer is implicit)
        if (x > maxRest) x          // (orderer(x) is implicit)
        else maxRest
    }
}
