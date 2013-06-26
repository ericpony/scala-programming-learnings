// easy access to Fruit
import bobsdelights.Fruit

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
  }
}
