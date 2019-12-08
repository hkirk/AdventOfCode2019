import Day1.{FuelCounter, Module}
import org.scalatest.funspec.AnyFunSpec

class December1Spec extends AnyFunSpec {

  describe("Module") {
    it("A mass of 12 should need 2") {
      val fuel = Module(12)

      assert(fuel.fuelNeeded() == 2)
    }

    it("A mass of 14 should need 2") {
      val fuel = Module(14)

      assert(fuel.fuelNeeded() == 2)
    }

    it("A mass of 1969 should need 654") {
      val fuel = Module(1969)

      assert(fuel.fuelNeeded() == 654)
    }

    it("A mass of 100756 should need 33583") {
      val fuel = Module(100756)

      assert(fuel.fuelNeeded() == 33583)
    }

    /** Day 2 */

    it("A mass of 12 should need 2 with fuel") {
      val fuel = Module(12)

      assert(fuel.fuelNeededRec() == 2)
    }

    it("A mass of 14 should need 2 with fuel") {
      val fuel = Module(14)

      assert(fuel.fuelNeededRec() == 2)
    }

    it("A mass of 1969 should need 966 with fuel") {
      val fuel = Module(1969)

      assert(fuel.fuelNeededRec() == 966)
    }

    it("A mass of 100756 should need 50346 with fuel") {
      val fuel = Module(100756)

      assert(fuel.fuelNeededRec() == 50346)
    }
  }

  describe("FuelCounter") {
    it("Should calculate sum of fuel") {
      val fuelCounter = new FuelCounter(List(Module(12), Module(14), Module(1969), Module(100756)))

      assert(fuelCounter.fuel() == 34241)
    }


    it("Should calculate sum of fuel with default constructor") {
      val fuelCounter = new FuelCounter()

      assert(fuelCounter.fuel() == 3429947)
    }

    /** Day 2 */

    it("Should calculate sum of fuel with fuel-fuel") {
      val fuelCounter = new FuelCounter(List(Module(12), Module(14), Module(1969), Module(100756)))

      assert(fuelCounter.fuelRec() == 51316)
    }


    it("Should calculate sum of fuel with default constructor with fuel-fuel") {
      val fuelCounter = new FuelCounter()

      assert(fuelCounter.fuelRec() == 5142043)
    }
  }
}
