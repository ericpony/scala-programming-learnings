val numbers = List(-11, -10, -5, 0, 5, 10)

// (x: Int) => println(x) is a function literal that prints integers, it will be applied to every element of numbers
numbers.foreach((x: Int) => println(x))

// filter the elements from numbers that are not greater than 0:
numbers.filter((x: Int) => x > 0)

// Both of the above function literals could be shortened by leaving off their parameter types, which can be inferred:
numbers.foreach(x => println(x))

// Use the placeholder syntax to make the code even more concise, for function literals that were just one expression
numbers.filter(_ > 0)
