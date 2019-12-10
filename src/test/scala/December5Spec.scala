import Day2.IntCode
import org.scalatest.funspec.AnyFunSpec

import scala.io.Source

class December5Spec extends AnyFunSpec {
  describe("Part 1") {
    it("Should handle input and output") {
      val intCode = new IntCode("3,0,4,0,99", 1)

      assert(intCode.head() == 1)
    }

    it("Should handle position and immediate parameters") {
      val intCode = new IntCode("1002,4,3,4,33")

      assert(intCode.index(4) == 99)
    }

    it("Should support negative numbesr") {
      val intCode = new IntCode("1101,100,-1,4,0")

      assert(intCode.index(4) == 99)
    }

    it("Should handle puzzle input") {
      val source = Source.fromResource("5.txt").getLines().toList

      val intCode = new IntCode(source(0), 1)

      assert(intCode.index(223) == 3122865)
    }
  }

  describe("Part 2") {
    it("equal to 8") {
      val intCode = new IntCode("3,9,8,9,10,9,4,9,99,-1,8", 8)

      assert(intCode.index(9) == 1)
    }

    it("equal to 7") {
      val intCode = new IntCode("3,9,8,9,10,9,4,9,99,-1,8", 7)

      assert(intCode.index(9) == 0)
    }

    it("less then 8") {
      val intCode = new IntCode("3,9,7,9,10,9,4,9,99,-1,8", 8)

      assert(intCode.index(9) == 0)
    }

    it("less then 8 with 7") {
      val intCode = new IntCode("3,9,7,9,10,9,4,9,99,-1,8", 7)

      assert(intCode.index(9) == 1)
    }

    it("equal to 8 immediate mode") {
      val intCode = new IntCode("3,3,1108,-1,8,3,4,3,99", 8)

      assert(intCode.index(3) == 1)
    }

    it("equal to 7 immediate mode") {
      val intCode = new IntCode("3,3,1108,-1,8,3,4,3,99", 7)

      assert(intCode.index(3) == 0)
    }

    it("less than 8 immediate mode") {
      val intCode = new IntCode("3,3,1107,-1,8,3,4,3,99", 8)

      assert(intCode.index(3) == 0)
    }

    it("less than 8 immediate mode with 7") {
      val intCode = new IntCode("3,3,1107,-1,8,3,4,3,99", 7)

      assert(intCode.index(3) == 1)
    }

    it("should jump and output 0") {
      val intCode = new IntCode("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", 0)

      assert(intCode.index(12) == 0)
    }

    it("should jump and output 1") {
      val intCode = new IntCode("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", 1)

      assert(intCode.index(12) == 1)
    }

    it("should jump and output 0 immediate mode") {
      val intCode = new IntCode("3,3,1105,-1,9,1101,0,0,12,4,12,99,1", 0)

      assert(intCode.index(12) == 0)
    }

    it("should jump and output 1 immediate mode") {
      val intCode = new IntCode("3,3,1105,-1,9,1101,0,0,12,4,12,99,1", 1)

      assert(intCode.index(12) == 1)
    }

    it("Larger examples should output 999") {
      val intCode = new IntCode("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 1)

      assert(intCode.index(32) == 999)
    }

    it("Larger examples should output 1000") {
      val intCode = new IntCode("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 8)

      assert(intCode.index(20) == 1000)
    }

    it("Larger examples should output 1001") {
      val intCode = new IntCode("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 80)

      assert(intCode.index(20) == 1001)
    }

    it("Should handle puzzle input") {
      val source = Source.fromResource("5.txt").getLines().toList

      val intCode = new IntCode(source(0), 5)

      assert(intCode.index(223) == 773660)
    }
  }
}
