val filesHere = (new java.io.File(".")).listFiles

val forLineLengths =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")

    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(".*for.*")
  } yield trimmed.length

def scalaFiles =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
  } yield file

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
    trimmed = line.trim
    if trimmed.matches(pattern)
  } println(file + ": " + trimmed)
}

grep(".*gcd.*")
