package net.miladinov.abstractMembers

class Grass extends Food

class Cow extends Animal {
  type SuitableFood = Grass

  def eat(food: Grass) {}
}
