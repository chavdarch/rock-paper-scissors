package example.rock.paper.scissors

import java.security.SecureRandom

sealed trait Participant{
  def weapon : Weapon
}

case class Player(name :String, weapon: Weapon) extends Participant

case class Computer(name :String, numberOfWeapons: Int) extends Participant {
  override def weapon: Weapon = WeaponGenerator(numberOfWeapons).randomWeapon
}

object Computer {
  def rpsComputer(name :String) = Computer(name, 3)
  def rpsComputer(name :String, withWeapon: Weapon) = new Computer(name, 3) {
    override def weapon: Weapon = withWeapon
  }
}

case class Result(text: String, won: Int, lost: Int, drew: Int) {
  override def toString = s"$text. Won: $won, Lost: $lost, Drew: $drew"
}


case class RPSGame(participantOne: Participant, participantTwo: Participant) {
  def findWinner(): Option[Participant] = {
    if(participantOne.weapon.beats.contains(participantTwo.weapon))
      Some(participantOne)
    else if(participantTwo.weapon.beats.contains(participantOne.weapon))
      Some(participantTwo)
    else
      None
  }

}

//players ar always two
//weapons can be more than two
//object RPSGame {
//  def apply[P <: Participant](participantOne: P, participantTwo: P) = {
//    new GameOfTwo(participantOne, participantTwo)
//  }
//}

sealed trait Weapon {
  def beats: Seq[Weapon]
}

case object Rock extends Weapon{
  override val beats: Seq[Weapon] = Seq(Scissors)
}

case object Scissors extends Weapon {
  override val beats: Seq[Weapon] = Seq(Paper)
}

case object Paper extends Weapon {
  override val beats: Seq[Weapon] = Seq(Rock)
}

case class WeaponGenerator(numberfWeapons: Int = 3) {
  val secureRandom = SecureRandom.getInstanceStrong

  implicit class WeaponConverter(value: Int) {
    def toWeapon: Weapon = value match {
      case 1 => Rock
      case 2 => Scissors
      case 3 => Paper
      case unknown => sys.error(s"unknown material: $unknown")
    }
  }

  def randomWeapon: Weapon = {
    val randomWeaponNumber = secureRandom.nextInt(numberfWeapons) + 1
    randomWeaponNumber.toWeapon
  }
}