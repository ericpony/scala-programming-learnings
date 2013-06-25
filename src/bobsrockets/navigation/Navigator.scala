package bobsrockets {
  class Ship
}

package bobsrockets.navigation {
  package navigation {
    //  package bobsrockets.navigation
    class Navigator {
      // No need to say bobsrockets.navigation.StarMap
      val map = new StarMap
    }

    class StarMap

    package tests {
      // In package bobsrockets.navigation.tests
      class NavigatorSuite
    }
  }

  package fleets {
    class Fleet {
      // Doesn't compile! Ship is no longer in scope!
      // def addShip() { new Ship }
    }
  }
}
