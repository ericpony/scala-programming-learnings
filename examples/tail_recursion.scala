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