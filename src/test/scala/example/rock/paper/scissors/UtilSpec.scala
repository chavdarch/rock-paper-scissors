package example.rock.paper.scissors

import org.scalatest.{FlatSpec, Matchers}


class UtilSpec  extends FlatSpec with Matchers {
  "An Util" should "enable executing a function untill we get value" in {
    var countCalled = 0
    def isCalledTenTimes: Option[Int] = {
      if (countCalled ==10)
        Some(10)
      else{
        countCalled +=1
        None
      }
    }

    Util.doUntilSuccess(isCalledTenTimes) should be(10)
  }

}
