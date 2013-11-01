// Scala has a standard type named Option for optional values.
// Such a value can be of two forms. It can be of the form Some(x)
// where x is the actual value. Or it can be the None object,
// which represents a missing value. Optional values are produced
// by some of the standard operations on Scala's collections. For
// instance, the get method of Scala’s Map produces Some(value)
// if a value corresponding to a given key has been found, or
// None if the given key is not defined in the Map. Here’s an example:
val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo", "Serbia" -> "Belgrade")

capitals get "France" // returns Option[String] Some("Paris")
capitals get "North Pole" // returns Option[String] None

// The most common way to take optional values apart
// is through pattern match. For instance:
def show(x: Option[String]) = x match {
  case Some(s) => s
  case None => "?"
}

// The Option type is used frequently in Scala programs. Compare
// this to the dominant idiom in Java of using null to indicate no value.
// For example, the get method of java.util.HashMap returns either a
// value stored in the HashMap, or null if no value was found. This
// approach works for Java, but is error prone, because it is difficult
// in practice to keep track of which variables in a program are allowed
// to be null. If a variable is allowed to be null, then you must remember
// to check it for null every time you use it. When you forget to check,
// you open the possibility that a NullPointerException may result at runtime.
// Because such exceptions may not happen very often, it can be difficult
// to discover the bug during testing. For Scala, the approach would not
// work at all, because it is possible to store value types in hash maps,
// and null is not a legal element for a value type. For instance, a
// HashMap[Int, Int] cannot return null to signify "no element."
// By contrast, Scala encourages the use of Option to indicate an optional value.
// This approach to optional values has several advantages over Java’s.
// First, it is far more obvious to readers of code that a variable whose
// type is Option[String] is an optional String than a variable of type String,
// which may sometimes be null. But most importantly, that programming
// error described earlier of using a variable that may be null without
// first checking it for null becomes in Scala a type error. If a variable
// is of type Option[String] and you try to use it as a String, your Scala
// program will not compile.
