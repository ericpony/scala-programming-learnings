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

  object FailingToDefineEqualsAsAnEquivalenceRelation {
    // The contract of the equals method in scala.Any specifies that equals must implement an equivalence relation on
    // non-null objects (As with hashCode, Any's equals contract is based on the contract of equals in java.lang.Object):

    // • It is reflexive: for any non-null value x , the expression x.equals(x) should return true.
    // • It is symmetric: for any non-null values x and y, x.equals(y) should return true if and
    //   only if y.equals(x) returns true.
    // • It is transitive: for any non-null values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true,
    //   then x.equals(z) should return true.
    // • It is consistent: for any non-null values x and y, multiple invocations of x.equals(y) should consistently
    //   return true or consistently return false, provided no information used in equals comparisons
    //   on the objects is modified.
    // • For any non-null value x, x.equals(null) should return false.

    // The definition of equals developed so far for class Point satisfies the contract for equals. However, things
    // become more complicated once subclasses are considered. Say there is a subclass ColoredPoint of Point that adds
    // a field color of type Color. Assume Color is defined as an enumeration, as presented here:
    object Color extends Enumeration {
      val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
    }

    // Here is the Point class again, so we can have one defined in the current object:
    class Point(val x: Int, val y: Int) {
      override def hashCode = 41 * (41 + x) + y
      override def equals(other: Any) = other match {
        case that: Point => this.x == that.x && this.y == that.y
        case _ => false
      }
    }

    // ColoredPoint overrides equals to take the new color field into account:
    class ColoredPointEqualsNotSymmetric(x: Int, y: Int, val color: Color.Value) extends Point(x, y) { // Problem: equals not symmetric
      override def equals(other: Any) = other match {
        case that: ColoredPointEqualsNotSymmetric => this.color == that.color && super.equals(that)
        case _ => false
      }
    }

    // This is what many programmers would likely write. Note that in this case, class ColoredPoint need not override
    // hashCode. Because the new definition of equals on ColoredPoint is stricter than the overridden definition in Point
    // (meaning it equates fewer pairs of objects), the contract for hashCode stays valid. If two colored points are equal,
    // they must have the same coordinates, so their hash codes are guaranteed to be equal as well.

    // Taking the class ColoredPoint by itself, its definition of equals looks OK. However, the contract for equals
    // is broken once points and colored points are mixed. Consider:

    // scala> val p = new Point(1, 2)
    // p: Point = Point@6bc
    // scala> val cp = new ColoredPoint(1, 2, Color.Red)
    // cp: ColoredPoint = ColoredPoint@6bc
    // scala> p equals cp
    // res9: Boolean = true
    // scala> cp equals p
    // res10: Boolean = false

    // The comparison "p equals cp" invokes p's equals method, which is defined in class Point. This method only takes
    // into account the coordinates of the two points. Consequently, the comparison yields true. On the other hand,
    // the comparison "cp equals p" invokes cp's equals method, which is defined in class ColoredPoint. This method
    // returns false, because p is not a ColoredPoint. So the relation defined by equals is not symmetric.
    // The loss in symmetry can have unexpected consequences for collections. Here’s an example:

    // scala> HashSet[Point](p) contains cp
    // res11: Boolean = true
    // scala> HashSet[Point](cp) contains p
    // res12: Boolean = false

    // So even though p and cp are equal, one contains test succeeds whereas the other one fails. How can you change
    // the definition of equals so that it becomes symmetric? Essentially there are two ways. You can either make the
    // relation more general or more strict. Making it more general means that a pair of two objects, x and y, is taken
    // to be equal if either comparing x with y or comparing y with x yields true. Here’s code that does this:

    class ColoredPointEqualsNotTransitive(x: Int, y: Int, val color: Color.Value) extends Point(x, y) { // Problem: equals not transitive
      override def equals(other: Any) = other match {
        case that: ColoredPointEqualsNotTransitive => (this.color == that.color) && super.equals(that)
        case that: Point => that equals this
        case _ => false
      }
    }

    // The new definition of equals in ColoredPoint has one more case than the old one: If the other object is a Point
    // but not a ColoredPoint, the method forwards to the equals method of Point. This has the desired effect of making
    // equals symmetric. Now, both "cp equals p" and "p equals cp" result in true. However, the contract for equals
    // is still broken. Now the problem is that the new relation is no longer transitive! Here's a sequence of statements
    // that demonstrates this. Define a point and two colored points of different colors, all at the same position:

    // scala> val redp = new ColoredPoint(1, 2, Color.Red)
    // redp: ColoredPoint = ColoredPoint@6bc
    // scala> val bluep = new ColoredPoint(1, 2, Color.Blue)
    // bluep: ColoredPoint = ColoredPoint@6bc

    // Taken individually, redp is equal to p and p is equal to bluep:

    // scala> redp == p
    // res13: Boolean = true
    // scala> p == bluep
    // res14: Boolean = true

    // However, comparing redp and bluep yields false:

    // scala> redp == bluep
    // res15: Boolean = false

    // Hence, the transitivity clause of equals's contract is violated. Making the equals relation more general seems
    // to lead to a dead end. We’ll try to make it stricter instead. One way to make equals stricter is to always treat
    // objects of different classes as different. That could be achieved by modifying the equals methods in classes
    // Point and ColoredPoint. In class Point, you could add an extra comparison that checks whether the run-time class
    // of the other Point is exactly the same as this Point’s class, as follows:

    // A technically valid, but unsatisfying, equals method
    class PointChecksClassAtRuntimeForEquality(val x: Int, val y: Int) {
      override def hashCode = 41 * (41 + x) + y
      override def equals(other: Any) = other match {
        case that: PointChecksClassAtRuntimeForEquality => this.x == that.x && this.y == that.y && this.getClass == that.getClass
        case _ => false
      }
    }

    // You can then revert class ColoredPoint’s implementation back to the version that previously had violated
    // the symmetry requirement:
    class ColoredPointChecksClassAtRuntimeForEquality(x: Int, y: Int, val color: Color.Value) extends Point(x, y) {
      override def equals(other: Any) = other match {
        case that: ColoredPointChecksClassAtRuntimeForEquality => (this.color == that.color) && super.equals(that)
        case _ => false
      }
    }

    // Here, an instance of class Point is considered to be equal to some other in- stance of the same class only if
    // the objects have the same coordinates and they have the same run-time class, meaning .getClass on either object
    // returns the same value. The new definitions satisfy symmetry and transitivity because now every comparison between
    // objects of different classes yields false. So a colored point can never be equal to a point. This convention looks
    // reasonable, but one could argue that the new definition is too strict. Consider the following slightly roundabout
    // way to define a point at coordinates (1, 2):

    // scala> val pAnon = new Point(1, 1) { override val y = 2 }
    // pAnon: Point = $anon$1@6bc

    // Is pAnon equal to p? The answer is no because the java.lang.Class objects associated with p and pAnon are different.
    // For p it is Point, whereas for pAnon it is an anonymous subclass of Point. But clearly, pAnon is just another point
    // at coordinates (1, 2). It does not seem reasonable to treat it as being different from p.

    // So it seems we are stuck. Is there a sane way to redefine equality on several levels of the class hierarchy while
    // keeping its contract? In fact, there is such a way, but it requires one more method to redefine together with equals
    // and hashCode. The idea is that as soon as a class redefines equals (and hashCode), it should also explicitly state
    // that objects of this class are never equal to objects of some superclass that implement a different equality method.
    // This is achieved by adding a method canEqual to every class that redefines equals. Here's the method's signature:

    // def canEqual(other: Any): Boolean

    // The method should return true if the other object is an instance of the class in which canEqual is (re)defined,
    // false otherwise. It is called from equals to make sure that the objects are comparable both ways. Following is
    // the new (and final) implementation of class Point along these lines:

    class PointWithCanEqual(val x: Int, val y: Int) {
      override def hashCode = 41 * (41 + x) + y
      override def equals(other: Any) = other match {
        case that: PointWithCanEqual => (that canEqual this) && (this.x == that.x) && (this.y == that.y)
        case _ => false
      }
      def canEqual(other: Any) = other.isInstanceOf[PointWithCanEqual]
    }

    // The equals method in this version of class Point contains the additional requirement that the other object
    // can equal this one, as determined by the canEqual method. The implementation of canEqual in Point states that
    // all instances of Point can be equal.

    // Below is the corresponding implementation of ColoredPoint. It can be shown that the new definition of Point and
    // ColoredPoint keeps the contract of equals. Equality is symmetric and transitive. Comparing a Point to a
    // ColoredPoint always yields false. Indeed, for any point p and colored point cp, "p equals cp" will return false
    // because "cp canEqual p" will return false. The reverse comparison, "cp equals p", will also return false, because
    // p is not a ColoredPoint, so the first pattern match in the body of equals in ColoredPoint will fail.

    class ColoredPointWithCanEqual(x: Int, y: Int, val color: Color.Value) extends PointWithCanEqual(x, y) {
      override def hashCode = 41 * super.hashCode + color.hashCode
      override def equals(other: Any) = other match {
        case that: ColoredPointWithCanEqual => (that canEqual this) && super.equals(that) && this.color == that.color
        case _ => false
      }
      override def canEqual(other: Any) = other.isInstanceOf[ColoredPointWithCanEqual]
    }

    // On the other hand, instances of different subclasses of Point can be equal, as long as none of the classes
    // redefines the equality method. For instance, with the new class definitions, the comparison of p and pAnon would
    // yield true. Here are some examples:

    // scala> val p = new Point(1, 2)
    // p: Point = Point@6bc
    // scala> val cp = new ColoredPoint(1, 2, Color.Indigo)
    // cp: ColoredPoint = ColoredPoint@11421
    // scala> val pAnon = new Point(1, 1) { override val y = 2 }
    // pAnon: Point = $anon$1@6bc
    // scala> val coll = List(p)
    // coll: List[Point] = List(Point@6bc)
    // scala> coll contains p
    // res16: Boolean = true
    // scala> coll contains cp
    // res17: Boolean = false
    // scala> coll contains pAnon
    // res18: Boolean = true

    // These examples demonstrate that if a superclass equals implementation defines and calls canEqual, then programmers
    // who implement subclasses can decide whether or not their subclasses may be equal to instances of the superclass.
    // Because ColoredPoint overrides canEqual, for example, a colored point may never be equal to a plain-old point.
    // But because the anonymous subclass referenced from pAnon does not override canEqual, its instance can be equal
    // to a Point instance.

    // One potential criticism of the canEqual approach is that it violates the Liskov Substitution Principle (LSP).
    // For example, the technique of implementing equals by comparing run-time classes, which led to the inability
    // to define a subclass whose instances can equal instances of the superclass, has been described as a violation
    // of the LSP. The reasoning is that the LSP states you should be able to use (substitute) a subclass instance
    // where a superclass instance is required. In the previous example, however, "coll contains cp" returned false even
    // though cp’s x and y values matched those of the point in the collection. Thus it may seem like a violation of
    // the LSP, because you can't use a ColoredPoint here where a Point is expected. We believe this is the wrong
    // interpretation, though, because the LSP doesn't require that a subclass behaves identically to its superclass,
    // just that it behaves in a way that fulfills the contract of its superclass.

    // The problem with writing an equals method that compares run-time classes is not that it violates the LSP, but
    // that it doesn't give you a way to create a subclass whose instances can equal superclass instances. For example,
    // had we used the run-time class technique in the previous example, "coll contains pAnon" would have returned false,
    // and that's not what we wanted. By contrast, we really did want "coll contains cp' to return false, because
    // by overriding equals in ColoredPoint, we were basically saying that an indigo-colored point at coordinates (1, 2)
    // is not the same thing as an uncolored point at (1, 2). Thus, in the previous example we were able to pass two
    // different Point subclass instances to the collection's contains method, and we got back two different answers,
    // both correct.
  }
}
