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


// Arrays allow you to hold a sequence of elements and efficiently access an element at an arbitrary position, both
// to get or update the element, with a zero-based index. Here’s how you create an array whose size you know, but
// for which you don’t yet know the element values:
val fiveInts = new Array[Int](5)
// returns: fiveInts: Array[Int] = Array(0, 0, 0, 0, 0)

// Here's how you initialize an array when you do know the element values:
val fiveToOne = Array(5, 4, 3, 2, 1)
// returns: fiveToOne: Array[Int] = Array(5, 4, 3, 2, 1)

// As mentioned previously, arrays are accessed in Scala by placing an index in parentheses, not square brackets
// as in Java. Here's an example of both accessing and updating an array element:
fiveInts(0) = fiveToOne(4)
fiveInts
// returns: Array[Int] = Array(1, 0, 0, 0, 0)