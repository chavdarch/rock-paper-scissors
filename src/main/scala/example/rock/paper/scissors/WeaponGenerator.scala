package example.rock.paper.scissors

import java.security.SecureRandom

sealed trait Weapon {
  def beats: Set[Weapon]
  def name: String
}

trait WeaponGenerator {
  def allWeapons: Set[Weapon]
  def randomWeapon: Weapon
}

object RPSWeaponGenerator extends WeaponGenerator{
  val secureRandom = SecureRandom.getInstanceStrong

  case object Rock extends Weapon{
    override val beats: Set[Weapon] = Set(Scissors)

    override val name: String = "rock"
  }

  case object Scissors extends Weapon {
    override val beats: Set[Weapon] = Set(Paper)

    override val name: String = "scissors"
  }

  case object Paper extends Weapon {
    override val beats: Set[Weapon] = Set(Rock)

    override def name: String = "paper"
  }

  private implicit class WeaponConverter(value: Int) {
    def toRPSWeapon: Weapon = value match {
      case 1 => Rock
      case 2 => Scissors
      case 3 => Paper
      case unknown => sys.error(s"unknown material: $unknown")
    }
  }

  def randomWeapon: Weapon = {
    val randomWeaponNumber = secureRandom.nextInt(allWeapons.size) + 1
    randomWeaponNumber.toRPSWeapon
  }

  override val allWeapons: Set[Weapon] = Set(Rock, Scissors, Paper)
}


object RPSLSWeaponGenerator extends WeaponGenerator{
  val secureRandom = SecureRandom.getInstanceStrong

  case object Rock extends Weapon{
    override val beats: Set[Weapon] = Set(Lizard, Scissors)
    override val name: String = "rock"
  }

  case object Scissors extends Weapon {
    override val beats: Set[Weapon] = Set(Paper, Lizard)
    override val name: String = "scissors"
  }

  case object Paper extends Weapon {
    override val beats: Set[Weapon] = Set(Rock, Spock)
    override val name: String = "paper"
  }

  case object Lizard extends Weapon {
    override val beats: Set[Weapon] = Set(Spock, Paper)
    override val name: String = "lizard"
  }

  case object Spock extends Weapon {
    override val beats: Set[Weapon] = Set(Scissors, Rock)
    override def name: String = "spock"
  }

  private implicit class WeaponConverter(value: Int) {
    def toRPSLSWeapon: Weapon = value match {
      case 1 => Rock
      case 2 => Scissors
      case 3 => Paper
      case 4 => Lizard
      case 5 => Spock
      case unknown => sys.error(s"unknown material: $unknown")
    }
  }

  def randomWeapon: Weapon = {
    val randomWeaponNumber = secureRandom.nextInt(allWeapons.size) + 1
    randomWeaponNumber.toRPSLSWeapon
  }

  override val allWeapons: Set[Weapon] = Set(Rock, Scissors, Paper, Lizard, Spock)

}