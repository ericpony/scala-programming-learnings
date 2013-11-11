// Many transformer functions on such views provide a window into the original sequence
// that can then be used to update selectively some elements of that sequence. To see this
// in an example, suppose you have an array arr:

val array = (0 to 9).toArray
// array: Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

// You can create a subwindow into that array by creating a slice of a view of the array:
val subArray = array.view.slice(3, 6)
// subArray: scala.collection.mutable.IndexedSeqView[Int,Array[Int]] = SeqViewS(...)

// This gives a view, subArray, which refers to the elements at positions 3 through 5 of
// the array. The view does not copy these elements, it just provides a reference to them.
// Now, assume you have a method that modifies some elements of a sequence. For instance,
// the following negate method would negate all elements of the sequence of integers it's
// given:

def negate (xs: collection.mutable.Seq[Int]) =
  for (i <- 0 until xs.length) xs(i) = -xs(i)
// negate: (xs: scala.collection.mutable.Seq[Int])Unit

// Assume now you want to negate elements at positions three through five of the array.
// Can you use negate for this? Using a view, this is simple:

negate(subArray)
array
// res1: Array[Int] = Array(0, 1, 2, -3, -4, -5, 6, 7, 8, 9)

// What happened here is that negate changed all elements of subArray, which were a slice
// of the elements of array. Again, you see that views help in keeping things modular.
// The code above nicely separated the question of what index range to apply a method to
// from the question what method to apply.
