import java.io.FileReader

val file = new FileReader("input.txt")

try {
  // use the file
} finally {
  file.close() // Be sure to close the file
}
