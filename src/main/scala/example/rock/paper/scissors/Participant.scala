package example.rock.paper.scissors

sealed trait Participant{
  def weapon : Weapon
}

case class Player(name :String, weapon: Weapon) extends Participant

case class Computer(name :String, weaponGenerator: WeaponGenerator) extends Participant {
  override def weapon: Weapon = weaponGenerator.randomWeapon
}

object Computer {
  def rpsComputer(name :String) = Computer(name, RPSWeaponGenerator)
  def rpsComputer(name :String, withWeapon: Weapon) = new Computer(name, RPSWeaponGenerator) {
    override def weapon: Weapon = withWeapon
  }
}
