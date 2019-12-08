package Day1

import scala.io.Source

case class Module(mass: Int) {
  def fuelNeeded(): Int = Math.floorDiv(mass, 3) - 2

  def fuelNeededRec(): Int = {

    def fuelNeededRec(fuelMass: Int): Int = {
      val result = Math.floorDiv(fuelMass, 3) - 2
      if (result <= 0) 0
      else result + fuelNeededRec(result)
    }
    fuelNeededRec(mass);
  }
}
