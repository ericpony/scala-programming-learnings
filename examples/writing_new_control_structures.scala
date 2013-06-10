import java.io.{PrintWriter, File}

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

// Consider now a more widely used coding pattern: open a resource, operate on it, and then close the resource.
// You can capture this in a control abstraction using a method like the following:
def withPrintWriter(file: File, op: PrintWriter => Unit) {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

// Given such a method, you could use it like this:
withPrintWriter(
  new File("date.txt"),
  writer => writer.println(new java.util.Date)
)

// The advantage of using this method is that it's withPrintWriter, not user code, that assures the file is closed
// at the end. So it’s impossible to forget to close the file. This technique is called the loan pattern, because a
// control-abstraction function, such as withPrintWriter, opens a resource and "loans" it to a function. For instance,
// withPrintWriter loans a PrintWriter to the function, op. When the function completes, it signals that it no longer
// needs the "borrowed" resource. The resource is then closed in a finally block, to ensure it is indeed closed,
// regardless of whether the function completes by returning normally or throwing an exception.

// One way in which you can make the client code look a bit more like a built-in control structure is to use
// curly braces instead of parentheses to surround the argument list. In any method invocation in Scala
// in which you're passing in exactly one argument, you can opt to use curly braces to surround the argument
// instead of parentheses. For example, instead of
println("Hello, world!")

// you could write:
println { "Hello, world!" }

// The purpose of this ability to substitute curly braces for parentheses for passing in one argument is to enable
// client programmers to write function literals between curly braces. This can make a method call feel more like a
// control abstraction. For example, if we could somehow reorganize withPrintWriter so that it only took a single
// parameter, then we could make it look like a native control structure. Why don't we try currying?
def withPrintWriter(file: File)(op: PrintWriter => Unit) {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

// Now, we can call the method with a more pleasing syntax:
val file = new File("date.txt")

withPrintWriter(file) {
  writer => writer.println(new java.util.Date)
}

// Here, the first argument list, which contains one File argument, is written surrounded by the parentheses.
// The second argument list, which contains one function argument, is surrounded by curly braces.
