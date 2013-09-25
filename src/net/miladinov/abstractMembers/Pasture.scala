package net.miladinov.abstractMembers

// Structural subtyping has its own advantages. One is that sometimes there really is no more
// to a type than its members. For example, suppose you want to define a Pasture class that can
// contain animals that eat grass.

// One option would be to define a trait AnimalThatEatsGrass and mix it into every class where
// it applies. It would be verbose, however. Class Cow has already declared that it's an animal
// and that it eats grass, and now it would have to declare that it is also an animal-that-eats-grass.

// Instead of defining AnimalThatEatsGrass, you can use a refinement type. Simply write the base type,
// Animal, followed by a sequence of members listed in curly braces. The members in the curly braces
// further specify— or refine, if you will—the types of members from the base class.
// Here is how you write the type, "animal that eats grass":

// Animal { type SuitableFood = Grass }

// Given this type, we can now write the pasture class like this:

class Pasture {
  var animals: List[Animal {type SuitableFood = Grass}] = Nil
  // ...
}
