package net.miladinov.abstractMembers

import java.io.PrintWriter
import java.util.Date
import java.net.{Socket, ServerSocket}

abstract class StructuralSubtyping {

  def main(args: Array[String]) {
    // Suppose we wanted to generalize the loan pattern to group together a bunch of code
    // written by someone else. Suppose we wanted to be able to define a "using block" for
    // any object that had a "close()" method, not just PrintWriters.
    // For the purposes of this example, let's leave using as an abstract method of
    // this class. We'll define it later.

    // One caller using our code might want to use using like this, to clean up an open file:
    using(new PrintWriter("dates.txt")) { writer =>
      // cast to PrintWriter for now to pass type-check
      writer.asInstanceOf[PrintWriter].println(new Date)
    }

    // Another caller, meanwhile, might want to clean up an open socket:
    val serverSocket: ServerSocket = new ServerSocket

    using(serverSocket.accept()) { socket =>
      socket.asInstanceOf[Socket].getOutputStream().write("howdy, planet!\n".getBytes)
    }
  }

  def using[T <: { def close(): Unit }, S] (thing: T)(operation: T => S) = {
    val result = operation(thing)
    thing.close()
    result
  }
}
