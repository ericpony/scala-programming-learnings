val greetStrings: Array[String] = new Array[String](3)

// greetStrings can never refer to another Array[String], but
// the array itself is mutable, as you can see below
greetStrings(0) = "Hello"
greetStrings(2) = ", "
greetStrings(3) = "World!"

for (i <- 0 to 2) {
  print(greetStrings(i))
}

val numNames = Array("zero", "one", "two")

val oneTwo = List(1, 2)
val threeFour = List(3, 4)
val oneTwoThreeFour = oneTwo ::: threeFour

// oneTwo and threeFour were not mutated.
// Thus, oneTwoThreeFour is a new list.

val twoThree = List(2, 3)
val oneTwoThree = 1 :: twoThree
