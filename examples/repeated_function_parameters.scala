// Repeated parameters (varargs)
def echo (args: String*) =
  for (arg <- args) println(arg)

// If you want to pass an actual array of Strings to echo, the compiler won't let you because of type mismatch.
val sentence = Array("What's", "up", "doc?")
// You can, however, append the array argument with a colon and an _* symbol, like this:
echo(sentence: _*)
// This notation tells the compiler to pass each element of sentence as its own argument to echo,
// rather than all of it as a single argument.
