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
