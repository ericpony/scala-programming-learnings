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

// A by-name type, in which the empty parameter list, (), is left out, is only allowed for parameters. There is no such
// thing as a by-name variable or a by-name field. Now, you may be wondering why you couldn't simply write myAssert
// using a plain old Boolean for the type of its parameter, like this:
def boolAssert(predicate: Boolean) {
  if (assertionsEnabled && !predicate)
    throw new AssertionError
}

// This formulation is also legal, of course, and the code using this version of boolAssert would still look exactly
// as before:
boolAssert(5 > 3)

// Nevertheless, one difference exists between these two approaches that is important to note. Because the type of
// boolAssert's parameter is Boolean, the expression inside the parentheses in boolAssert(5 > 3) is evaluated before
// the call to boolAssert. The expression 5 > 3 yields true, which is passed to boolAssert. By contrast, because
// the type of byNameAssert's predicate parameter is => Boolean, the expression inside the parentheses
// in byNameAssert(5 > 3) is not evaluated before the call to byNameAssert. Instead a function value will be created
// whose apply method will evaluate 5 > 3, and this function value will be passed to byNameAssert.

// The difference between the two approaches, therefore, is that if assertions are disabled, you'll see any
// side effects that the expression inside the parentheses may have in boolAssert, but not in byNameAssert. For example,
// if assertions are disabled, attempting to assert on “x / 0 == 0” will yield an exception in boolAssert’s case:
//
// scala> var assertionsEnabled = false
// assertionsEnabled: Boolean = false
//
// scala> boolAssert(x / 0 == 0)
// java.lang.ArithmeticException: / by zero
//     at .<init>(<console>:9)
//     at .<clinit>(<console>)
//     at RequestResult$.<init>(<console>:9)
//     at RequestResult$.<clinit>(<console>)
