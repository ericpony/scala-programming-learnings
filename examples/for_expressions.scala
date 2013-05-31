val filesHere = (new java.io.File(".")).listFiles

for (
  file <- filesHere
  if file.isFile
  if file.getName.endsWith(".scala")
) println(file)

for (i <- 1 to 4)
  println("Iteration " + i)

for (i <- 1 until 4)
  println("Iteration " + i)


def fileLines(file: java.io.File) =
  scala.io.Source.fromFile(file).getLines().toList

def grep(pattern: String) {
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")

    line <- fileLines(file)
    if line.trim.matches(pattern)
  } println(file + ": " + line.trim)
}

grep(".*gcd.*")
