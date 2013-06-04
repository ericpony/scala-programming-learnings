// Now let's talk about some closures. Closures see changes made to free variables referenced inside of, but created
// outside of, the closure. The same is true in the opposite direction as well - changes made by a closure to a captured
// variable are visible outside the closure. Here's an example:
val someNumbers = List(-11, -10, -5, 0, 5, 10)
var sum = 0
someNumbers.foreach(sum += _)
// At this point, after running the above code, the value of sum will no longer be 0 but -11. This example is a
// roundabout way to sum the numbers in a List. Variable sum is in a surrounding scope from the function literal
// sum += _, which adds numbers to sum. Even though it is the closure modifying the sum at runtime, the resulting total,
// -11, is still visible outside the closure.

// What if a closure accesses some variable that has several different copies as the program runs? For example,
// what if a closure uses a local variable of some function, and the function is invoked many times? Which instance of
// that variable gets used at each access? Only one answer is consistent with the rest of the language: the instance
// used is the one that was active at the time the closure was created. For example, here is a function that
// creates and returns “increase” closures:
def makeIncreaser(more: Int) = (x: Int) => x + more
val inc1 = makeIncreaser(1)
val inc9999 = makeIncreaser(9999)

// When you call makeIncreaser(1), a closure is created and returned that captures the value 1 as the binding for more.
// Similarly, when you call makeIncreaser(9999), a closure that captures the value 9999 for more is returned.
// When you apply these closures to arguments (in this case, there’s // just one argument, x, which must be passed in),
// the result that comes back depends on how more was defined when the closure was created:

// scala>  inc1(10)
// res2: Int = 11

// scala>  inc9999(10)
// res3: Int = 10009

// It makes no difference that the more in this case is a parameter to a method call that has already returned.
// The Scala compiler rearranges things in cases like this so that the captured parameter lives out on the heap,
// instead of the stack, and thus can outlive the method call that created it. This rearrangement is all taken care of
// automatically, so you don’t have to worry about it. Capture any variable you like: val, var, or parameter.
