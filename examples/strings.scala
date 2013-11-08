// Like arrays, strings are not directly sequences, but they can be converted to them, and they also
// support all sequence operations. Here are some examples of operations you can invoke on strings:

val str = "hello"
// returns: str: String = hello

str.reverse
// returns: String = olleh

str.map(_.toUpper)
// returns: String = HELLO

str drop 3
// returns: String = lo

str slice (1, 4)
// returns: String = ell

val chars: Seq[Char] = str
// returns: Seq[Char] = hello

// These operations are supported by two implicit conversions, which were explained earlier. The first,
// low-priority conversion maps a String to a WrappedString, which is a subclass of
// immutable.IndexedSeq. This conversion was applied in the last line of the previous example
// in which a string was converted into a Seq. The other, high-priority conversion maps a string to a
// StringOps object, which adds all methods on immutable sequences to strings. This conversion was
// implicitly inserted in the method calls of reverse, map, drop, and slice in the previous example.
