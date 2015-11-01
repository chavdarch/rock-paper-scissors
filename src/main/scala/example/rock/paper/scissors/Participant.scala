package example.rock.paper.scissors

sealed trait Participant{
  def weapon : Weapon
  def name:String
}

case class Player(name :String, weapon: Weapon) extends Participant

case class Computer(name :String, weapon: Weapon) extends Participant



//object Computer {
//  def rpsComputer(name :String) = Computer(name, RPSWeaponGenerator)
//  def rpsComputer(name :String, withWeapon: Weapon) = new Computer(name, RPSWeaponGenerator) {
//    override def weapon: Weapon = withWeapon
//  }
//
//  def rpslsComputer(name :String) = Computer(name, RPSLSWeaponGenerator)
//  def rpslsComputer(name :String, withWeapon: Weapon) = new Computer(name, RPSLSWeaponGenerator) {
//    override def weapon: Weapon = withWeapon
//  }
//}
