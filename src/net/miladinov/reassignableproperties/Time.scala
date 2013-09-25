package net.miladinov.reassignableproperties

// The following simple class:
class Time {
  var hour = 12
  var minute = 0
}

// ... is equivalent to the following, which Scala normally generates for you:
class TimeEquivalent {
  private[this] var h = 12
  private[this] var m = 0

  def hour: Int = h

  def hour_= (x: Int) {
    h = x
  }

  def minute: Int = m

  def minute_= (x: Int) = {
    m = x
  }
}

// You can perform two fundamental operations on a reassignable variable: get its value
// or set it to a new value. In libraries such as JavaBeans, these operations are often
// encapsulated in separate getter and setter methods, which need to be defined explicitly.
// In Scala, every var that is a non-private member of some object implicitly defines a getter
// and a setter method with it. These getters and setters are named differently from the Java
// convention, however. The getter of a var x is just named "x", while its setter is named "x_=".

// An interesting aspect about this expansion of vars into getters and setters is that you can
// also choose to define a getter and a setter directly instead of defining a var. By defining
// these access methods directly you can interpret the operations of variable access and variable
// assignment as you like. For instance, this next version of the Time class contains
// requirements that catch all assignments to hour and minute with illegal values.

class TimeWithSetterInvariants {
  private[this] var h = 12
  private[this] var m = 0

  def hour: Int = h

  def hour_= (x: Int) {
    require(0 <= x && x < 24)
    h = x
  }

  def minute: Int = m

  def minute_= (x: Int) {
    require(0 <= x && x < 60)
    m = x
  }
}
