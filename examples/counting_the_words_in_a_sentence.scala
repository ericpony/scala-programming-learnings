import scala.collection.mutable

// The key characteristic of sets is that they will ensure that at most one of
// each object, as determined by ==, will be contained in the set at any one time.
// As an example, we’ll use a set to count the number of different words in a string.
// The split method on String can separate a string into words, if you specify spaces
// and punctuation as word separators. The regular expression “[ !,.]+” will suffice:
// it indicates the string should be split at each place that one or more space and/or
// punctuation characters exist:
val text = "See Spot run. Run, Spot. Run!"
// returns: text: String = See Spot run. Run, Spot. Run!

val wordsArray = text.split("[ !,.]+")
// returns: wordsArray: Array[String] = Array(See, Spot, run, Run, Spot, Run)

// To count the distinct words, you can convert them to the same case and then
// add them to a set. Because sets exclude duplicates, each distinct word will appear
// exactly one time in the set. First, you can create an empty set using the empty
// method provided on the Set companion objects:
val words = mutable.Set.empty[String]
// returns: words: scala.collection.mutable.Set[String] = Set()

// Then, just iterate through the words with a for expression, convert each word
// to lower case, and add it to the mutable set with the += operator:
for (word <- wordsArray)
  words += word.toLowerCase

words
// returns: scala.collection.mutable.Set[String] = Set(see, run, spot)