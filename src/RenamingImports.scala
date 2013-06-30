// You can alias your imports, renaming Apple to McIntosh in this example.
import bobsdelights.Fruits.{Apple => McIntosh, _}
import java.{sql => S}

object RenamingImports {
  // After the above import, you can access the same object either via McIntosh or
  // bobsdelights.Fruits.Apple
  val newApple = McIntosh
  val oldApple =  bobsdelights.Fruits.Apple
}
