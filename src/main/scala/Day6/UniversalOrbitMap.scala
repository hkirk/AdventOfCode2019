package Day6

class UniversalOrbitMap(orbitsInput: List[String]) {
  val orbits: List[Orbit] = orbitsInput.map(s => {
    val arr = s.split(')')
    Orbit(arr(1), arr(0))
  })
  val orbitSet: Map[String, Orbit] = orbits.map(o => o.name -> o).toMap

  def checksum(): Int = {
    def countOrbit(o: Orbit): Int =
      if (o.name == "COM") 0
      else if (o.orbitAround == "COM") 1
      else 1 + countOrbit(orbitSet(o.orbitAround))

    orbits.foldLeft(0) { (op, o) => op + countOrbit(o)}
  }

  def countOrbitalTransfers(): Int = {
    def orbitList(o: Orbit): List[Orbit] =
      if (o.orbitAround == "COM") List(o)
      else o :: orbitList(orbitSet(o.orbitAround))

    val youOrbit = orbitSet("YOU")
    val youList = orbitList(youOrbit).reverse
    val santaOrbit = orbitSet("SAN")
    val santaList = orbitList(santaOrbit).reverse

    val equalPart = youList.filter(santaList.toSet).dropRight(1).toSet
    val youListUnique = youList.filterNot(equalPart)
    val santaListUnique = santaList.filterNot(equalPart)

    // -2 from both list, since we want to remove ourself/santa and our planet.
    youListUnique.size - 2 + santaListUnique.size - 2
  }
}

case class Orbit(name: String, orbitAround: String)
