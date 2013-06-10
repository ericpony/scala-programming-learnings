// Consider exists, a method that determines whether a passed value is contained in a collection. You could of course
// search for an element by having a var initialized to false, looping through the collection checking each item,
// and setting the var to true if you find what you are looking for. Here’s a method that uses this approach
// to determine whether a passed List contains a negative number:
def containsNeg(nums: List[Int]): Boolean = {
  var exists = false
  for (num <- nums)
    if (num < 0)
      exists = true
  exists
}
