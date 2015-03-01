package net.miladinov.combiningScalaAndJava

object SingletonObjects {
  // Java has no exact equivalent to a singleton object, but it does have static methods. The Scala translation of
  // singleton objects uses a combination of static and instance methods. For every Scala singleton object,
  // the compiler will create a Java class for the object with a dollar sign added to the end. For a singleton object
  // named App, the compiler produces a Java class named App$. This class has all the methods and fields of the Scala
  // singleton object. The Java class also has a single static field named MODULE$ to hold the one instance of the
  // class that is created at run time.

  // As a full example, suppose you compile the following singleton object:
  object App {
    def main(args: Array[String]) {
      println("Hello, world!")
    }
  }

  // Scala will generate a Java App$ class with the following fields and methods:
  // $ javap App$
  // public final class App$ extends java.lang.Object implements scala.ScalaObject {
  //   public static final App$ MODULE$;
  //   public static {};
  //   public App$();
  //   public void main(java.lang.String[]);
  //   public int $tag();
  // }

  // That's the translation for the general case. An important special case is if you have a "standalone" singleton
  // object, one which does not come with a class of the same name. For example, you might have a singleton object
  // named App, and not have any class named App. In that case, the compiler will create a Java class named App that
  // has a static forwarder method for each method of the Scala singleton object:

  // $ javap App
  // Compiled from "App.scala"
  // public final class App extends java.lang.Object{
  //   public static final int $tag();
  //   public static final void main(java.lang.String[]);
  // }

  // To contrast, if you did have a class named App, Scala would create a corresponding Java App class to hold the
  // members of the App class you defined. In that case it would not add any forwarding methods for the same-named
  // singleton object, and Java code would have to access the singleton via the MODULE$ field.
}
