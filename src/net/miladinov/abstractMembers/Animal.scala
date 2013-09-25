package net.miladinov.abstractMembers

// Animals eat Food, but what kind of Food each Animal eats depends on the Animal.
// This can be neatly expressed with an abstract type:

class Food

abstract class Animal {
  type SuitableFood <: Food

  def eat(food: SuitableFood)
}

// With this class definition, an Animal can eat only food that's suitable.
// What food is suitable cannot be determined at the level of the Animal class.
// That's why SuitableFood is modeled as an abstract type. The type has an upper bound,
// Food, which is expressed by the "<: Food" clause. This means that any concrete instantiation
// of SuitableFood (in a subclass of Animal) must be a subclass of Food. For example, you would
// not be able to instantiate SuitableFood with class IOException.

