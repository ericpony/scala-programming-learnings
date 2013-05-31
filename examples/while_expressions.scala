
def gcdLoop(x: Long, y: Long): Long = {
  var a = x
  var b = y

  while (a != 0) {
    val temp = a
    a = b % a
    b = temp
  }

  b
}

// Determine the GCD with recursion instead of looping
def gcd(x: Long, y: Long): Long =
  if (y == 0) x else gcd(y, x % y)

var line = ""

do {
  line = readLine()
  println("Read: " + line)
} while (line != "")
