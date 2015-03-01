package net.miladinov.combiningScalaAndJava

object GeneralRules {
  // Scala is implemented as a translation to standard Java bytecodes. As much as possible, Scala features map directly
  // onto the equivalent Java features. Scala classes, methods, strings, exceptions, for example, are all compiled to
  // the same in Java bytecode as their Java counterparts.

  // To make this happen required an occasional hard choice in the design of Scala. For example, it might have been
  // nice to resolve overloaded methods at run time, using run-time types, rather than at compile time. Such a design
  // would break with Java's, however, making it much trickier to mesh Java and Scala. In this case, Scala stays
  // with Java's overloading resolution, and thus Scala methods and method calls can map directly to Java methods
  // and method calls.

  // For other features Scala has its own design. For example, traits have no equivalent in Java. Similarly, while both
  // Scala and Java have generic types, the details of the two systems clash. For language features like these, Scala
  // code cannot be mapped directly to a Java construct, so it must be encoded using some combination
  // of the structures Java does have.

  // For these features that are mapped indirectly, the encoding is not fixed. There is an ongoing effort to make
  // the translations as simple as possible, so by the time you read this, some details may be different than at the
  // time of writing. You can find out what translation your current Scala compiler uses by examining the ".class"
  // files with tools like javap.
}
