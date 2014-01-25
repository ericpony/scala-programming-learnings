package net.miladinov.recipe

abstract class Database extends FoodCategories {
  def allFoods: List[Food]
  def allRecipes: List[Recipe]
  def foodNamed (name: String) = allFoods.find(_.name == name)
}
