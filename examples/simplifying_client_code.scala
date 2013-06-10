// Consider exists, a method that determines whether a passed value is contained in a collection.
def containsNeg(nums: List[Int]): Boolean = nums.exists(_ < 0)

// The exists method represents a control abstraction. It is a special-purpose looping construct provided
// by the Scala library rather than being built into the Scala language like while or for. In the previous section,
// the higher-order function, filesMatching, reduces code duplication in the implementation of the object FileMatcher.

// The exists method provides a similar benefit, but because exists is public in Scala’s collections API, the code
// duplication it reduces is client code of that API. If exists didn't exist, and you wanted to write a containsOdd
// method, to test whether a list contains odd numbers, you might write it like this:
// def containsOdd(nums: List[Int]): Boolean = {
//   var exists = false
//   for (num <- nums)
//     if (num % 2 == 1)
//       exists = true
//   exists
// }

// If you compare the body of containsNeg with that of containsOdd, you’ll
// find that everything is repeated except the test condition of an if expression.
// Using exists, you could write this instead:
def containsOdd(nums: List[Int]) = nums.exists(_ % 2 == 1)

// The body of the code in this version is again identical to the body of the corresponding containsNeg method
// (the version that uses exists), except the condition for which to search is different. Yet the amount of code
// duplication is much smaller because all of the looping infrastructure is factored out into the exists method itself.
// There are many other looping methods in Scala’s standard library. As with exists, they can often shorten your code
// if you recognize opportunities to use them.
