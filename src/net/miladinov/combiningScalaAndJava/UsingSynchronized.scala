package net.miladinov.combiningScalaAndJava

object UsingSynchronized {
  // For compatibility's sake, Scala provides access to Java's concurrency primitives. The wait, notify and notifyAll
  // methods can be called in Scala, and they have the same meaning as in Java. Scala doesn't technically have a
  // synchronized keyword, but it includes a predefined synchronized method that can be called as follows:

  var counter = 0
  synchronized {
    // One thread in here at a time
    counter = counter + 1
  }
}
