package net.miladinov.reassignableproperties

// It is also possible, and sometimes useful, to define a getter and a setter
// without an associated field. An example is the following class Thermometer,
// which encapsulates a temperature variable that can be read and updated.
// Temperatures can be expressed in Celsius or Fahrenheit degrees. The class below
// allows you to get and set the temperature in either measure.

class Thermometer {
  var celsius: Float = _

  def fahrenheit = celsius * 9 / 5 + 32

  def fahrenheit_= (f: Float) {
    celsius = (f - 32) * 5 / 9
  }

  override def toString = fahrenheit + "F/" + celsius + "C"
}

