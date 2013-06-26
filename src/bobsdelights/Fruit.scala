// easy access to Fruit
import bobsdelights.Fruit
// easy access to all members of bobsdelights
import bobsdelights._
// easy access to all members of Fruits
import bobsdelights.Fruits._

package bobsdelights {
  abstract class Fruit (
    val name: String,
    val color: String
  )

  object Fruits {
    object Apple extends Fruit("apple", "red")
    object Orange extends Fruit("orange", "orange")
    object Pear extends Fruit("pear", "pear-colored")
    val menu = List(Apple, Orange, Pear)

    def showFruit(fruit: Fruit) {
      import fruit._
      println(name + "s are " + color)
    }
  }
}
