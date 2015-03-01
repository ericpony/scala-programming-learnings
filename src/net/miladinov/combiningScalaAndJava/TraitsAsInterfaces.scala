package net.miladinov.combiningScalaAndJava

object TraitsAsInterfaces {
  // Compiling any trait creates a Java interface of the same name. This interface is usable as a Java type,
  // and it lets you call methods on Scala objects through variables of that type.

  // Implementing a trait in Java is another story. In the general case it is not practical. One special case
  // is important, however. If you make a Scala trait that includes only abstract methods, then that trait will be
  // translated directly to a Java interface, with no other code to worry about.
  // Essentially this means that you can write a Java interface in Scala syntax if you like.
}
