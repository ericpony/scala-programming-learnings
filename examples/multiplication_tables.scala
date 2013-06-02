// Return a row as a sequence
def makeRowSequence(row: Int) =
  for (col <- 1 to 10) yield {
    val product = (row * col).toString
    val padding = " " * (4 - product.length)
    padding + product
  }

// Return a row as a string
def makeRow(row: Int) = makeRowSequence(row).mkString

// Return a multiplication table as a string with one row per line
def multiplicationTable(): String = {
  val tableSequence =
    for (row <- 1 to 10)
    yield makeRow(row)
  tableSequence.mkString("\n")
}

println("\n" concat multiplicationTable())
