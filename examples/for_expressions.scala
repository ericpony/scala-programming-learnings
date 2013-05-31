val filesHere = (new java.io.File(".")).listFiles

for (file <- filesHere)
  println(file)

for (i <- 1 to 4)
  println("Iteration " + i)

for (i <- 1 until 4)
  println("Iteration " + i)
