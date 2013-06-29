// Only import Apple and Orange from bobsdelights.Fruits
import bobsdelights.Fruits.{Apple, Orange}

object HidingImports {
  // This object exists solely to demonstrate how you can selectively import an object's
  // or package's members, thereby hiding the rest
  val apple = Apple
  val orange = Orange
}
