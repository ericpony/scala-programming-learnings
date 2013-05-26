// print args, imperative style:
def printArgsImperatively(args: Array[String]) {
  var i = 0
  while (i < args.length) {
    println(args(i))
    i += 1
  }
}

// use a more functional style by getting rid of the var:
def printArgsFunctionallyWithFor(args: Array[String]) {
  for (arg <- args) {
    println(args)
  }
}

// or perhaps with a foreach:
def printArgsFunctionallyWithForeach(args: Array[String]) {
  args.foreach(println)
}

// An even more functional approach would be to define a method
// that formats the passed args for printing, but just returns the formatted string:
// no vars or side-effects in sight!
def formatArgs(args: Array[String]) = args.mkString("\n")

// formatArgs doesn't actually print anything, but printing args is then easy:
println(formatArgs(args))

// with no vars or side-effects, formatArgs is very easy to test:
val res = formatArgs(Array("zero", "one", "two"))
assert(res == "zero\none\ntwo")
