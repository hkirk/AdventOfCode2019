import Day3.{Wire, WireCalculator}
import org.scalatest.funspec.AnyFunSpec

import scala.io.Source

class December3Spec extends AnyFunSpec {
  describe("Wire Translation") {
    it("Should translate R8,U5,L5,D3 to a path from 0,0 to 3,-2") {
      val wire = new Wire("R8,U5,L5,D3")

      assert(wire.coordinates.last == (3, -2))
    }

    it("Should translate U7,R6,D4,L4 to a path from 0,0 to 2,-3") {
      val wire = new Wire("U7,R6,D4,L4")

      assert(wire.coordinates.last == (2, -3))
    }
  }
  describe("Part1") {
    it("Should find 2 intersections") {
      val wire1 = new Wire("R8,U5,L5,D3")
      val wire2 = new Wire("U7,R6,D4,L4")

      val intersections = WireCalculator.intersections(wire1, wire2)
      assert(intersections.size == 2)
      assert(intersections.contains((3,-3)))
      assert(intersections.contains((6,-5)))
    }

    it("Should calculate Manhattan distance") {
      val distance = WireCalculator.manhattanDistance((3, -3))

      assert(distance == 6)
    }

    it("should find correct shortest distance between example 1") {
      val wire1 = new Wire("R8,U5,L5,D3")
      val wire2 = new Wire("U7,R6,D4,L4")

      val shortestDistance = WireCalculator.findSmallestDistance(wire1, wire2)

      assert(shortestDistance == 6)
    }

    it("should find correct shortest distance between example 2") {
      val wire1 = new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72")
      val wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83")

      val shortestDistance = WireCalculator.findSmallestDistance(wire1, wire2)

      assert(shortestDistance == 159)
    }

    it("should find correct shortest distance between example 3") {
      val wire1 = new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R5")
      val wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

      val shortestDistance = WireCalculator.findSmallestDistance(wire1, wire2)

      assert(shortestDistance == 135)
    }


    ignore("should find correct distance between real example") {
      val lines = Source.fromResource("3.txt").getLines.toList
      val wire1 = new Wire(lines(0))
      val wire2 = new Wire(lines(1))

      val shortestDistance = WireCalculator.findSmallestDistance(wire1, wire2)

      assert(shortestDistance == 651)
    }
  }

  describe("Part 2") {
    it("should count steps correctly example 1.1") {
      val wire1 = new Wire("R8,U5,L5,D3")
      val wire2 = new Wire("U7,R6,D4,L4")

      val steps = WireCalculator.countStepsToIntersection((6,-5), wire1, wire2)

      assert(steps == 30)
    }

    it("should count steps correctly example 1.2") {
      val wire1 = new Wire("R8,U5,L5,D3")
      val wire2 = new Wire("U7,R6,D4,L4")

      val steps = WireCalculator.countStepsToIntersection((3, -3), wire1, wire2)

      assert(steps == 40)
    }

    it("should correctly find the shortest step count from all intersections in example 1") {
      val wire1 = new Wire("R8,U5,L5,D3")
      val wire2 = new Wire("U7,R6,D4,L4")

      val steps = WireCalculator.findShortestRouteToIntersection(wire1, wire2)

      assert(steps == 30)
    }


    it("should correctly find the shortest step count from all intersections in example 2") {
      val wire1 = new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72")
      val wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83")

      val steps = WireCalculator.findShortestRouteToIntersection(wire1, wire2)

      assert(steps == 610)
    }

    it("should correctly find the shortest step count from all intersections in example 3") {
      val wire1 = new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
      val wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

      val steps = WireCalculator.findShortestRouteToIntersection(wire1, wire2)

      assert(steps == 410)
    }

    ignore("should correctly find the shortest step count from all intersections in real example") {
      val lines = Source.fromResource("3.txt").getLines.toList
      val wire1 = new Wire(lines(0))
      val wire2 = new Wire(lines(1))

      val steps = WireCalculator.findShortestRouteToIntersection(wire1, wire2)

      assert(steps == 7534)
    }
  }

}
