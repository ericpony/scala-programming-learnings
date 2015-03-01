package net.miladinov.recipe

object GotApples {

  def main (args: Array[String]) {
    val db: Database = if (args(0) == "student")
      StudentDatabase
    else
      SimpleDatabase

    object browser extends Browser {
      // This definition has a funny-looking type of db.type. The ".type" on the end means that this is a singleton type.
      // A singleton type is extremely specific and holds only one object, in this case, whichever object is referred to
      // by db. Usually such types are too specific to be useful, which is why the compiler is reluctant to insert them
      // automatically. In this case, though, the singleton type allows the compiler to know that db and browser.database
      // are the same object, enough information to eliminate the type error highlighted below:
      val database: db.type = db
    }

    for (category <- db.allCategories)
      // Without the singleton type declaration of browser.database, the following type error would result:
      // Type mismatch, expected: browser.database.FoodCategory, actual: db.FoodCategory
      browser.displayCategory(category)

    val apple = SimpleDatabase.foodNamed("Apple").get

    for (recipe <- browser.recipesUsing(apple))
      println(recipe)
  }
}
