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
  else xs.head :: removeDuplicates(
    for (x <- xs.tail if x != xs.head) yield x
  )
}

// removeDuplicates: [A](xs: List[A])List[A]
// scala> removeDuplicates(res3)
// res4: List[String] = List(Ullman, Jeffrey)

// For expressions are just syntactic sugar over expressions of flatMap,
// map and withFilter.

// Here are the rules for transformation:

// A for expression with one generator:
// ------------------------------------
// for (x <- expr1) yield expr2
// becomes
// expr1.map(x => expr2)

// for expressions starting with a generator and a filter:
// ------------------------------------------------------
// for (x <- expr1 if expr2) yield expr3
// becomes
// for (x <- expr1 withFilter (x => expr2)) yield expr3
// which then becomes
// expr1 withFilter (x => expr2 ) map (x => expr3)
// A more general form of this is:
// for (x <- expr1 if expr2; seq) yield expr3
// becomes
// for (x <- expr1 withFilter expr2; seq) yield expr3

// for expressions starting with two generators:
// ---------------------------------------------
// for (x <- expr1; y <- expr2; seq) yield expr3
// becomes
// expr1.flatMap(x => for (y <- expr2; seq) yield expr3)


// Putting the above rules together, here's how the following
// for expression:

for (b1 <- books; b2 <- books; if b1 != b2;
     a1 <- b1.authors;
     a2 <- b2.authors; if a1 == a2)
yield a1

// translates into invocations of map, withFilter and flatMap:

books flatMap (b1 =>
  books withFilter (b2 => b1 != b2) flatMap (b2 =>
    b1.authors flatMap (a1 =>
      b2.authors withFilter (a2 => a1 == a2) map (a2 =>
        a1))))
