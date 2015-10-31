package example.rock.paper.scissors

import java.security.SecureRandom



case class Result(text: String, won: Int, lost: Int, drew: Int) {
  override def toString = s"$text. Won: $won, Lost: $lost, Drew: $drew"
}



