package net.miladinov.recipe

trait FoodCategories {
  case class FoodCategory (name: String, foods: List[Food])
  def allCategories: List[FoodCategory]
}
