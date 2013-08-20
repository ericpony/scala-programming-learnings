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

// Class List provides fast access to the head of the list, but not the end. Thus, when you need to build a list
// by appending to the end, you should consider building the list backwards by prepending elements to the front,
// then when you’re done, calling reverse to get the elements in the order you need.
//
// Another alternative, which avoids the reverse operation, is to use a ListBuffer. A ListBuffer is a mutable object
// (contained in package scala.collection.mutable), which can help you build lists more effi- ciently when you need
// to append. ListBuffer provides constant time ap- pend and prepend operations. You append elements with  the +=
// operator, and prepend them with the +=: operator. When you’re done building, you can obtain a List by invoking
// toList on the ListBuffer. Here’s an example:
import scala.collection.mutable.ListBuffer
val buf = new ListBuffer[Int]
// returns buf: scala.collection.mutable.ListBuffer[Int] = ListBuffer()

buf += 1
// returns: buf.type = ListBuffer(1)

buf += 2
// returns: buf.type = ListBuffer(1, 2)

buf
// returns: scala.collection.mutable.ListBuffer[Int]  = ListBuffer(1, 2)

3 +=: buf
// returns: buf.type = ListBuffer(3, 1, 2)

buf.toList
// returns: List[Int] = List(3, 1, 2)

// Another reason to use ListBuffer instead of List is to prevent the potential for stack overflow. If you can build
// a list in the desired order by prepending, but the recursive algorithm that would be required is not tail recursive,
// you can use a for expression or while loop and a ListBuffer instead.

// An ArrayBuffer is like an array, except that you can additionally add and remove elements from the beginning and
// end of the sequence. All Array operations are available, though they are a little slower due to a layer of wrapping
// in the implementation. The new addition and removal operations are constant time on average, but occasionally require
// linear time due to the implementation needing to allocate a new array to hold the buffer’s contents.
//
// To use an ArrayBuffer, you must first import it from the mutable collections package:
import scala.collection.mutable.ArrayBuffer

// When you create an ArrayBuffer, you must specify a type parameter, but need not specify a length. The ArrayBuffer
// will adjust the allocated space automatically as needed:

val arr = new ArrayBuffer[Int]()
// returns: arr: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()

// You can append to an ArrayBuffer using the += method:
arr += 12
// returns: arr.type = ArrayBuffer(12)

arr += 15
// returns: arr.type = ArrayBuffer(12, 15)

arr
// returns: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(12, 15)

// All the normal array methods are available. For example, you can ask an ArrayBuffer its length, or you can retrieve
// an element by its index:

arr.length
// returns: Int = 2

arr(0)
// returns: Int = 12

// One other sequence to be aware of is StringOps, which implements many sequence methods. Because Predef has an
// implicit conversion from String to StringOps, you can treat any string like a sequence. Here’s an example:
def hasUpperCase(s: String) = s.exists(_.isUpper)
// returns: hasUpperCase: (s: String)Boolean

hasUpperCase("Robert Frost")
// returns: Boolean = true

hasUpperCase("e e cummings")
// returns: Boolean = false

// In this example, the exists method is invoked on the string named s in the hasUpperCase method body.
// Because no method named "exists" is declared in class String itself, the Scala compiler will implicitly convert
// s to StringOps, which has the method. The exists method treats the string as a sequence of characters, and will
// return true if any of the characters are upper case.

// If you want to use both mutable and immutable sets or maps in the same source file, one approach is to import
// the name of the package that contains the mutable variants:
import scala.collection.mutable

// You can continue to refer to the immutable set as Set, as before, but can now refer to the mutable set as
// mutable.Set. Here’s an example:
val mutableSet = mutable.Set(1, 2, 3)
// returns: mutableSet: scala.collection.mutable.Set[Int] = Set(1, 2, 3)

val numbers = Set(1, 2, 3)
// Creates an immutable set (numbers.toString returns Set(1, 2, 3))

numbers+5
// Adds an element (returns Set(1, 2, 3, 5))

numbers-3
// Removes an element (returns Set(1, 2))

numbers ++ List(5, 6)
// Adds multiple elements (returns Set(1, 2, 3, 5, 6))

numbers -- List(1, 2)
// Removes multiple elements (returns Set(3))

numbers & Set(1, 3, 5, 7)
// Takes the intersection of two sets (returns Set(1, 3))

numbers.size
// Returns the size of the set (returns 3)

numbers.contains(3)
// Checks for inclusion (returns true)

import scala.collection.mutable
// Makes the mutable collections easy to access

val words = mutable.Set.empty[String]
// Creates an empty, mutable set (words.toString returns Set())

words += "the"
// Adds an element (words.toString returns Set(the))

words -= "the"
// Removes an element, if it exists (words.toString returns Set())
