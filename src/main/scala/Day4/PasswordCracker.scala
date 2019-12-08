package Day4

import scala.collection.mutable

object PasswordCracker {
  /*
  It is a six-digit number.
  The value is within the range given in your puzzle input.
  Two adjacent digits are the same (like 22 in 122345).
  Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
   */
  def checkPassword(password: Int): Boolean = {
    val passwordString = password.toString.toCharArray.map(_.toInt)
    passwordString.length == 6 &&
      passwordString.foldLeft((false, 0)) { (acc, i) => (acc._1 || acc._2 == i, i)}._1 &&
      numbersAreIncreasing(passwordString)

  }

  /*
  112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
  123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
  111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).
   */
  def checkPasswordMax2Repeting(password: Int): Boolean = {
    val numberArray = password.toString.toCharArray.map(_.toInt)

    def containsTwoConsecutive(): Boolean = {
      var result = false
      var consecutive = 1;
      (1 until numberArray.length).foreach(i => {
        if (numberArray(i-1) == numberArray(i)) {
          consecutive += 1;
        }
        else {
          if (consecutive == 2) result = true
          consecutive = 1
        }
      })
      if (consecutive == 2) result = true
      result
    }

    numberArray.length == 6 &&
      numbersAreIncreasing(numberArray) &&
      //!numberArray.exists(i => find3Consecutive(i.toChar.toString))
      containsTwoConsecutive()
  }

  private def numbersAreIncreasing(passwordString: Array[Int]) = {
    passwordString.foldLeft((true, 0)) { (acc, i) => (acc._1 && acc._2 <= i, i) }._1
  }

  def checkPasswordInRange(from: Int, to: Int): Int = (from to to).count(checkPassword)

  def checkPasswordMax2RepetingInRange(from: Int, to: Int): Int = (from to to).count(checkPasswordMax2Repeting)
}
