package example.rock.paper.scissors

import java.io.{BufferedReader, PrintStream}


trait Output {
  def writer: PrintStream

  def print(s: String) = writer.print(s)
  def println(s: String) = writer.println(s)

}

object Input {
  val validParticipantTypes: Seq[String] = Seq("player", "computer")
}

trait Input {
  def reader: BufferedReader

  def read(): String = reader.readLine()

  def readParticipantType(errorOutput: Output, validTypes: Seq[String] = Input.validParticipantTypes): Option[String] = read match {
    case x if validTypes.contains(x) => Some(x)
    case invalidInput => {
      errorOutput.print(s"\nInvalid input '$invalidInput', please choose from (${validTypes.mkString(",")}): ")
      None
    }
  }

  def readParticipantName(errorOutput: Output): Option[String] = read match {
    case x if !(x == null || x.isEmpty) => Some(x)
    case invalidInput => {
      errorOutput.print("\nParticipant name should not be empty, try again: ")
      None
    }
  }

  def readParticipantWeapon(errorOutput: Output, validWeapons: Set[Weapon] = RPSWeaponGenerator.allWeapons): Option[Weapon] = {
    val weapon = read
    validWeapons.find(_.name == weapon).orElse {
      errorOutput.print(s"\nInvalid input '$weapon', please choose from (${validWeapons.map(_.name).mkString(",")}): ")
      None
    }
  }
}


trait RockPaperScissorsGameHelper {

  def chooseParticipant(participantType: String, name: String, weapon: Weapon)(errorOutput: Output): Option[Participant] = participantType match {
    case "player" => Some(Player(name, weapon))
    case "computer" => Some(Computer(name, weapon))
    case invalidInput => {
      errorOutput.print(s"\nInvalid input '$invalidInput', please choose from (${Input.validParticipantTypes.mkString(",")}): ")
      None
    }
  }

  def chooseParticipant(input: Input, output: Output): Participant = {
    output.print(s"Choose participant from (${Input.validParticipantTypes.mkString(",")}): ")
    val participantType: String = Util.doUntilSuccess(input.readParticipantType(output))
    output.println(s"Participant type is: $participantType")


    output.print(s"Choose participant name: ")
    val name: String = Util.doUntilSuccess(input.readParticipantName(output))
    output.println(s"Participant name is: $name")

    val weapon = if(participantType == "computer") {
      RPSWeaponGenerator.randomWeapon
    }else{
      output.print(s"Choose participant weapon from (${RPSWeaponGenerator.allWeapons.map(_.name).mkString(",")}): ")
      Util.doUntilSuccess(input.readParticipantWeapon(output))
    }
    output.println(s"Participant weapon is: ${weapon.name}")

    Util.doUntilSuccess(chooseParticipant(participantType, name, weapon)(output))
  }
}

trait ConsoleInputOutput extends Input with Output {
  override val reader: BufferedReader = Console.in
  override val writer: PrintStream = Console.out
}

object RockPaperScissorsGame extends App with ConsoleInputOutput with RockPaperScissorsGameHelper {
  println(s"Choose first participant")
  val firstParticipant = chooseParticipant(this, this)

  println(s"Choose second participant")
  val secondParticipant = chooseParticipant(this, this)

  val winner = WinnerFinder(firstParticipant, secondParticipant).winner

  if (winner.isDefined)
    println(s"${winner.get.name} is the winner with weapon ${winner.get.weapon.name}!")
  else
    println(s"The game doesn't have a winner, for ${firstParticipant.name} with weapon ${firstParticipant.weapon.name} and ${secondParticipant.name} with weapon ${secondParticipant.weapon.name}!")
}





