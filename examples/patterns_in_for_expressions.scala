// Say we have a map of data, full of items
val capitals = Map("Italy" -> "Rome", "Germany" -> "Berlin", "Norway" -> "Oslo")

// You can use a pattern match to destructure each key-value pair as you iterate:
for ((country, city) <- capitals)
  println(s"The capital of $country is $city")

// The pair pattern shown above was special because the match against it
// can never fail. // Indeed, `capitals` yields a sequence of pairs, so you can
// be sure that every generated // pair can be matched against a pair pattern.
// But it is equally possible that a pattern // might not match a generated value.
// Here is an example where that is the case:
val results = List(Some("apple"), None, Some("orange"))

for (Some(fruit) <- results) println(fruit)
