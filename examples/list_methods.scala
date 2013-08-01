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

// The drop and take operations generalize tail and init in that they return arbitrary
// prefixes or suffixes of a list. The expression “xs take n” returns the first n elements
// of the list xs. If n is greater than xs.length, the whole list xs is returned.
// The operation “xs drop n” returns all elements of the list xs except the first n ones.
// If n is greater than xs.length, the empty list is returned. The splitAt operation splits
// the list at a given index, returning a pair of two lists.

// define n to satisfy the interpreter:
val n = 5

// splitAt is defined by the equality:
xs splitAt n equals (xs take n, xs drop n)

// Here are some examples of take, drop and splitAt:
abcde take 2
// returns: List[Char] = List(a, b)

abcde drop 2
// returns: List[Char] = List(c, d, e)

abcde splitAt 2
// returns: (List[Char], List[Char]) = (List(a, b), List(c, d, e))

// Random element selection is supported through the apply method;
// however it is a less common operation for lists than it is for arrays.
abcde apply 2 // rare in Scala
// returns: Char = c

// As for all other types, apply is implicitly inserted when an object
// appears in the function position in a method call, so the line above
// can be shortened to:
abcde(2) // rare in Scala
// returns: Char = c

// One reason why random element selection is less popular for lists
// than for arrays is that xs(n) takes time proportional to the index n.
// In fact, apply is simply defined by a combination of drop and head:
xs apply n equals (xs drop n).head

// This definition also makes clear that list indices range from 0 up
// to the length of the list minus one, the same as for arrays. The indices
// method returns a list consisting of all valid indices of a given list:
abcde.indices
// returns: scala.collection.immutable.Range = Range(0, 1, 2, 3, 4)

// The flatten method takes a list of lists and flattens it out to a single list:
List(List(1, 2), List(3), List(), List(4, 5)).flatten
// returns: List[Int] = List(1, 2, 3, 4, 5)

val fruit: List[String] = List("apples", "oranges", "pears")
fruit.map(_.toCharArray).flatten
// returns: List[Char] = List(a, p, p, l, e, s, o, r, a, n, g, e, s, p, e, a, r, s)

// It can only be applied to lists whose elements are all lists.
// Trying to flatten any other list will give a compilation error:
List(1, 2, 3).flatten
//  <console>:5: error: could not find implicit value for parameter
// asTraversable: (Int) => Traversable[B] List(1, 2, 3).flatten

// The zip operation takes two lists and forms a list of pairs:
abcde.indices zip abcde
// returns: scala.collection.immutable.IndexedSeq[(Int, Char)] = IndexedSeq((0,a), (1,b), (2,c), (3,d), (4,e))

// If the two lists are of different length, any unmatched elements are dropped:
val zipped = abcde zip List(1, 2, 3)
// returns: zipped: List[(Char, Int)] = List((a,1), (b,2), (c,3))

// A useful special case is to zip a list with its index. This is done most efficiently
// with the zipWithIndex method, which pairs every element of a list with the position
// where it appears in the list.
abcde.zipWithIndex
// returns: List[(Char, Int)] = List((a,0), (b,1), (c,2), (d,3), (e,4))

// Any list of tuples can also be changed back to a tuple of lists by using the unzip method:
zipped.unzip
// returns: (List[Char], List[Int]) = (List(a, b, c), List(1, 2, 3))

// The zip and unzip methods provide one way to operate on multiple lists together.
// But there are other ways that are sometimes more concise.

// The toString operation returns the canonical string representation of a list:
abcde.toString
// returns: String = List(a, b, c, d, e)

// If you want a different representation you can use the mkString method. The operation xs mkString (pre, sep, post)
// involves four operands: the list xs to be displayed, a prefix string pre to be displayed in front of all elements,
// a separator string sep to be displayed between successive elements, and a postfix string post
// to be displayed at the end. The result of the operation is the string:
// pre + xs(0) + sep + ...+ sep + xs(xs.length - 1) + post

// The mkString method has two overloaded variants that let you drop some or all of its arguments. The first variant
// only takes a separator string:
val sep = ":separator:"
(xs mkString sep) equals (xs mkString ("", sep, ""))

// The second variant lets you omit all arguments:
xs.mkString equals (xs mkString "")

// Here are some examples:

abcde mkString ("[", ",", "]")
// returns: String = [a,b,c,d,e]

abcde mkString ""
// returns: String = abcde

abcde.mkString
// returns: String = abcde

abcde mkString ("List(", ", ", ")")
// returns: String = List(a, b, c, d, e)

// There are also variants of the mkString methods called addString which append the constructed string
// to a StringBuilder (Scala's scala.StringBuilder, not Java's java.lang.StringBuilder) object,
// rather than returning them as a result:

val buf = new StringBuilder
// returns: buf: StringBuilder =

abcde addString (buf, "(", ";", ")")
// returns: StringBuilder = (a;b;c;d;e)

// The mkString and addString methods are inherited from List’s super trait Traversable, so they are applicable
// to all other collections, as well.

// To convert data between the flat world of arrays and the recursive world of
// lists, you can use method toArray in class List and toList in class Array:
val arr = abcde.toArray
// returns: arr: Array[Char] = Array(a, b, c, d, e)

arr.toList
// returns: List[Char] = List(a, b, c, d, e)

// There’s also a method copyToArray, which copies list elements to successive array positions
// within some destination array. The operation: xs copyToArray (arr, start)
// copies all elements of the list xs to the array arr, beginning with position start.
// You must ensure that the destination array arr is large enough to hold the list in full.
// Here’s an example:
val arr2 = new Array[Int](10)
// returns: arr2: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

List(1, 2, 3) copyToArray (arr2, 3)
// arr2 is now: Array[Int] = Array(0, 0, 0, 1, 2, 3, 0, 0, 0, 0)

// Finally, if you need to access list elements via an iterator, you can use the iterator method:
val it = abcde.iterator
// returns: it: Iterator[Char] = non-empty iterator

it.next
// returns: Char = a

it.next
// returns: Char = b