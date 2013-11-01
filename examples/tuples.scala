// Tuples are easy to create, just place objects inside parentheses, comma-separated
val pair = (99, "balloons")
// Once you have a tuple, you can access its elements individually with a dot underscore and
// 1-based index of the element.
println(pair._1)
println(pair._2)

// The actual type of a tuple depends on the number of elements it contains and their types.
// So, (99, "balloons") is Tuple2[Int, String].
// The type of ('u', 'r', "the", 1, 4, "me") is Tuple6[Char, Char, String, Int, Int, String].

/*
Accessing the elements of a tuple

You may be wondering why you can't access the elements of a tuple
like the elements of a list, for example, with "pair(0)". The reason
is that a list’s apply method always returns the same type, but each
element of a tuple may be a different type: _1 can have one result type,
_2 another, and so on. These _N numbers are one-based, instead of
zero-based, because starting with 1 is a tradition set by other languages
with statically typed tuples, such as Haskell and ML.
 */
