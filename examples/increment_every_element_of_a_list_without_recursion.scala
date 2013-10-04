// Here's a very inefficient approach to incrementing every element in a list
// of arbitrary size (as much as the heap allows):

def incrementAllIteratively(xs: List[Int]): List[Int] = {
  var result = List[Int]()
  for (x <- xs) result = result ::: List(x + 1)
  result
}
