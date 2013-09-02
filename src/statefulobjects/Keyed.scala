package statefulobjects

// Using vars doesn't automatically preclude you from being functional!
// A class might contain vars and still be purely functional. An example would be a class
// that caches the result of an expensive operation in a field for optimization purposes.
// To pick an example, assume the following unoptimized class Keyed with
// an expensive operation computeKey:
class Keyed {
  def computeKey: Int = {
    // imagine some expensive computation here
    0
  }
}

// Provided that computeKey neither reads nor writes any vars, you can make
// Keyed more efficient by adding a cache:
class MemoKeyed extends Keyed {
  private var keyCache: Option[Int] = None

  override def computeKey: Int = {
    if (! keyCache.isDefined) keyCache = Some(super.computeKey)
    keyCache.get
  }
}

// Using MemoKeyed instead of Keyed can speed up things, because the second time the result
// of the computeKey operation is requested, the value stored in the keyCache field can be returned
// instead of running computeKey once again. But except for this speed gain, the behavior of class
// Keyed and MemoKeyed is exactly the same. Consequently, if Keyed is purely functional,
// then so is MemoKeyed, even though it contains a reassignable variable.

