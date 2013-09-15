package net.miladinov.abstractMembers

// If you have a trait such as the following,
trait AbstractTime {
  var hour: Int
  var minute: Int
}

// what does that mean, to have an abstract var?
// Well, for one thing, they get expanded into abstract getters and setters:
/*
trait AbstractTime {
  def hour: Int         // getter for ‘hour’
  def hour_=(x: Int)    // setter for ‘hour’
  def minute: Int       // getter for ‘minute’
  def minute_=(x: Int)  // setter for ‘minute’
}
*/
