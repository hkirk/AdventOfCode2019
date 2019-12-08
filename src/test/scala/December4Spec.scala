import Day4.PasswordCracker
import org.scalatest.funspec.AnyFunSpec

class December4Spec extends AnyFunSpec {
  describe("Part 1") {
    it("111111 meets the criteria") {
      val valid = PasswordCracker.checkPassword(111111)
      assert(valid)
    }

    it("223450 meets the criteria") {
      val valid = PasswordCracker.checkPassword(223450)
      assert(!valid)
    }

    it("123789 meets the criteria") {
      val valid = PasswordCracker.checkPassword(123789)
      assert(!valid)
    }

    it("122345 meets the criteria") {
      val valid = PasswordCracker.checkPassword(122345)
      assert(valid)
    }

    it("111123 meets the criteria") {
      val valid = PasswordCracker.checkPassword(111123)
      assert(valid)
    }

    it("133679 meets the criteria") {
      val valid = PasswordCracker.checkPassword(133679)
      assert(valid)
    }

    it("Should count numbers of valid password in range 111111-111121") {
      val count = PasswordCracker.checkPasswordInRange(111111, 111121)
      assert(count == 9)
    }

    it("Should count numbers of valid password in range 231832-767346")  {
      val count = PasswordCracker.checkPasswordInRange(231832, 767346)

      assert(count == 1330)
    }
  }

  describe("Part 2") {
    it("112233 meets the criteria") {
      val valid = PasswordCracker.checkPasswordMax2Repeting(112233)
      assert(valid)
    }

    it("123444 meets the criteria") {
      val valid = PasswordCracker.checkPasswordMax2Repeting(123444)
      assert(!valid)
    }

    it("111122 meets the criteria") {
      val valid = PasswordCracker.checkPasswordMax2Repeting(111122)
      assert(valid)
    }

    it("Should count numbers of valid password in range 231832-767346")  {
      val count = PasswordCracker.checkPasswordMax2RepetingInRange(231832, 767346)

      assert(count == 876)
    }
  }
}
