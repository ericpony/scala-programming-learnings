// Suppose we were to implement my own assert, but without using by-name parameters. We could write it like this:
var assertionsEnabled = true

def myAssert(predicate: () => Boolean) {
  if (assertionsEnabled && !predicate())
    throw new AssertionError
}

// The definition is fine, but using it is a little awkward:
myAssert(() => 5 > 3)
