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
