package net.miladinov.guiProgramming

import scala.swing._

object MyFirstScalaSwingApp extends SimpleSwingApplication {
  def top: Frame = new MainFrame {
    title = "My First Scala Swing App"
    contents = new Button {
      text = "Click Me"
    }
  }
}
