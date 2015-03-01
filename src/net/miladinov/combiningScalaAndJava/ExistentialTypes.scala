package net.miladinov.combiningScalaAndJava

object ExistentialTypes {
  // All Java types have a Scala equivalent. This is necessary so that Scala code can access any legal Java class.
  // Most of the time the translation is straightforward. Pattern in Java is Pattern in Scala, and Iterator<Component>
  // in Java is Iterator[Component] in Scala. For some cases, though, the Scala types you have seen so far are not
  // enough. What can be done with Java wildcard types such as Iterator<?> or Iterator<? extends Component>?
  // What can be done about raw types like Iterator, where the type parameter is omitted? For wildcard types and raw
  // types, Scala uses an extra kind of type called an existential type.

  // Existential types are a fully supported part of the language, but in practice they are mainly used when accessing
  // Java types from Scala. This section gives a brief overview of how existential types work, but mostly this is only
  // useful so that you can understand compiler error messages when your Scala code accesses Java code.
  // The general form of an existential type is as follows:
  //
  // type forSome { declarations }

  // The type part is an arbitrary Scala type, and the declarations part is a list of abstract vals and types.
  // The interpretation is that the declared variables and types exist but are unknown, just like abstract members of
  // a class. The type is then allowed to refer to the declared variables and types even though it is unknown what
  // they refer to. Take a look at some concrete examples. A Java Iterator<?> would be written in Scala as:
  //
  // Iterator[T] forSome { type T }

  // Read this from left to right. This is an Iterator of T's for some type T. The type T is unknown, and could be
  // anything, but it is known to be fixed for this particular Iterator. Similarly, a Java
  // Iterator<? extends Component> would be viewed in Scala as:
  //
  // Iterator[T] forSome { type T <: Component }

  // This is an Iterator of T, for some type T that is a subtype of Component. In this case T is still unknown,
  // but now it is sure to be a subtype of Component.

  // By the way, there is a shorter way to write these examples. If you write Iterator[_], it means the same thing as
  // Iterator[T] forSome { type T }. This is placeholder syntax for existential types, and is similar in spirit to the
  // placeholder syntax for function literals that was described earlier. If you use an underscore (_) in place of an
  // expression, then Scala treats this as a placeholder and makes a function literal for you. For types it works
  // similarly. If you use an underscore in place of a type, Scala makes an existential type for you. Each underscore
  // becomes one type parameter in a forSome clause, so if you use two underscores in the same type, you will get the
  // effect of a forSome clause with two types in it.

  // You can also insert upper and lower bounds when using this placeholder syntax. Simply add them to the underscore
  // instead of in the forSome clause. The type
  //
  // Iterator[_ <: Component]
  //
  // is the same as this one, which you just saw:
  //
  // Iterator[T] forSome { type T <: Component }

  // Enough about the existential types themselves. How do you actually use them? Well, in simple cases, you use an
  // existential type just as if the forSome were not there. Scala will check that the program is sound even though
  // the types and values in the forSome clause are unknown. For example, suppose you had the following Java class:

  // This is a Java class with wildcards
  // public class Wild {
  //   Collection<?> contents() {
  //     Collection<String> stuff = new Vector<String>();
  //     stuff.add("a");
  //     stuff.add("b");
  //     stuff.add("see");
  //     return stuff;
  //   }
  // }

  // If you access this in Scala code you will see that it has an existential type:
  // scala> val contents = (new Wild).contents
  // contents: java.util.Collection[?0] forSome { type ?0 } = [a, b, see]

  // If you want to find out how many elements are in this collection, you can simply ignore the existential part and
  // call the size method as normal:

  // scala> contents.size()
  // res0: Int = 3

  // In more complicated cases, existential types can be more awkward, because there is no way to name the existential
  // type. For example, suppose you wanted to create a mutable Scala set and initialize it with the elements
  // of contents:

  // import scala.collection.mutable.Set
  // val iter = (new Wild).contents.iterator
  // val set = Set.empty[???]     // what type goes here?
  // while (iter.hasMore)
  //   set += iter.next()

  // A problem strikes on the third line. There is no way to name the type of elements in the Java collection, so you
  // cannot write down a satisfactory type for set. To work around this kind of problem, here are two tricks you should
  // consider:

  // 1. When passing an existential type into a method, move type parameters from the forSome clause to type parameters
  // of the method. Inside the body of the method, you can use the type parameters to refer to the types that were in
  // the forSome clause.

  // 2. Instead of returning an existential type from a method,return an object that has abstract members for each of
  // the types in the forSome clause.

  // Using these two tricks together, the previous code can be written as follows:
  import scala.collection.mutable

  abstract class SetAndType {
    type Elem
    val set: mutable.Set[Elem]
  }

  def javaSetToScalaSet [T] (javaSet: java.util.Collection[T]): SetAndType = {
    val scalaSet = mutable.Set.empty[T] // now T can be named!

    val iterator = javaSet.iterator
    while (iterator.hasNext) scalaSet += iterator.next()

    new SetAndType {
      type Elem = T
      val set: mutable.Set[Elem] = scalaSet
    }
  }

  // You can see why Scala code normally does not use existential types. To do anything sophisticated with them,
  // you tend to convert them to use abstract members. So you may as well use abstract members to begin with.
}
