package example.rock.paper.scissors

import java.io.{Serializable, BufferedReader, PrintStream}


trait Output {
  def writer: PrintStream

  def print(s: String) = writer.print(s)
}

object Input{
  val validParticipantTypes: Seq[String] = Seq("player", "computer")
}

trait Input {
  def reader: BufferedReader

  def read(): String = reader.readLine()

  def readParticipantType(errorOutput: Output, validTypes: Seq[String] = Input.validParticipantTypes): Option[String] = read match {
    case x if validTypes.contains(x) => Some(x)
    case invalidInput => {
      errorOutput.print(s"Invalid input $invalidInput, please choose from: ${validTypes.mkString(",")}")
      None
    }
  }

  def readParticipantName(errorOutput: Output): Option[String] = read match {
    case x if !(x == null || x.isEmpty) => Some(x)
    case invalidInput => {
      errorOutput.print("Participant name should not be empty")
      None
    }
  }

  def readParticipantWeapon(errorOutput: Output, validWeapons: Set[Weapon] = RPSWeaponGenerator.allWeapons): Option[Weapon] = {
    val weapon = read
    validWeapons.find(_.name == weapon).orElse {
      errorOutput.print(s"Invalid input $weapon , please choose from: ${validWeapons.map(_.name).mkString(",")}")
      None
    }
  }
}


trait RockPaperScissorsGameHelper {

  def chooseParticipant(participantType: String, name: String, weapon: Weapon)(errorOutput: Output): Option[Participant] = participantType match {
    case "player" => Some(Player(name, weapon))
    case "computer" => Some(Computer(name, weapon))
    case invalidInput => {
      errorOutput.print(s"Invalid input $invalidInput, please choose from: ${Input.validParticipantTypes.mkString(",")}")
      None
    }
  }

  //  def chooseUntilValid[T](choice: T, valid: T => Boolean): T = {
  //    while(!validChoice)
  //      chooseUntilValid(validChoice)
  //    chooseParticipant(choice, name,  weapon).fold[Participant] {
  //      error: String  => {
  //        Console.out.println(error)
  //        chooseUntilValid(choice, name, weapon)
  //        },
  //         p:Participant => p
  //    }
  //  }
  //}
}

trait ConsoleInputOutput extends Input with Output {
  override val reader: BufferedReader = Console.in
  override val writer: PrintStream = Console.out
}

object RockPaperScissorsGame extends App with ConsoleInputOutput {
  //  def chooseParticipant(choice: Choice, name: String, weapon: Weapon): Either[String, Participant] = choice match {
  //    case PlayerChoice => Right(Player(name, weapon))
  //    case ComputerChoice => Right(Computer(name, weapon))
  //    case _ => Left(s"unknown choice $choice,  please choose player or computer")
  //  }

  print(s" Choose first participant from: ${Input.validParticipantTypes.mkString(",")} ")
  val participantType: String = Util.doUntilSuccess(readParticipantType(this))
  println(s"\nfirst participant type is: $participantType")


  print(s" Choose first participant name:")
  val name: String = Util.doUntilSuccess(readParticipantName(this))
  println(s"\nfirst participant name is: $name")

  print(s" Choose first participant weapon from: ${RPSWeaponGenerator.allWeapons.mkString(",")}")
  //only if it si player otherwise generate
  val weapon: String = Console.in.readLine()
  println(s"\nfirst participant weapon is: $name")

  //....
  //
  //  ///do the same for teh other player and then print the result
  //
  //  Console.withOut()
}

//case class Result(text: String, won: Int, lost: Int, drew: Int) {
//  override def toString = s"$text. Won: $won, Lost: $lost, Drew: $drew"
//
//}





