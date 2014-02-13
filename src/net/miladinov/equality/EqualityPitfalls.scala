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

  object ChangingEqualsWithoutAlsoChangingHashCode {
    // If you repeat the comparison of p1 and p2a with the latest definition of Point defined previously, you will get
    // true, as expected. However, if you repeat the HashSet.contains test, you will probably still get false.

    // scala> val p1, p2 = new Point(1, 2)
    // p1: Point = Point@67e5a7
    // p2: Point = Point@1165e21
    // scala> HashSet(p1) contains p2
    // res4: Boolean = false

    // In fact, this outcome is not 100% certain. You might also get true from the experiment. If you do, you can try
    // with some other points with coordinates 1 and 2. Eventually, you’ll get one which is not contained in the set.
    // What goes wrong here is that Point redefined equals without also redefining hashCode.

    // Note that the collection in the example above is a HashSet. This means elements of the collection are put in
    // "hash buckets" determined by their hash code. The contains test first determines a hash bucket to look in
    // and then compares the given elements with all elements in that bucket. Now, the last version of class Point did
    // redefine equals, but it did not at the same time redefine hashCode. So hashCode is still what it was in its
    // version in class AnyRef: some transformation of the address of the allocated object. The hash codes of p1 and p2
    // are almost certainly different, even though the fields of both points are the same. Different hash codes mean with
    // high probability different hash buckets in the set. The contains test will look for a matching element
    // in the bucket which corresponds to p2's hash code. In most cases, point p1 will be in another bucket, so it will
    // never be found. p1 and p2 might also end up by chance in the same hash bucket. In that case the test would
    // return true.

    // The problem was that the last implementation of Point violated the contract on hashCode as defined for class Any:
    // If two objects are equal according to the equals method, then calling the hashCode method on each of the two
    // objects must produce the same integer result. In fact, it's well known in Java that hashCode and equals should
    // always be redefined together. Furthermore, hashCode may only depend on fields that equals depends on. For the
    // Point class, the following would be a suitable definition of hashCode:

    class Point(val x: Int, val y: Int) {
      override def hashCode = 41 * (41 + x) + y
      override def equals(other: Any) = other match {
        case that: Point => this.x == that.x && this.y == that.y
        case _ => false
      }
    }

    // This is just one of many possible implementations of hashCode. Adding the constant 41 to one integer field x,
    // multiplying the result with the prime number 41, and adding to that result the other integer field y gives a
    // reasonable distribution of hash codes at a low cost in running time and code size.
    // Adding hashCode fixes the problems of equality when defining classes like Point. However, there are still other
    // trouble spots to watch out for.
  }

  object DefiningEqualsInTermsOfMutableFields {
    // Consider the following slight variation of class Point:
    class Point(var x: Int, var y: Int) { // Problematic
      override def hashCode = 41 * (41 + x) + y
      override def equals(other: Any) = other match {
        case that: Point => this.x == that.x && this.y == that.y
        case _ => false
      }
    }

    // The only difference is that the fields x and y are now vars instead of vals. The equals and hashCode methods are
    // now defined in terms of these mu- table fields, so their results change when the fields change. This can have
    // strange effects once you put points in collections:

    // scala> val p = new Point(1, 2)
    // p: Point = Point@6bc
    // scala> val coll = HashSet(p)
    // coll: scala.collection.mutable.HashSet[Point] =
    // Set(Point@6bc)
    // scala> coll contains p
    // res5: Boolean = true

    // Now, if you change a field in point p, does the collection still contain the point? We’ll try it:

    // scala> p.x += 1
    // scala> coll contains p
    // res7: Boolean = false

    // This looks strange. Where did p go? More strangeness results if you check whether the iterator of the set contains p:

    // scala> coll.iterator contains p
    // res8: Boolean = true

    // So here's a set that does not contain p, yet p is among the elements of the set! What happened, of course,
    // is that after the change to the x field, the point p ended up in the wrong hash bucket of the set coll. That is,
    // its original hash bucket no longer corresponded to the new value of its hash code. In a manner of speaking, the
    // point p "dropped out of sight" in the set coll even though it still belonged to its elements.

    // The lesson to be drawn from this example is that when equals and hashCode depend on mutable state, it causes
    // problems for potential users. If they put such objects into collections, they have to be careful never to modify
    // the depended-on state, and this is tricky. If you need a comparison that takes the current state of an object
    // into account, you should usually name it something else, not equals. Considering the last definition of Point,
    // it would have been preferable to omit a redefinition of hashCode and to name the comparison method equalContents,
    // or some other name different from equals. Point would then have inherited the default implementation of equals
    // and hashCode. So p would have stayed locatable in coll even after the modification to its x field.
  }
}
