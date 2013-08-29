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

// Makes the mutable collections easy to access

val words = mutable.Set.empty[String]
// Creates an empty, mutable set (words.toString returns Set())

words += "the"
// Adds an element (words.toString returns Set(the))

words -= "the"
// Removes an element, if it exists (words.toString returns Set())

words ++= List("do", "re", "mi")
// Adds multiple elements (words.toString returns Set(do, re, mi))

words --= List("do", "re")
// Removes multiple elements (words.toString returns Set(mi))

words.clear
// Removes all elements (words.toString returns Set())
￼
// Maps let you associate a value with each element of the collection. Using a map looks similar to using an array,
// except that instead of indexing with integers counting from 0, you can use any kind of key. If you import
// the scala.collection.mutable package, you can create an empty mutable map like this:
val map = mutable.Map.empty[String, Int]
// returns: map: scala.collection.mutable.Map[String,Int] = Map()

// Note that when you create a map, you must specify two types. The first type is for the keys of the map, the second
// for the values. In this case, the keys are strings and the values are integers. Setting entries in a map looks
// similar to setting entries in an array:
map("hello") = 1
map("there") = 2
map
// returns: scala.collection.mutable.Map[String,Int] = Map(hello -> 1, there -> 2)

// Likewise, reading a map is similar to reading an array:
map("hello")
// returns: Int = 1

// Putting it all together, here is a method that counts the number of times each word occurs in a string:
def countWords(text: String) = {
  val counts = mutable.Map.empty[String, Int]
  for (rawWord <- text.split("[ ,!.]+")) {
    val word = rawWord.toLowerCase
    val oldCount = if (counts.contains(word)) counts(word) else 0
    counts += (word -> (oldCount + 1))
  }
  counts
}

countWords("See Spot run! Run, Spot. Run!")
// returns: scala.collection.mutable.Map[String,Int] = Map(spot -> 2, see -> 1, run -> 3)

// Given these counts, you can see that this text talks a lot about running, but not so much about seeing.
// The way this code works is that a mutable map, named counts, maps each word to the number of times it occurs
// in the text. For each word in the text, the word’s old count is looked up, that count is incremented by one,
// and the new count is saved back into counts. Note the use of contains to check whether a word has been seen
// yet or not. If counts.contains(word) is not true, then the word has not yet been seen and zero is used for the count.

// Many of the most commonly used methods on both mutable and immutable maps are shown here:

val numbersMap = Map("i" -> 1, "ii" -> 2)
// Creates an immutable map (numbersMap.toString returns Map(i -> 1, ii -> 2))

numbersMap + ("vi" -> 6)
// Adds an entry (returns Map(i -> 1, ii -> 2, vi -> 6))

numbersMap - "ii"
// Removes an entry (returns Map(i -> 1))

numbersMap ++ List("iii" -> 3, "v" -> 5)
// Adds multiple entries (returns Map(i -> 1, ii -> 2, iii -> 3, v -> 5))

numbersMap -- List("i", "ii")
// Removes multiple entries (returns Map())

numbersMap.size
// Returns the size of the map (returns 2)

numbersMap.contains("ii")
// Checks for inclusion (returns true)

numbersMap("ii")
// Retrieves the value at a specified key (returns 2)

numbersMap.keys
// Returns the keys (returns an Iterable over the strings "i" and "ii")

numbersMap.keySet
// Returns the keys as a set (returns Set(i, ii))

numbersMap.values
// Returns the values (returns an Iterable over the integers 1 and 2)

numbersMap.isEmpty
// Indicates whether the map is empty (returns false)

import scala.collection.mutable
// Makes the mutable collections easy to access

val wordsMap = mutable.Map.empty[String, Int]
// Creates an empty, mutable map

wordsMap += ("one" -> 1)
// Adds a map entry from "one" to 1 (wordsMap.toString returns Map(one -> 1))

wordsMap -= "one"
// Removes a map entry, if it exists (wordsMap.toString returns Map())

wordsMap ++= List("one" -> 1, "two" -> 2, "three" -> 3)
// Adds multiple map entries (wordsMap.toString returns Map(one -> 1, two -> 2, three -> 3))

wordsMap --= List("one", "two")
// Removes multiple objects (wordsMap.toString returns Map(three -> 3))

// Here are some examples of TreeSet, which is the implementation of OrderedSet:
import scala.collection.immutable.TreeSet
val ts = TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
// returns: ts: scala.collection.immutable.TreeSet[Int] = TreeSet(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

val cs = TreeSet('f', 'u', 'n')
// returns cs: scala.collection.immutable.TreeSet[Char] = TreeSet(f, n, u)

// And here are a few TreeMap examples, which is the implementation of OrderedMap:
import scala.collection.immutable.TreeMap
var tm = TreeMap(3 -> 'x', 1 -> 'x', 4 -> 'x')
// returns: tm: scala.collection.immutable.TreeMap[Int,Char] = Map(1 -> x, 3 -> x, 4 -> x)
tm += (2 -> 'x')

tm
// returns: scala.collection.immutable.TreeMap[Int,Char] = Map(1 -> x, 2 -> x, 3 -> x, 4 -> x)

// To make it easier to switch from immutable to mutable collections, and vice versa, Scala used to provide
// some syntactic sugar. Even though immutable sets and maps do not support a true += method, Scala gave
// a useful alternate interpretation to +=. Whenever you wrote a += b, and a did not support a method named +=,
// Scala used to try interpreting it as a = a + b. For example, immutable sets do not support a += operator:

val people = Set("Nancy", "Jane")
// returns: people: scala.collection.immutable.Set[String] = Set(Nancy, Jane)

people += "Bob"
// error: value += is not a member of scala.collection.immutable.Set[String]
// people += "Bob"
//        ^

// If you declare people as a var, instead of a val, however, then the collection can be “updated” with a += operation,
// even though it is immutable. First, a new collection will be created, and then people will be reassigned to refer
// to the new collection:

var mutablePeople = Set("Nancy", "Jane")
// returns: mutablePeople: scala.collection.immutable.Set[String] = Set(Nancy, Jane)

mutablePeople += "Bob"
// returns: mutablePeople: scala.collection.immutable.Set[String] = Set(Nancy, Jane, Bob)

// After this series of statements, the people variable refers to a new immutable set, which contains the added string,
// "Bob". The same idea applies to any method ending in =, not just the += method. Here’s the same syntax used with
// the -= operator, which removes an element from a set, and the ++= operator, which adds a collection of elements
// to a set:

mutablePeople -= "Jane"
mutablePeople ++= List("Tom", "Harry")
mutablePeople
// returns: scala.collection.immutable.Set[String] = Set(Nancy, Bob, Tom, Harry)

// To see how this is useful, consider again the following Map example from earlier:
var capital = Map("US" -> "Washington", "France" -> "Paris")
capital += ("Japan" -> "Tokyo")
println(capital("France"))

// This code uses immutable collections. If you want to try using mutable col- lections instead,
// all that is necessary is to import the mutable version of Map, thus overriding the default
// import of the immutable Map:
import scala.collection.mutable.Map  // only change needed!
var mutableCapital = Map("US" -> "Washington", "France" -> "Paris")
mutableCapital += ("Japan" -> "Tokyo")
println(mutableCapital("France"))

// Not all examples are quite that easy to convert, but the special treatment of methods ending in an equals sign
// will often reduce the amount of code that needs changing.

// By the way, this syntactic treatment works on any kind of value, not just collections. For example,
// here it is being used on floating-point numbers:

var roughlyPi = 3.0
// returns: roughlyPi: Double = 3.0

roughlyPi += 0.1
roughlyPi += 0.04
roughlyPi
// returns: Double = 3.14

// The effect of this expansion is similar to Java's assignment operators +=, -=, *=, etc., but it is more general
// because every operator ending in = can be converted.

// Sometimes, you need to specify the type of the collection you want, when the compiler would choose a different type
// than what you intended.
val stuff = mutable.Set(42)
// returns: stuff: scala.collection.mutable.Set[Int] = Set(42)


// Suppose you wanted to add a string to this Set of stuff, now you couldn't:
stuff += "blah"
// <console>:11: error: type mismatch;
//   found   : String("blah")
//   required: Int
//   stuff += "blah"

// The problem here is that stuff was given an element type of Int. If you want it to have an element type of Any,
// you need to say so explicitly by putting the element type in square brackets, like this:

val anyStuff = mutable.Set[Any](42)
anyStuff += "blah"
anyStuff
// returns: anyStuff.type = Set(blah, 42)

// Another special situation is if you want to initialize a collection with another collection.
// For example, imagine you have a list, but you want a TreeSet containing the elements in the list. Here’s the list:
val someColors = List("blue", "yellow", "red", "green")
// someColors: List[String] = List(blue, yellow, red, green)

// You cannot pass the colors list to the factory method for TreeSet:

import scala.collection.immutable.TreeSet
val colorsSet = TreeSet(someColors)
// <console>:11: error: No implicit Ordering defined for List[String].
// val colorsSet = TreeSet(someColors)
//                        ^

// Instead, you’ll need to create an empty TreeSet[String] and add to it the elements of the list
// with the TreeSet’s ++ operator:

val treeSet = TreeSet[String]() ++ someColors
// returns treeSet: scala.collection.immutable.TreeSet[String] = TreeSet(blue, green, red, yellow)
