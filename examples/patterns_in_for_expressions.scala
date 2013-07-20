// Say we have a map of data, full of items
val capitals = Map("Italy" -> "Rome", "Germany" -> "Berlin", "Norway" -> "Oslo")

// You can use a pattern match to destructure each key-value pair as you iterate:
for ((country, city) <- capitals)
  println("The capital of " + country + " is " + city)
