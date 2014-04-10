package net.miladinov.guiProgramming.scalaCells

class Model (val height: Int, val width: Int) extends Evaluator with Arithmetic {
  case class Cell(row: Int, column: Int) {
    var formula: Formula = Empty
    def value = evaluate(formula)
    override def toString: String = formula match {
      case Textual(s) => s
      case _ => value.toString
    }
  }

  val cells = Array.ofDim[Cell](height, width)

  for (i <- 0 until height; j <- 0 until width)
    cells(i)(j) = Cell(i, j)
}
