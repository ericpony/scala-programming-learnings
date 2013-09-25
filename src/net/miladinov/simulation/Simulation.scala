package net.miladinov.simulation

abstract class Simulation {
  type Action = () => Unit

  case class WorkItem(time: Int, action: Action)

  private var curTime = 0
  private var agenda: List[WorkItem] = List()

  def currentTime: Int = curTime

  def afterDelay(delay: Int)(block: => Unit) {
    val item = WorkItem(currentTime + delay, () => block)
    agenda = insert(agenda, item)
  }

  def run() {
    afterDelay(0) {
      println(s"*** simulation started, time = $currentTime ***")
    }
    while (!agenda.isEmpty) next()
  }

  private def insert(ag: List[WorkItem], item: WorkItem): List[WorkItem] = {
    if (ag.isEmpty || item.time < ag.head.time) item :: ag
    else ag.head :: insert(ag.tail, item)
  }

  private def next() {
    (agenda: @unchecked) match {
      case item :: rest =>
        agenda = rest
        curTime = item.time
        item.action()
    }
  }
}
