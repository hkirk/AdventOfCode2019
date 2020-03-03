package Day2

import scala.collection.mutable

class IntCode(code: String, inputs: Int*) {
  private val inputBuffer = inputs.toBuffer
  private val buffer = code.split(",").map(s => s.toInt).toBuffer
  private var i = 0
  private val outputBuffer = mutable.Buffer[Int]();

  private var state: State = Stopped
  def isDone(): Boolean = state == Stopped

  def run(): Int = {
    state = Running
    while (state != Running && buffer(i) != 99) {
      buffer(i) match {
        case 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 99 =>
          handleCommand(buffer(i), positionMode, positionMode, positionMode)

        case complex =>
          val str = complex.toString
          parameterMode("0" * (5-str.length) + str)

      }
    }
    if (outputBuffer.nonEmpty) outputBuffer.head
    else 0
  }

  def addInputs(inputs: Int*): Unit = inputBuffer.addAll(inputs)

  private def parameterMode(value: String): Unit = {
    val A = value.charAt(0)
    val B = value.charAt(1)
    val C = value.charAt(2)
    val opCode = value.slice(3, 5).toInt

    handleCommand(opCode,
      if(C == '1') immediateMode else positionMode,
      if(B == '1') immediateMode else positionMode,
      if(A == '1') immediateMode else positionMode)
  }

  private def handleCommand(opcode: Int,
                            f1: Int => Int,
                            f2: Int => Int,
                            f3: Int => Int): Unit = opcode match {
    case 1 =>
      handleAdd(f1, f2)
    case 2 =>
      handleMultiply(f1, f2)
    case 3 =>
      handleInput()
    case 4 =>
      handleOutput(f1)
    case 5 => handleJump(i => i != 0, f1, f2)
    case 6 => handleJump(i => i == 0, f1, f2)
    case 7 => handleComparison((a, b) => a < b, f1, f2)
    case 8 => handleComparison((a, b) => a == b, f1, f2)

    case 99 =>
      state = Stopped

  }

  private def handleAdd(f1: Int => Int, f2: Int => Int): Unit = {
    def add(a: Int, b: Int): Int = a+b
    buffer(buffer(i+3)) = add(f1(i+1), f2(i+2))
    i += 4
  }

  private def handleMultiply(f1: Int => Int, f2: Int => Int): Unit = {
    def multiply(a: Int, b: Int): Int = a*b
    buffer(buffer(i+3)) = multiply(f1(i+1), f2(i+2))
    i += 4
  }

  private def handleInput(): Unit = {
    if (inputBuffer.isEmpty) {
      state = Halted
    }
    buffer(buffer(i+1)) = inputBuffer.remove(0)
    i += 2
  }

  private def handleOutput(f1: Int => Int): Unit = {
    val value: Int = f1(i+1)
    println("Output at position: " + (i+1) + ", is: " + value)
    outputBuffer.addOne(value)
    i += 2
  }

  private def handleJump(f: Int => Boolean, f1: Int => Int, f2: Int => Int): Unit = {
    if (f(f1(i+1))) i = f2(i+2)
    else i += 3
  }

  private def handleComparison(f: (Int, Int) => Boolean, f1: Int => Int, f2: Int => Int): Unit = {
    buffer(buffer(i+3)) = if (f(f1(i+1), f2(i+2))) 1 else 0
    i += 4
  }

  private def positionMode(index: Int): Int = buffer(buffer(index))
  private def immediateMode(index: Int): Int = buffer(index)

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
      intCode.run()
      intCode.head() == expectedResult
    }

    val pairs = for {
      i <- 0 to 99
      j <- 0 to 99
    } yield (i, j)

    val result = pairs.find(v => {
        calculateResult(v._1, v._2)
    })
    100 * result.get._1 + result.get._2

  }
}
