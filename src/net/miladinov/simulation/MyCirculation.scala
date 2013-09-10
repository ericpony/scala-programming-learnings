package net.miladinov.simulation

object MySimulation extends CircuitSimulation with App {
  def InverterDelay = 1
  def AndGateDelay = 3
  def OrGateDelay = 5

  val input1, input2, sum, carry = new Wire
  probe("sum", sum)
  probe("carry", carry)

  halfAdder(input1, input2, sum, carry)
  input1 setSignal true

  run()

  input2 setSignal true

  run()
}
