package Day2

class IntCode(input: String) {
  private val buffer = input.split(",").map(s => s.toInt).toBuffer

  var i = 0;
  while (buffer(i) != 99) {
    buffer(i) match {
      case 1 =>
        buffer(buffer(i+3)) = buffer(buffer(i+1)) + buffer(buffer(i+2))
        i = i+4
      case 2 =>
        buffer(buffer(i+3)) = buffer(buffer(i+1)) * buffer(buffer(i+2))
        i = i+4
      case 99 =>
    }
  }

  def head(): Int = buffer.head

  def index(i:Int): Int = buffer(i)
}

object IntCode {
  def NounVerbCalculator(input: String, expectedResult: Int): Int = {
    val inputArray = input.split(",").map(s => s.toInt)

    def setInputs(noun: Int, verb: Int): String = {
      val list = inputArray.toList.patch(1, List(noun, verb), 2)
      list.mkString(",")
    }

    def calculateResult(noun: Int, verb: Int): Boolean = {
      val intCode = new IntCode(setInputs(noun, verb))
      intCode.head() == expectedResult
    }

    val pairs = for {
      i <- 0 to 99
      j <- 0 to 99
    } yield (i, j)

    val result = pairs.find(v => {
        calculateResult(v._1, v._2);
    })
    100 * result.get._1 + result.get._2

  }
}
