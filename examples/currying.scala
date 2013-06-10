// To understand how to make control abstractions that feel more like language extensions, you first need to understand
// the functional programming technique called currying. A curried function is applied to multiple argument lists,
// instead of just one. Here is a regular, non-curried function, which adds two Int parameters, x and y.
def plainOldSum(x: Int, y: Int) = x + y

// By contrast, here is a similar function that’s curried. Instead of one list of two Int parameters, you apply this
// function to two lists of one Int parameter each.
def curriedSum(x: Int)(y: Int) = x + y

// What’s happening here is that when you invoke curriedSum, you actually get two traditional function invocations
// back to back. The first function invocation takes a single Int parameter named x, and returns a function value
// for the second function. This second function takes the Int parameter y.

// Here’s a function named first that does in spirit what the first traditional
// function invocation of curriedSum would do:
def first(x: Int) = (y: Int) => x + y

// Applying 1 to the first function - in other words, invoking the first function and passing in 1 - yields the second
// function:
val second = first(1)

// Applying 2 to the second function yields the result:
second(2)
// res6: Int = 3

// These first and second functions are just an illustration of the currying process. They are not directly connected
// to the curriedSum function. Nevertheless, there is a way to get an actual reference to curriedSum's "second"
// function. You can use the placeholder notation to use curriedSum in a partially applied function expression,
// like this:
val onePlus = curriedSum(1)_
// onePlus: (Int) => Int = <function1>

// The underscore in curriedSum(1)_ is a placeholder for the second parameter list.2 The result is a reference to a
// function that, when invoked, adds one to its sole Int argument and returns the result:
onePlus(2)
// res7: Int = 3
