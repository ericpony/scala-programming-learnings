// Suppose we were to implement my own assert, but without using by-name parameters. We could write it like this:
var assertionsEnabled = true

def myAssert(predicate: () => Boolean) {
  if (assertionsEnabled && !predicate())
    throw new AssertionError
}

// The definition is fine, but using it is a little awkward:
myAssert(() => 5 > 3)

// Instead, we'd like to write it like myAssert(5 > 3), but this won't work because of a type mismatch; you're passing
// a boolean when it's expecting a function that returns boolean.

// So, let's change myAssert's predicate parameter into a by-name parameter, by changing its type, "() => Boolean", into
// "=> Boolean":
def byNameAssert(predicate: => Boolean) {
  if (assertionsEnabled && !predicate)
    throw new AssertionError
}

// Now you can leave out the empty parameter in the property you want to assert. The result is that byNameAssert looks
// exactly like using a built-in control structure:
byNameAssert(5 > 3)
