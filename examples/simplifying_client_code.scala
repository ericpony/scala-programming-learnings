// Consider exists, a method that determines whether a passed value is contained in a collection.
def containsNeg(nums: List[Int]): Boolean = nums.exists(_ < 0)

// The exists method represents a control abstraction. It is a special-purpose looping construct provided
// by the Scala library rather than being built into the Scala language like while or for. In the previous section,
// the higher-order function, filesMatching, reduces code duplication in the implementation of the object FileMatcher.
