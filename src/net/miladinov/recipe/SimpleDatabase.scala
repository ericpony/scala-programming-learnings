package net.miladinov.recipe

object SimpleDatabase {
  def allFoods = List(Apple, Orange, Cream, Sugar)

  def foodNamed (name: String): Option[Food] = allFoods.find(_.name == name)

  def allRecipes: List[Recipe] = List(FruitSalad)

  case class FoodCategory (name: String, foods: List[Food])

  private var categories = List(
    FoodCategory("fruits", List(Apple, Orange)),
    FoodCategory("misc", List(Cream, Sugar))
  )

  def allCategories: List[FoodCategory] = categories
}

object SimpleBrowser {
  def recipesUsing (food: Food): List[Recipe] = SimpleDatabase.allRecipes.filter(recipe => recipe.ingredients.contains(food))

  def displayCategory (category: SimpleDatabase.FoodCategory): Unit = {
    println(category)
  }
}
