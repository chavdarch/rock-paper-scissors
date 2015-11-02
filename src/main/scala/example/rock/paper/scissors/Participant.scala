package example.rock.paper.scissors

sealed trait Participant{
  def weapon : Weapon
  def name:String
}

case class Player(name :String, weapon: Weapon) extends Participant

case class Computer(name :String, weapon: Weapon) extends Participant