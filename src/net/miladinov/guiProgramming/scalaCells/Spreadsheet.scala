package net.miladinov.guiProgramming.scalaCells

import scala.swing.event.TableUpdated
import swing._

class Spreadsheet (val height: Int, val width: Int) extends ScrollPane {

  val table = new Table(height, width) {
    rowHeight = 25
    autoResizeMode = Table.AutoResizeMode.Off
    showGrid = true
    gridColor = new Color(150, 150, 150)

    val cellModel = new Model(height, width)
    import cellModel._

    override protected def rendererComponent (isSelected: Boolean, focused: Boolean, row: Int, column: Int): Component =
      if (hasFocus)
        new TextField(userData(row, column))
      else
        new Label(cells(row)(column).toString) { xAlignment = Alignment.Right }

    def userData (row: Int, column: Int): String = {
      val v = this(row, column)
      if (v == null) "" else v.toString
    }

    reactions += {
      case TableUpdated(table, rows, column) =>
        for (row <- rows)
          cells(row)(column).formula = FormulaParsers.parse(userData(row, column))

      case ValueChanged(cell) => updateCell(cell.row, cell.column)
    }

    for (row <- cells; cell <- row) listenTo(cell)
  }

  val rowHeader = new ListView((0 until height) map (_.toString)) {
    fixedCellWidth = 30
    fixedCellHeight = table.rowHeight
  }

  viewportView = table
  rowHeaderView = rowHeader
}
