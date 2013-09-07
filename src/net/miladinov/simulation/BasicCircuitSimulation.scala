package net.miladinov.simulation

abstract class BasicCircuitSimulation extends Simulation {
  def InverterDelay: Int
  def AndGateDelay: Int
  def OrGateDelay: Int

  class Wire {
    private var signalVal = false
    private var actions: List[Action] = List()

    def getSignal = signalVal
    def setSignal(s: Boolean) =
      if (s != signalVal) {
        signalVal = s
        actions foreach (_ ())
      }

    def addAction(a: Action) = {
      actions = a :: actions
      a()
    }
  }

  def inverter(input: Wire, output: Wire) = {
    def invertAction() {
      val inputSignal = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSignal
      }
    }
    input addAction invertAction
  }

  def andGate(a1: Wire, a2: Wire, output: Wire) = {
    def andAction() = {
      val a1Signal = a1.getSignal
      val a2Signal = a2.getSignal
      afterDelay(AndGateDelay) {
        output setSignal(a1Signal & a2Signal)
      }
    }
    a1 addAction andAction
    a2 addAction andAction
  }

  def orGate(o1: Wire, o2: Wire, output: Wire) = {
    def orAction() = {
      val o1Signal = o1.getSignal
      val o2Signal = o2.getSignal
      afterDelay(OrGateDelay) {
        output setSignal(o1Signal & o2Signal)
      }
    }
    o1 addAction orAction
    o2 addAction orAction
  }

  def probe(name: String, wire: Wire) {
    def probeAction() = {
      println(s"$name $currentTime new-value: = ${wire.getSignal}")
    }
    wire addAction probeAction
  }
}
