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
}
