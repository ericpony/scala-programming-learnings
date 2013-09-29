package net.miladinov.implicits.implicitParameters

// Note that the implicit keyword applies to an entire parameter list, not to individual
// parameters. Here is an example in which the last parameter list of Greeter’s greet method,
// which is again marked implicit, has two parameters: prompt (of type PreferredPrompt) and
// drink (of type PreferredDrink):

class PreferredPrompt (val preference: String) {
  override def toString = preference
}

class PreferredDrink (val preference: String) {
  override def toString: String = preference
}

object Greeter {
  def greet (name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink) {
    println(s"Welcome, $name. The system is ready.")
    println(s"But while you work, why not enjoy a cup of $drink?")
    println(prompt)
  }
}

object DansPreferences {
  implicit val prompt = new PreferredPrompt("danielm@slimpickings $")
  implicit val drink = new PreferredDrink("iced coffee")
}

// The implicits need to be in scope as single identifiers (no dots), or they won't be used:
// scala>  Greeter.greet("Danny")
// <console>:11: error: could not find implicit value for parameter prompt: PreferredPrompt
// Greeter.greet("Danny")
//              ^
//
// scala>  import DansPreferences._
// import DansPreferences._
//
// scala>  Greeter.greet("Danny")
// Welcome, Danny. The system is ready.
// But while you work, why not enjoy a cup of iced coffee?
// danielm@slimpickings $