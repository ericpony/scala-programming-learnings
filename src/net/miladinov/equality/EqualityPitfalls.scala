package net.miladinov.equality

object EqualityPitfalls {
  // Here are four common pitfalls that can cause inconsistent behavior when overriding equals:
  // 1. Defining equals with the wrong signature.
  // 2. Changing equals without also changing hashCode.
  // 3. Defining equals in terms of mutable fields.
  // 4. Failing to define equals as an equivalence relation.

  object EqualsWithTheWrongSignature {
    // Consider adding an equality method to the following class of simple points:
    class PointWithIncorrectEqualsSignature(val x: Int, val y: Int) {
      // A seemingly obvious, but wrong way would be to define it like this:
      // An utterly wrong definition of equals
      def equals(other: PointWithIncorrectEqualsSignature): Boolean =
        this.x == other.x && this.y == other.y
    }

    // What’s wrong with this method? At first glance, it seems to work OK:
    // scala> val p1, p2 = new Point(1, 2)
    // p1: Point = Point@79f0ec
    // p2: Point = Point@1b8424e
    // scala> val q = new Point(2, 3)
    // q: Point = Point@d990db
    // scala> p1 equals p2
    // res0: Boolean = true
    // scala> p1 equals q
    // res1: Boolean = false

    // However, trouble starts once you start putting points into a collection:
    // scala> import scala.collection.mutable._
    // import scala.collection.mutable._
    // scala> val coll = HashSet(p1)
    // coll: scala.collection.mutable.HashSet[Point] =
    // Set(Point@79f0ec)
    // scala> coll contains p2
    // res2: Boolean = false

    // How to explain that coll does not contain p2, even though p1 was added to it, and p1 and p2 are equal objects?
    // The reason becomes clear in the following interaction, where the precise type of one of the compared points
    // is masked. Define p2a as an alias of p2, but with type Any instead of Point:

    // scala> val p2a: Any = p2
    // p2a: Any = Point@1b8424e

    // Now, were you to repeat the first comparison, but with the alias p2a instead of p2, you would get:

    // scala> p1 equals p2a
    // res3: Boolean = false

    // What went wrong? In fact, the version of equals given previously does not override the standard method equals,
    // because its type is different. Here is the type of the equals method as it is defined in the root class Any:

    // def equals(other: Any): Boolean

    // Because the equals method in Point takes a Point instead of an Any as an argument, it does not override
    // equals in Any. Instead, it is just an overloaded alternative. Now, overloading in Scala and in Java is resolved
    // by the static type of the argument, not the run-time type. So as long as the static type of the argument
    // is Point, the equals method in Point is called. However, once the static argument is of type Any, the equals
    // method in Any is called instead. This method has not been overridden, so it is still implemented by comparing
    // object identity. That’s why the comparison "p1 equals p2a" yields false even though points p1 and p2a have the
    // same x and y values. That’s also why the contains method in HashSet returned false. Since that method operates
    // on generic sets, it calls the generic equals method in Object instead of the overloaded variant in Point.


    class PointWithSlightlyBetterEqualsSignature(val x: Int, val y: Int) {
      // A better equals method is the following:
      // A better definition, but still not perfect
      override def equals(other: Any) = other match {
        case that: PointWithSlightlyBetterEqualsSignature => this.x == that.x && this.y == that.y
        case _ => false
      }
    }

    // Now equals has the correct type. It takes a value of type Any as parameter and it yields a Boolean result.
    // The implementation of this method uses a pattern match. It first tests whether the other object is also of type
    // Point. If it is, it compares the coordinates of the two points and returns the result.

    // Otherwise the result is false. A related pitfall is to define == with a wrong signature. Normally, if you try
    // to redefine == with the correct signature, which takes an argument of type Any, the compiler will give you an
    // error because you try to override a final method of type Any. However, newcomers to Scala sometimes make
    // two errors at once: They try to override == and they give it the wrong signature. For instance:

    // def ==(other: Point): Boolean = // Don’t do this!

    // In that case, the user-defined == method is treated as an overloaded variant of the same-named method class Any,
    // and the program compiles. However, the behavior of the program would be just as dubious as if you had defined
    // equals with the wrong signature.
  }
}
