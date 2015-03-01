package net.miladinov.recipe

// The problem here is that Pear is located in a different trait from the one that uses it, so it is out of scope.
// The compiler has no idea that SimpleRecipes is only ever mixed together with SimpleFoods. There is a way you can tell
// this to the compiler, however.
//
// Scala provides the self type for precisely this situation. Technically, a self type is an assumed type for `this`
// whenever this is mentioned within the class. Pragmatically, a self type specifies the requirements on any concrete
// class the trait is mixed into. If you have a trait that is only ever used when mixed in with another trait or traits,
// then you can specify that those other traits should be assumed.

trait SimpleRecipes {
  // Here, it's enough to specify a self type of SimpleFoods
  this: SimpleFoods =>

  object FruitSalad extends Recipe(
    "fruit salad",
    List(Apple, Pear),
    "Mix it all together."
  )

  def allRecipes = List(FruitSalad)
}
