package net.miladinov.guiProgramming.scalaCells

class Model (val height: Int, val width: Int) {
  case class Cell(row: Int, column: Int) {
    var formula: Formula = Empty
    var value: Double = 0.0
    override def toString: String = formula.toString
  }

  val cells = Array.ofDim[Cell](height, width)

  for (i <- 0 until height; j <- 0 until width)
    cells(i)(j) = Cell(i, j)
}
