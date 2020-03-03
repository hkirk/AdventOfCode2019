import Day2.IntCode

import scala.annotation.tailrec

class AmplifierController(intCodeProgram: String, settingsSequence: String) {

  lazy val trusterSignal: Int = settingsSequence.toCharArray.map(c => c.toString.toInt).foldLeft(0) { (acc, i) => {
    val intCode = new IntCode(intCodeProgram, i, acc)
    intCode.run()
  }}

  private lazy val amplifiers = settingsSequence.toCharArray.map(_.toString.toInt).map(i => new IntCode(intCodeProgram, i))

  @tailrec
  def thrusterSignalFeedbackLoop: Int = {
    def thrusterSignalFeedbackLoop(input: Int): Unit = {
      if (amplifiers.forall(_.isDone())) input
      else {
        amplifiers.foreach(i => {
          i.addInputs(input)
          i.run()
        })
        thrusterSignalFeedbackLoop()
      }
    }
  }

    /*settingsSequence.toCharArray.map(c => c.toString.toInt).foldLeft(0) { (acc, i) => {
    val intCode = new IntCode(intCodeProgram, i, acc)
    intCode.outputBuffer.last
  }}*/

}

object AmplifierController {
  def tryAllCombinations(intCodeProgram: String): Int = {
      List(0, 1, 2, 3, 4).permutations.foldLeft(0) { (op, l) =>
      val ac = new AmplifierController(intCodeProgram, l.mkString(""))
      Math.max(op, ac.trusterSignal)
    }
  }

  def tryAllCombinationsInFeedbackMode(intCodeProgram: String): Int = {
    List(5, 6,7,8,9).permutations.foldLeft(0) { (op, l) =>
      val ac = new AmplifierController(intCodeProgram, l.mkString(""))
      Math.max(op, ac.thrusterSignalFeedbackLoop)
    }
  }
}
