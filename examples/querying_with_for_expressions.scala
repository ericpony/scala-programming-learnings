// Here is a small example database, represented as an in-memory list:

val books: List[Book] =
  List(
    Book(
      "Structure and Interpretation of Computer Programs",
      "Abelson, Harold", "Sussman, Gerald J."
    ),
    Book(
      "Principles of Compiler Design",
      "Aho, Alfred", "Ullman, Jeffrey"
    ),
    Book(
      "Programming in Modula-2",
      "Wirth, Niklaus"
    ),
    Book(
      "Elements of ML Programming",
      "Ullman, Jeffrey"
    ),
    Book(
      "The Java Language Specification",
      "Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
    )
  )

// Then, to find the titles of all books whose
// author's last name is "Gosling":
for (book <- books; author <- book.authors; if author startsWith "Gosling")
  yield book.title

// scala> for (book <- books; author <- book.authors; if author startsWith "Gosling")
// yield book
//    | res1: List[String] = List(The Java Language Specification)

// Or, to find the titles of all books that have the string "Program" in their title:
for (book <- books; if book.title contains "Program")
  yield book.title

// scala> for (book <- books; if book.title contains "Program")
// yield book
//    | res2: List[String] = List(
//      Structure and Interpretation of Computer Programs,
//      Programming in Modula-2,
//      Elements of ML Programming,
//    )

// Or, to find the names of all authors that have written
// at least two books in the database:
for (b1 <- books; b2 <- books; if b1 != b2;
     a1 <- b1.authors;
     a2 <- b2.authors; if a1 == a2)
  yield a1

// scala> for (b1 <- books; b2 <- books; if b1 != b2;
//      a1 <- b1.authors;
//      a2 <- b2.authors; if a1 == a2)
//   yield a1
// |      |      | res3: List[String] = List(Ullman, Jeffrey, Ullman, Jeffrey)

// The last solution is not yet perfect, because authors will appear several
// times in the list of results. You still need to remove duplicate authors
// from result lists. This can be achieved with the following function:

def removeDuplicates [A] (xs: List[A]): List[A] = {
  if (xs.isEmpty) xs
  else xs.head :: removeDuplicates(xs.tail filter (x => x != xs.head))
}

// removeDuplicates: [A](xs: List[A])List[A]
// scala> removeDuplicates(res3)
// res4: List[String] = List(Ullman, Jeffrey)
