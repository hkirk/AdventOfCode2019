import Day2.IntCode
import org.scalatest.funspec.AnyFunSpec

class December2Spec extends AnyFunSpec {
  val puzzleInput = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,19,1,19,5,23,2,9,23,27,1,5,27,31,1,5,31,35,1,35,13,39,1,39,9,43,1,5,43,47,1,47,6,51,1,51,13,55,1,55,9,59,1,59,13,63,2,63,13,67,1,67,10,71,1,71,6,75,2,10,75,79,2,10,79,83,1,5,83,87,2,6,87,91,1,91,6,95,1,95,13,99,2,99,13,103,1,103,9,107,1,10,107,111,2,111,13,115,1,10,115,119,1,10,119,123,2,13,123,127,2,6,127,131,1,13,131,135,1,135,2,139,1,139,6,0,99,2,0,14,0"
  describe("Intcode") {
    it("opcode1") {
      val intcode = new IntCode("1,0,0,0,99")
      intcode.run()

      assert(intcode.head() == 2)

    }

    it("opcode2") {
      val intcode = new IntCode("2,3,0,3,99")
      intcode.run()

      assert(intcode.head() == 2)
      assert(intcode.index(3) == 6)
    }

    it("opcode2 2") {
      val intCode = new IntCode("2,4,4,5,99,0")
      intCode.run()

      assert(intCode.head() == 2)
      assert(intCode.index(3) == 5)
    }

    it("opcode1 2") {
      val intCode = new IntCode("1,1,1,4,99,5,6,0,99")
      intCode.run()

      assert(intCode.head() == 30)
      assert(intCode.index(4) == 2)
    }

    it("opcode1 mix") {
      val intCode = new IntCode("1,9,10,3,2,3,11,0,99,30,40,50")
      intCode.run()

      assert(intCode.head() == 3500)
    }

    it("solution 1") {
      val intCode = new IntCode(puzzleInput)
      intCode.run()

      assert(intCode.head() == 4090689)
    }
  }

  describe("Part2") {
    ignore("Should calculate noun/verb") {
      val expectedResult = 19690720

      val result = IntCode.NounVerbCalculator(puzzleInput, expectedResult)

      assert(result == 7733)
    }
  }
}
