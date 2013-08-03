// The operation xs map f takes as operands a list xs of type List[T] and
// a function f of type T => U. It returns the list resulting from applying the function
// f to each list element in xs. For instance:
List(1, 2, 3) map (_ + 1)
// returns: List[Int] = List(2, 3, 4)

val words = List("the", "quick", "brown", "fox")
// returns: words: List[String] = List(the, quick, brown, fox)

words map (_.length)
// returns: List[Int] = List(3, 5, 5, 3)

words map (_.reverse.mkString)
// returns: List[String] = List(eht, kciuq, nworb, xof)

