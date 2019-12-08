package Day1

import scala.io.Source

class FuelCounter(modules: List[Module]) {

  def this() = this(FuelCounter.readFile())

  def fuel(): Int = modules.foldRight(0)((a, b) => a.fuelNeeded() + b)

  def fuelRec(): Int = modules.foldRight(0)((a, b) => a.fuelNeededRec() + b)
}

object FuelCounter {
  def readFile(filename: String = "1.txt"): List[Module] = {
    var m = for (
      line <- Source.fromResource(filename).getLines
    ) yield Module(Integer.valueOf(line))

    m.toList
  }

}
