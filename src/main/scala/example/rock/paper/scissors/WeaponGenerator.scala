package example.rock.paper.scissors

import java.security.SecureRandom

sealed trait Weapon {
  def beats: Set[Weapon]
}

trait WeaponGenerator {
  def numberOfWeapons: Int
  def randomWeapon: Weapon
}

object RPSWeaponGenerator extends WeaponGenerator{
  val secureRandom = SecureRandom.getInstanceStrong

  case object Rock extends Weapon{
    override val beats: Set[Weapon] = Set(Scissors)
  }

  case object Scissors extends Weapon {
    override val beats: Set[Weapon] = Set(Paper)
  }

  case object Paper extends Weapon {
    override val beats: Set[Weapon] = Set(Rock)
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
    val randomWeaponNumber = secureRandom.nextInt(numberOfWeapons) + 1
    randomWeaponNumber.toRPSWeapon
  }

  override def numberOfWeapons: Int = 3
}


object RPSLSWeaponGenerator extends WeaponGenerator{
  val secureRandom = SecureRandom.getInstanceStrong

  case object Rock extends Weapon{
    override val beats: Set[Weapon] = Set(Lizard, Scissors)
  }

  case object Scissors extends Weapon {
    override val beats: Set[Weapon] = Set(Paper, Lizard)
  }

  case object Paper extends Weapon {
    override val beats: Set[Weapon] = Set(Rock, Spock)
  }

  case object Lizard extends Weapon {
    override val beats: Set[Weapon] = Set(Spock, Paper)
  }

  case object Spock extends Weapon {
    override val beats: Set[Weapon] = Set(Scissors, Rock)
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
    val randomWeaponNumber = secureRandom.nextInt(numberOfWeapons) + 1
    randomWeaponNumber.toRPSLSWeapon
  }

  override def numberOfWeapons: Int = 5
}