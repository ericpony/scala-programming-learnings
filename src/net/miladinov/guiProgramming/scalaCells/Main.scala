package net.miladinov.guiProgramming.scalaCells

import swing._

object Main extends SimpleSwingApplication {
  def top: Frame = new MainFrame {
    title = "ScalaSheet"
    contents = new Spreadsheet(100, 26)
  }
}
