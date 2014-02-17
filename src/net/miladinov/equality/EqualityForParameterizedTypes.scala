package net.miladinov.equality

object EqualityForParameterizedTypes {

  // The equals methods in the previous examples all started with a pattern match that tested whether the type of
  // the operand conformed to the type of the class containing the equals method. When classes are parameterized,
  // this scheme needs to be adapted a little bit. As an example, consider binary trees. The class hierarchy shown
  // here defines an abstract class Tree for a binary tree, with two alternative implementations: an EmptyTree object
  // and a Branch class representing non-empty trees. A non-empty tree is made up of some element elem and a left and
  // right child tree. The type of its element is given by a type parameter T.

  trait Tree [+T] {
    def elem: T
    def left: Tree[T]
    def right: Tree[T]
  }

  object EmptyTree extends Tree[Nothing] {
    def elem: Nothing = throw new NoSuchElementException("EmptyTree.elem")
    def left: Tree[Nothing] = throw new NoSuchElementException("EmptyTree.left")
    def right: Tree[Nothing] = throw new NoSuchElementException("EmptyTree.right")
  }

  class Branch [+T] (
    val elem: T,
    val left: Tree[T],
    val right: Tree[T]
  ) extends Tree[T] {

    // We’ll now add equals and hashCode methods to these classes. For class Tree itself there's nothing to do, because
    // we assume that these methods are implemented separately for each implementation of the abstract class. For object
    // EmptyTree, there's still nothing to do because the default implementations of equals and hashCode that EmptyTree
    // inherits from AnyRef work just fine. After all, an EmptyTree is only equal to itself, so equality should be
    // reference equality, which is what's inherited from AnyRef. But adding equals and hashCode to Branch requires
    // some work. A Branch value should only be equal to other Branch values, and only if the two values have equal elem,
    // left and right fields. It’s natural to apply the schema for equals that was developed previously in this package.
    // This would give:

    override def equals (other: Any) = other match {
      case that: Branch[_] => (that canEqual this) &&
                              this.elem  == that.elem &&
                              this.left  == that.left &&
                              this.right == that.right
      case _ => false
    }

    // The only thing that remains is to define for class Branch the other two methods, hashCode and canEqual, which go
    // with equals. Here’s a possible implementation of hashCode:

    override def hashCode: Int =
      41 * (
        41 * (
          41 + elem.hashCode
          ) + left.hashCode
        ) + right.hashCode

    // This is only one of many possible implementations. As shown previously, the principle is to take hashCode values
    // of all fields, and to combine them using additions and multiplications by some prime number. Here’s an
    // implementation of method canEqual in class Branch:

    // def canEqual(other: Any) = other match {
    //   case that: Branch[_] => true
    //   case _ => false
    // }

    // That implementation of the canEqual method used a typed pattern match.
    // It would also be possible to formulate it with isInstanceOf:
    def canEqual (other: Any) = other.isInstanceOf[Branch[_]]
  }
}
