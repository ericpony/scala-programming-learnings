// For a mutable collection, equality simply depends on the current elements at the time the
// equality test is performed. This means that a mutable collection might be equal to
// different collections at different times, depending what elements are added or removed.
// This is a potential trap when using a mutable collection as a key in a hash map.
// For example:

import collection.mutable.{HashMap, ArrayBuffer}
// import collection.mutable.{HashMap, ArrayBuffer}

val buf = ArrayBuffer(1, 2, 3)
// buf: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)

val map = HashMap(buf -> 3)
// map: scala.collection.mutable.HashMap[scala.collection.mutable.ArrayBuffer[Int],Int] = Map((ArrayBuffer(1, 2, 3),3))

map(buf)
// res0: Int = 3

buf(0) += 1

map(buf)
// java.util.NoSuchElementException: key not found: ArrayBuffer(2, 2, 3)

// In this example, the selection in the last line will most likely fail because the hash
// code of the array xs has changed in the second-to-last line. Therefore, the
// hash-code-based lookup will look at a different place than the one in which xs was
// stored.
