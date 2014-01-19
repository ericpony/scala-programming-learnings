package net.miladinov.recipe

object SimpleDatabase {
  def allFoods = List(Apple, Orange, Cream, Sugar)

  def foodNamed (name: String): Option[Food] = allFoods.find(_.name == name)

  def allRecipes: List[Recipe] = List(FruitSalad)
}

object SimpleBrowser {
  def recipesUsing (food: Food): List[Recipe] = SimpleDatabase.allRecipes.filter(recipe => recipe.ingredients.contains(food))
}
