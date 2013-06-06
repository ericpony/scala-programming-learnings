// A tail-recursive function will not build a new stack frame for each call; all calls will execute in a single frame.
// This may surprise a programmer inspecting a stack trace of a program that failed. For example, this function calls
// itself some number of times then throws an exception:
def boom(x: Int): Int =
  if (x == 0) throw new Exception("boom!") else boom(x - 1) + 1

// This function is _not_ tail recursive, because it performs an increment operation after the recursive call.
// You'll get what you expect when you run it:

// scala>  boom(5)
// java.lang.Exception: boom!
// 	at .boom(<console>:9)
// 	at .boom(<console>:9)
// 	at .boom(<console>:9)
// 	at .boom(<console>:9)
// 	at .boom(<console>:9)
// 	at .boom(<console>:9)
// 	at .<init>(<console>:9)

// If you now modify boom so that it does become tail recursive:
def bang(x: Int): Int =
  if (x == 0) throw new Exception("bang!")  else bang(x - 1)

// Even if you pass a much larger number such as 50, you'll still only see one stack frame for bang in the trace:
// scala>  bang(50)
// java.lang.Exception: bang!
// 	at .bang(<console>:9)
// 	at .<init>(<console>:9)

// Tail recursion on the JVM is limited to only the most basic kind. Scala only optimizes directly recursive calls back
// to the same function making the call. If the recursion is indirect, as in the following example of two mutually
// recursive functions, no optimization is possible:

// Mutual recursion is not tail-call optimizable:
def isEven(x: Int): Boolean =
  if (x == 0) true else isOdd(x - 1)
def isOdd(x: Int): Boolean =
  if (x == 0) false else isEven(x - 1)

// You also won't get a tail-call optimization if the final call goes to a function value. Consider for instance the
// following recursive code:
val funValue = nestedFun _
def nestedFun(x: Int) {
  if (x != 0) { println(x); funValue(x - 1) }
}

// The funValue variable refers to a function value that essentially wraps a call to nestedFun. When you apply the
// function value to an argument, it turns around and applies nestedFun to that same argument, and returns the result.
// You might hope, therefore, the Scala compiler would perform a tail-call optimization, but in this case it would not.
// Thus, tail-call optimization is limited to situations in which a method or nested function calls itself directly
// as its last operation, without going through a function value or some other intermediary.
