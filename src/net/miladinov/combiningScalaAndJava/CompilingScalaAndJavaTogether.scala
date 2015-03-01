package net.miladinov.combiningScalaAndJava

object CompilingScalaAndJavaTogether {
  // Usually when you compile Scala code that depends on Java code, you first build the Java code to class files.
  // You then build the Scala code, putting the Java code's class files on the classpath. This approach doesn't work,
  // however, if the Java code has references back into the Scala code. In such a case, no matter which order you
  // compile the code, one side or the other will have unsatisfied external references. Such a situation isn't unusual,
  // either. All it takes is a mostly Java project where you replace one Java file with a Scala file.

  // To support such builds, Scala allows compiling against Java source code as well as Java class files. All you have
  // to do is put the Java source files on the command line as if they were Scala files. The Scala compiler won’t
  // compile those Java files, but it will scan them to see what they contain. To use this facility, you first compile
  // the Scala code using Java source files, and then compile the Java code using Scala class files.

  // Here is a typical sequence of commands:

  // $ scalac -d bin InventoryAnalysis.scala InventoryItem.java Inventory.java
  // $ javac -cp bin -d bin Inventory.java InventoryItem.java InventoryManagement.java
  // $ scala -cp bin InventoryManagement
}
