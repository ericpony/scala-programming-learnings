// Default parameter values
// Scala lets you specify default values for function parameters. The argument for such a parameter can optionally
// be omitted from a function call, in which case the corresponding argument will be filled in with the default.
// Here's an example of a function with a default value:
def printTime(out: java.io.PrintStream = Console.out) =
  out.println("time = " + System.currentTimeMillis())

// If you call the function as printTime(), thus specifying no argument to be used for out, then out will be set to its
// default value of Console.out. You could also call the function with an explicit output stream. For example, you could
// send logging to the standard error output by calling the function as printTime(Console.err). Default parameters are
// especially helpful when used in combination with named parameters. For example, function printTime2 has two
// optional parameters. The out parameter has a default of Console.out, and the divisor parameter
// has a default value of 1.
def printTime2(out: java.io.PrintStream = Console.out, divisor: Int = 1) =
  out.println("time = " + System.currentTimeMillis() / divisor)

// Function printTime2 can be called as printTime2() to have both parameters filled in with their default values.
// Using named arguments, however, either one of the parameters can be specified while leaving the other as the
// default.
// To specify the output stream, call it like this:
printTime2(out = Console.err)

// To specify the time divisor, call it like this:
printTime2(divisor = 100)
