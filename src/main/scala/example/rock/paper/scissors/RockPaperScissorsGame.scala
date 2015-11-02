package example.rock.paper.scissors

class RockPaperScissorsGameDriver(val input: Input, val output: Output) {
  def readParticipantType(validTypes: Seq[String] = Input.validParticipantTypes): Option[String] = input.read() match {
    case x if validTypes.contains(x) => Some(x)
    case invalidInput => {
      output.print(s"\nInvalid input '$invalidInput', please choose from (${validTypes.mkString(",")}): ")
      None
    }
  }

  def readParticipantName(): Option[String] = input.read() match {
    case x if !(x == null || x.isEmpty) => Some(x)
    case invalidInput => {
      output.print("\nParticipant name should not be empty, try again: ")
      None
    }
  }

  def readParticipantWeapon(validWeapons: Set[Weapon] = RPSWeaponGenerator.allWeapons): Option[Weapon] = {
    val weapon = input.read()
    validWeapons.find(_.name == weapon).orElse {
      output.print(s"\nInvalid input '$weapon', please choose from (${validWeapons.map(_.name).mkString(",")}): ")
      None
    }
  }

  def chooseParticipant(participantType: String, name: String, weapon: Weapon): Option[Participant] = participantType match {
    case "player" => Some(Player(name, weapon))
    case "computer" => Some(Computer(name, weapon))
    case invalidInput => {
      output.print(s"\nInvalid input '$invalidInput', please choose from (${Input.validParticipantTypes.mkString(",")}): ")
      None
    }
  }

  def chooseParticipant(): Participant = {
    output.print(s"Choose participant from (${Input.validParticipantTypes.mkString(",")}): ")
    val participantType: String = Util.doUntilSuccess(readParticipantType())
    output.println(s"Participant type is: $participantType")


    output.print(s"Choose participant name: ")
    val name: String = Util.doUntilSuccess(readParticipantName())
    output.println(s"Participant name is: $name")

    val weapon = if (participantType == "computer") {
      RPSWeaponGenerator.randomWeapon
    } else {
      output.print(s"Choose participant weapon from (${RPSWeaponGenerator.allWeapons.map(_.name).mkString(",")}): ")
      Util.doUntilSuccess(readParticipantWeapon())
    }
    output.println(s"Participant weapon is: ${weapon.name}")

    Util.doUntilSuccess(chooseParticipant(participantType, name, weapon))
  }

  def printWinner(winner: Participant): String = {
    s"${winner.name} is the winner with weapon ${winner.weapon.name}!"
  }

  def printDraw(firstParticipant: Participant, secondParticipant: Participant): String = {
    s"The game doesn't have a winner, for ${firstParticipant.name} with weapon ${firstParticipant.weapon.name} and ${secondParticipant.name} with weapon ${secondParticipant.weapon.name}!"
  }

  /**
   * Play game with two participant returning result of the game as a string
   */
  def gameResult(firstParticipant: Participant, secondParticipant: Participant): String = {
      WinnerFinder(firstParticipant, secondParticipant).winner.fold {
        printDraw(firstParticipant, secondParticipant)
      }{
        printWinner
      }
  }
}

object RockPaperScissorsGame extends App with ConsoleInputOutput {
  val driver =  new RockPaperScissorsGameDriver(this, this)
  println(s"Choose first participant")
  val firstParticipant = driver.chooseParticipant()

  println(s"Choose second participant")
  val secondParticipant = driver.chooseParticipant()

  println(driver.gameResult(firstParticipant, secondParticipant))
}





