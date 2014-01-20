package net.miladinov.recipe

object SimpleDatabase extends Database {
  def allFoods = List(Apple, Orange, Cream, Sugar)

  def allRecipes: List[Recipe] = List(FruitSalad)

  private var categories = List(
    FoodCategory("fruits", List(Apple, Orange)),
    FoodCategory("misc", List(Cream, Sugar))
  )

  def allCategories: List[FoodCategory] = categories
}

object SimpleBrowser extends Browser {
  override val database = SimpleDatabase
}
