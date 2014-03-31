package net.miladinov.guiProgramming

import net.miladinov.reassignableproperties.Thermometer

import swing._
import event._

object TemperatureConverter extends SimpleSwingApplication {
  def top: Frame = new MainFrame {
    title = "Celsius / Fahrenheit Converter"

    object celsius extends TextField { columns = 5 }
    object fahrenheit extends TextField { columns = 5 }

    contents = new FlowPanel {
      contents += celsius
      contents += new Label(" Celsius = ")
      contents += fahrenheit
      contents += new Label (" Fahrenheit")
      border = Swing.EmptyBorder(15, 10, 10, 10)
    }

    listenTo(celsius, fahrenheit)

    val thermometer = new Thermometer

    reactions += {
      case EditDone(`fahrenheit`) =>
        thermometer.fahrenheit = fahrenheit.text.toFloat
        celsius.text = thermometer.celsius.toString

      case EditDone(`celsius`) =>
        thermometer.celsius = celsius.text.toFloat
        fahrenheit.text = thermometer.fahrenheit.toString
    }
  }
}
