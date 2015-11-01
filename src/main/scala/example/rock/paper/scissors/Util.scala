package example.rock.paper.scissors

import scala.annotation.tailrec

object Util {
  @tailrec
  def doUntilSuccess[T](tryDoing: => Option[T]): T = {
    val result = tryDoing
    if(result.isDefined)
      result.get
    else
      doUntilSuccess(tryDoing)
  }
}
