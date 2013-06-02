// Remember numbers from the previous example? We'll be seeing more of it later.
val numbers = List(-11, -10, -5, 0, 5, 10)

// Now for some discussion of partially applied functions.
// Let's define a function to start:
def sum(a: Int, b: Int, c:Int) = a + b + c

// You could apply sum to the arguments 1, 2, and 3 like this:
val result = sum(1, 2, 3)

// You could apply sum to no arguments, resulting in a partially applied function value:
val addThree = sum _

// You could then apply addThree to three Ints as well:
var result2 = addThree(2, 3, 4)

// addThree(2, 3, 4) is a short term for:
addThree.apply(2, 3, 4)

// Underscore can also express partially applied functions, as per the following, which produces a function value
// that accepts 1 parameter and invokes sum, passing 1, 3 and the parameter passed to plusFour:
val plusFour = sum(1, _, 3)

// Finally, returning to the foreach example, you can leave off the underscore entirely when you are writing a partially
// applied function expression in which you leave off all the parameters. This last form is allowed only in places where
// a function is required, such as the invocation of foreach in this example. The compiler knows a function is required
// here, because that's what foreach requires for its argument.
numbers.foreach(println)
