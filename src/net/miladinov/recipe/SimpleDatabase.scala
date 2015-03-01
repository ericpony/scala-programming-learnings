package net.miladinov.recipe

object SimpleDatabase extends Database
  with SimpleFoods
  with SimpleRecipes

object SimpleBrowser extends Browser {
  override val database = SimpleDatabase
}
