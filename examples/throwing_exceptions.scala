// Throwing exceptions should look very familiar to java developers:
throw new IllegalArgumentException

// In Scala, however, throw is an expression that has a result type.
// The result type matters here:
def halve(n: Int): Int =
  if (n % 2 == 0)
    n / 2
  else
    throw new RuntimeException("n must be even")
