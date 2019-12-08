package Day3

import scala.collection.immutable.HashSet
import scala.collection.parallel.CollectionConverters._

object WireCalculator {
  def manhattanDistance(tuple: (Int, Int)): Int =
    Math.abs(tuple._1) + Math.abs(tuple._2)

  def findSmallestDistance(w1: Wire, w2: Wire): Int = intersections(w1, w2)
    .map(intersection => manhattanDistance(intersection))
    .min

  def intersections(w1: Wire, w2: Wire): List[(Int, Int)] = {
    val set = HashSet() ++ w2.coordinates
    w1.coordinates.toSet.filter(coordinate => { // if slow use .par
      set.contains(coordinate)
    }).toList
  }

  def countStepsToIntersection(intersection: (Int, Int), w1: Wire, w2: Wire): Int = {
    def countStepsToIntersection(wire: Wire): Int = {
      wire.coordinates.takeWhile(coordinate => coordinate != intersection).size + 1
    }

    countStepsToIntersection(w1) + countStepsToIntersection(w2)
  }

  def findShortestRouteToIntersection(w1: Wire, w2: Wire): Int = intersections(w1, w2)
    .map(intersection => countStepsToIntersection(intersection, w1, w2))
    .min

}
