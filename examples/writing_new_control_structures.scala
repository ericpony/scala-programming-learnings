// In languages with first-class functions, you can effectively make new control structures even though the syntax
// of the language is fixed. All you need to do is create methods that take functions as arguments. For example,
// here is the "twice" control structure, which repeats an operation two times and returns the result:
def twice(op: Double => Double, x: Double) = op(op(x))

// Here's the "twice" of adding 1 to 5:
twice(_ + 1, 5)
// res6: Double = 7.0

// The type of op in this example is Double => Double, which means it is a function that takes one Double
// as an argument and returns another Double. Any time you find a control pattern repeated in multiple parts of your
// code, you should think about implementing it as a new control structure.
