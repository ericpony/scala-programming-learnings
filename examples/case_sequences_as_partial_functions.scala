// A sequence of cases (i.e., alternatives) in curly braces can be used anywhere
// a function literal can be used. Essentially, a case sequence is a function literal,
// only more general. Instead of having a single entry point and list of parameters,
// a case sequence has multiple entry points, each with their own list of parameters.
// Each case is an entry point to the function, and the parameters are specified
// with the pattern. The body of each entry point is the right-hand side of the case.
// Here is a simple example:

val withDefault: Option[Int] => Int = {
  case Some(x) => x
  case None => 0
}

// The body of this function has two cases. The first case matches a Some,
// and returns the number inside the Some. The second case matches a None,
// and returns a default value of zero. Here is this function in use:

withDefault(Some(10))
// res28: Int = 10

withDefault(None)
// res29: Int = 0

