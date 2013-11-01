// Arrays can be compatible with sequences and how they can support all sequence operations.
// But, what about genericity? In Java you cannot write a T[] where T is a type parameter.
// How then is Scala's Array[T] represented? In fact a generic array like Array[T] could be at run-time
// any of Java's eight primitive array types byte[], short[], char[], int[], long[], float[], double[],
// boolean[], or it could be an array of objects. The only common run-time type encompassing all of these
// types is AnyRef (or, equivalently java.lang.Object), so that's the type to which the Scala compiler
// maps Array[T]. At run-time, when an element of an array of type Array[T] is accessed or updated there
// is a sequence of type tests that determine the actual array type, followed by the correct array
// operation on the Java array. These type tests slow down array operations somewhat. You can expect
// accesses to generic arrays to be three to four times slower than accesses to primitive or object
// arrays. This means that if you need maximal performance, you should prefer concrete
// over generic arrays.

// Representing the generic array type is not enough, however, There must also be a way to create
// generic arrays. This is an even harder problem, which requires a little bit of help from you.
// To illustrate the problem, consider the following attempt to write a generic method that creates
// an array:

// This is wrong!
def evenIndexes [T] (xs: Vector[T]): Array[T] = {
  val array = new Array[T]((xs.length + 1) / 2)

  for (i <- 0 until xs.length by 2)
    array(i / 2) = xs(i)

  array
}

// The evenIndexes method returns a new array that consists of all elements of the argument vector xs
// that are at even positions in the vector. The first line of the body of evenIndexes creates the
// result array, which has the same element type as the argument. So depending on the actual type
// parameter for T, this could be an Array[Int], or an Array[Boolean], or an array of some of the
// other primitive types in Java, or an array of some reference type. But these types all have different runtime representations,
// so how is the Scala runtime going to pick the correct one? In fact, it can't do that based on the
// information it is given, because the actual type that corresponds to the type parameter T is erased
// at runtime. That's why you will get the following error message if you attempt to compile the code
// above:

// error: cannot find class tag for element type T
// val array = new Array[T]((xs.length + 1) / 2)

// What's required here is that you help the compiler by providing a runtime hint of what the actual type
// parameter of evenIndexes is. This runtime hint takes the form of a class tag of type
// scala.reflect.ClassTag. A class tag is a type descriptor object that describes what the
// top-level class of a type is. Alternatively to class tags there are also full tags of type
// scala.reflect.Manifest, which describe all aspects of a type. But for array creation, only class
// tags are needed.

// The Scala compiler will generate code to construct and pass class tags automatically if you
// instruct it to do so. "Instructing" means that you demand a class tag as an implicit parameter,
// like this:

import scala.reflect.ClassTag
def evenIndexes [T] (xs: Vector[T]) (implicit m: ClassTag[T]): Array[T] = {
  val array = new Array[T]((xs.length + 1) / 2)

  for (i <- 0 until xs.length by 2)
    array(i / 2) = xs(i)

  array
}

// Using an alternative and shorter syntax, you can also demand that the type comes with a class tag by
// using a context bound. This means following the type with a colon and the class name ClassTag,
// like this:

// This works
def evenIndexes [T: ClassTag] (xs: Vector[T]): Array[T] = {
  val arr = new Array[T]((xs.length + 1) / 2)
  for (i <- 0 until xs.length by 2)
    arr(i / 2) = xs(i)
  arr
}

// The two revised versions of evenIndexes mean exactly the same. What happens in either case is that
// when the Array[T] is constructed, the compiler will look for a class manifest for the type parameter
// T, that is, it will look for an implicit value of type ClassTag[T]. If such a value is found,
// the manifest is used to construct the right kind of array. Otherwise, you'll see an error message
// like the one shown previously.

// In summary, generic array creation demands class manifests. Whenever you create an array of a type
// parameter T, you also need to provide an implicit class manifest for T. The easiest way to do this is
// to declare the type parameter with a ClassTag context bound, as in [T: ClassTag].
