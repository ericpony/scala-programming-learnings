package net.miladinov.guiProgramming

import scala.swing._
import scala.swing.event.ButtonClicked

object MySecondScalaSwingApp extends SimpleSwingApplication {
  def top: Frame = new MainFrame {
    title = "My Second Scala Swing App"

    val button = new Button {
      text = "Click Me"
    }

    val label = new Label {
      text = "No button clicks registered"
    }

    contents = new BoxPanel(Orientation.Vertical) {
      contents += button
      contents += label
      border = Swing.EmptyBorder(30, 30, 10, 30)
    }

    listenTo(button)
    var numClicks = 0

    reactions += {
      case ButtonClicked(b) =>
        numClicks += 1
        label.text = s"Number of button clicks: $numClicks"
    }
  }
}
