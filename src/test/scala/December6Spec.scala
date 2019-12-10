import Day6.UniversalOrbitMap
import org.scalatest.funspec.AnyFunSpec

import scala.io.Source

class December6Spec extends AnyFunSpec {
  val input = """COM)B
    |B)C
    |C)D
    |D)E
    |E)F
    |B)G
    |G)H
    |D)I
    |E)J
    |J)K
    |K)L""".stripMargin.linesIterator.toList.filter(s => s.nonEmpty)

  describe("Parsing") {
    it("should parse correct number of orbits") {
      val occ = new UniversalOrbitMap(input)

      assert(occ.orbits.size == 11)
    }
  }

  describe("Part 1") {
    it("Should calculate the checksum for the examples to 42") {
      val occ = new UniversalOrbitMap(input)

      assert(occ.checksum() == 42)
    }

    it("Should calculate the checksum for the puzzle input") {
      val source = Source.fromResource("6.txt").getLines().toList.filter(s => s.nonEmpty)

      val occ = new UniversalOrbitMap(source)

      assert(occ.checksum() == 223251)
    }
  }

  describe("Part 2") {
    it("Should correctly calculate number of orbit transfers to 4") {
      val input = """COM)B
                    |B)C
                    |C)D
                    |D)E
                    |E)F
                    |B)G
                    |G)H
                    |D)I
                    |E)J
                    |J)K
                    |K)L
                    |K)YOU
                    |I)SAN""".stripMargin.linesIterator.toList
      val occ = new UniversalOrbitMap(input)

      assert(occ.countOrbitalTransfers() == 4)
    }

    it("Should correctly calculate number of orbit transfers for puzzle input") {
      val source = Source.fromResource("6.txt").getLines().toList.filter(s => s.nonEmpty)

      val occ = new UniversalOrbitMap(source)

      assert(occ.countOrbitalTransfers() == 430)
    }
  }

}
