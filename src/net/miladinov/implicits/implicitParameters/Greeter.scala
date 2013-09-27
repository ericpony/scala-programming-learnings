package net.miladinov.implicits.implicitParameters

// The remaining place the compiler inserts implicits is within argument lists.
// The compiler will sometimes replace someCall(a) with someCall(a)(b), or new SomeClass(a)
// with new SomeClass(a)(b), thereby adding a missing parameter list to complete a function
// call. It is the entire last curried parameter list that’s supplied, not just the last
// parameter. For example, if someCall's missing last parameter list takes three parameters,
// the compiler might replace someCall(a) with someCall(a)(b, c, d). For this usage, not only
// must the inserted identifiers, such as b, c, and d in (b, c, d), be marked implicit where
// they are defined, but also the last parameter list in someCall's or someClass's definition
// must be marked implicit. Here’s a simple example. Suppose you have a class PreferredPrompt,
// which encapsulates a shell prompt string (such as, say "$ " or "> ") that is preferred by
// a user:

class PreferredPrompt (val preference: String) {
  override def toString = preference
}

object Greeter {
  def greet (name: String)(implicit prompt: PreferredPrompt) {
    println(s"Welcome, $name. The system is ready.")
    println(prompt)
  }
}

// The last parameter list is marked implicit, which means it can be supplied
// implicitly. But you can still provide the prompt explicitly, like this:
//
// scala> val bobsPrompt = new PreferredPrompt("relax> ")
// bobsPrompt: PreferredPrompt = PreferredPrompt@74a138
//
// scala> Greeter.greet("Bob")(bobsPrompt)
// Welcome, Bob. The system is ready.
// relax>
//
// To let the compiler supply the parameter implicitly, you must first define
// a variable of the expected type, which in this case is PreferredPrompt.
// You could do this, for example, in a preferences object:

object DansPreferences {
  implicit val prompt = new PreferredPrompt("danielm@host $")
}

// Note that the val itself is marked implicit. If it wasn't, the compiler would not use
// it to supply the missing parameter list. It will also not use it if it isn’t in scope
// as a single identifier. For example:
//
// scala> Greeter.greet("Joe")
// <console>:10: error: could not find implicit value for
// parameter prompt: PreferredPrompt
// Greeter.greet("Joe")
//              ˆ
// Once you bring it into scope via an import, however, it will be used to supply
// the missing parameter list:
// scala> import DansPreferences._
// import DansPreferences._
// scala> Greeter.greet("Danny")
// Welcome, Danny. The system is ready.
// danielm@host $
