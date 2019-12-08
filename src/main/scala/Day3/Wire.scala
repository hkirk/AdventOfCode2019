package Day3

class Wire(paths: String) {
  private var current = (0,0)
  val coordinates: List[(Int, Int)] = paths.split(",")
    .map(s => (s.head, s.substring(1).toInt))
    .flatMap(pair => pair match {
    case ('R', num) => calculateCoordinates(num, c1 => (c1._1+1,c1._2))
    case ('L', num) => calculateCoordinates(num, c1 => (c1._1-1, c1._2))
    case ('U', num) => calculateCoordinates(num, c1 => (c1._1, c1._2-1))
    case ('D', num) => calculateCoordinates(num, c1 => (c1._1, c1._2+1))
  }).toList

  private def calculateCoordinates(num: Int, f: ((Int, Int)) => ((Int, Int))): Seq[(Int, Int)] = {
    (0 until num).map(_ => {
      current = f(current)
      current
    })
  }
}
