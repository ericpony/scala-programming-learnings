// Perhaps the most important sequence type to know about is class List, the immutable linked-list described in detail
// before. Lists support fast addition and removal of items to the beginning of the list, but they do not provide fast
// access to arbitrary indexes because the implementation must iterate through the list linearly.
// This combination of features might sound odd, but they hit a sweet spot that works well for many algorithms.
// The fast addition and removal of initial elements means that pattern matching works well, as described earlier.
// The immutability of lists helps you develop correct, efficient algorithms because you never need to make copies
// of a list. Here’s a short example showing how to initialize a list and access its head and tail:
val colors = List("red", "blue", "green")
// returns: colors: List[String] = List(red, blue, green)

colors.head
// returns: String = red

colors.tail
// returns: List[String] = List(blue, green)

