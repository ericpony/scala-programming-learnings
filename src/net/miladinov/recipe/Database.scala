package net.miladinov.recipe

abstract class Database {
  def allFoods: List[Food]

  def allRecipes: List[Recipe]

  def foodNamed (name: String) = allFoods.find(_.name == name)

  case class FoodCategory (name: String, foods: List[Food])

  def allCategories: List[FoodCategory]
}
