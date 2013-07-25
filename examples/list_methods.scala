// The empty list
List()
Nil

// Creates a new List[String] with the three values "Cool", "tools", and "rule"
List("Cool", "tools", "rule")

// Creates a new List[String] with the three values "Will", "fill", and "until"
val thrill = "Will" :: "fill" :: "until" :: Nil

// Concatenates two lists (returns a new List[String] with values "a", "b", "c", and "d")
List("a", "b") ::: List("c", "d")

// Returns the element at index 2 (zero based) of the thrill list
// (returns "until")
thrill(2)

// Counts the number of string elements in thrill that have length 4
// (returns 2)
thrill.count(s => s.length == 4)

// Returns the thrill list without its first 2 elements
// (returns List("until"))
thrill.drop(2)

// Returns the thrill list without its rightmost 2 elements
// (returns List("Will"))
thrill.dropRight(2)

// Determines whether a string element exists in thrill that has the value "until"
// (returns true)
thrill.exists(s => s == "until")

// Returns a list of all elements, in order, of the thrill list that have length 4
// (returns List("Will", "fill"))
thrill.filter(s => s.length == 4)

// Indicates whether all elements in the thrill list end with the letter "l"
// (returns true)
thrill.forall(s => s.endsWith("l"))

// Executes the print statement on each of the strings in the thrill list
// (prints "Willfilluntil")
thrill.foreach(s => print(s))

// Same as the previous, but more concise (also prints "Willfilluntil")
thrill.foreach(print)

// Returns the first element in the thrill list
// (returns "Will")
thrill.head

// Returns a list of all but the last element in the thrill list
// (returns List("Will", "fill"))
thrill.init

// Indicates whether the thrill list is empty
// (returns false)
thrill.isEmpty

// Returns the last element in the thrill list
// (returns "until")
thrill.last

// Returns the number of elements in the thrill list
// (returns 3)
thrill.length

// Returns a list resulting from adding a "y" to each string element in the thrill list
// (returns List("Willy", "filly", "untily"))
thrill.map(s => s + "y")

// Makes a string with the elements of the list
// (returns "Will, fill, until")
thrill.mkString(", ")

// Returns a list of all elements, in order, of the thrill list except those that have length 4
// (returns List("until"))
thrill.filterNot(s => s.length == 4)

// Returns a list containing all elements of the thrill list in reverse order
// (returns List("until", "fill", "Will"))
thrill.reverse

// Returns a list containing all elements of the thrill list in alphabetical order
// of the first character lowercased
// (returns List("fill", "until", "Will"))
thrill.sortWith((s, t) =>
  s.charAt(0).toLower <
    t.charAt(0).toLower)

// returns the thrill list minus its first element
// (returns List("fill", "until"))
thrill.tail


// This code pretends that we don't already have List's ::: (concat) method, and
// we'll be re-implementing it as a stand-alone function, append(), that takes two Lists
// and returns a new List with the contents of the other two.
def append[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case x :: xs1 => x :: append(xs1, ys)
}


// Taking the length of a List is trivial:
List(1, 2, 3).length
// returns: Int = 3

// Methods init and last are reverses of head and tail.
// For example:
val abcde = List('a', 'b', 'c', 'd', 'e')
// returns: List[Char] = List(a, b, c, d, e)
abcde.last
// returns: Char = e
abcde.init
// returns: List[Char] = List('a', 'b', 'c', 'd')

// Like head and tail, these methods throw an exception when applied to the empty List:
List().init
// java.lang.UnsupportedOperationException: Nil.init
// at scala.List.init(List.scala:544)
// at ...
List().last
// java.util.NoSuchElementException: Nil.last
// at scala.List.last(List.scala:563)
// at ...

// Unlike head and tail, which both run in constant time, init and last need to traverse
// the whole list to compute their result. They therefore take time proportional to the
// length of the list. It’s a good idea to organize your data so that most accesses are
// at the head of a list, rather than the last element.


// If at some point in the computation an algorithm demands frequent accesses to the end
// of a list, it’s sometimes better to reverse the list first and work with the result
// instead. Here’s how to do the reversal:
abcde.reverse
// returns: List[Char] = List(e, d, c, b, a)

// Note that, like all other list operations, reverse creates a new list rather than
// changing the one it operates on. Since lists are immutable, such a change would not be
// possible, anyway. To verify this, check that the original value of abcde is unchanged
// after the reverse operation:
abcde
// returns: List[Char] = List(a, b, c, d, e)

// The reverse, init, and last operations satisfy some laws that can be used for reasoning
// about computations and for simplifying programs.

// 1. reverse is its own inverse:
val xs = List("foo", "bar", "baz")
xs.reverse.reverse equals xs

// 2. reverse turns init to tail and last to head, except that the elements are reversed:
xs.reverse.init equals xs.tail.reverse
xs.reverse.tail equals xs.init.reverse
xs.reverse.head equals xs.last
xs.reverse.last equals xs.head

// Reverse could be implemented using concatenation (:::), like in the following method, rev:
def rev[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case x :: xs1 => rev(xs1) ::: List(x)
}

// However, this method is less efficient than one would hope for. To study the complexity
// of rev, assume that the list xs has length n. Notice that there are n recursive calls
// to rev. Each call except the last involves a list concatenation. List concatenation
// xs ::: ys takes time proportional to the length of its first argument xs. Hence,
// the total complexity of rev is:
// n + (n - 1) + ... + 1 = (1 + n) * n / 2
// In other words, rev’s complexity is quadratic in the length of its input argument.
// This is disappointing when compared to the standard reversal of a mutable, linked list,
// which has linear complexity. However, the current implementation of rev is not the best
// implementation possible. Future learnings will show how to speed this up.
