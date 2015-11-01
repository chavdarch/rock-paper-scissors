package example.rock.paper.scissors


import java.io._

import example.rock.paper.scissors.RPSWeaponGenerator.Paper
import org.scalatest.{FlatSpec, Matchers}

import scala.Console

class RockPaperScissorsGameSpec extends FlatSpec with Matchers {
  val gameHelper = new RockPaperScissorsGameHelper {}
  def withOutput(out: OutputStream = new ByteArrayOutputStream()): Output = new Output {
    override def writer: PrintStream = new PrintStream(out)
  }
    "An RockPaperScissorsGameHlper" should "allow to choose player" in {
      val result: Option[Participant] = gameHelper.chooseParticipant("player", "testName", Paper)(withOutput())
      result.isDefined should be(true)
      result.get should be(Player("testName", Paper))
    }

    it should "allow to choose computer" in {
      val result: Option[Participant] = gameHelper.chooseParticipant("computer", "testName", Paper)(withOutput())
      result.isDefined should be(true)
      result.get should be(Computer("testName", Paper))
    }

    it should "give error message when neither computer not player was chosen" in {
      val errorStream = new ByteArrayOutputStream()
      val result: Option[Participant] = gameHelper.chooseParticipant("unknownParticipant", "testName", Paper)(withOutput(errorStream))
      result.isDefined should be(false)
      errorStream.toString should be("\nInvalid input 'unknownParticipant', please choose from (player,computer): ")
    }


  "An Input" should "allow reading from an input reader" in {
    val input = new Input {
      val stringReader: StringReader = new StringReader("testString")
      override def reader: BufferedReader = new BufferedReader(stringReader)
    }
    input.read() should be("testString")
  }

  it should "allow to choose player as participant" in {
    val input = new Input {
      val stringReader: StringReader = new StringReader("player")
      override def reader: BufferedReader = new BufferedReader(stringReader)
    }

    input.readParticipantType(withOutput()) should be (Some("player"))
  }

  it should "allow to choose computer as participant" in {
    val input = new Input {
      val stringReader: StringReader = new StringReader("computer")
      override def reader: BufferedReader = new BufferedReader(stringReader)
    }

    input.readParticipantType(withOutput()) should be (Some("computer"))
  }

  it should "print error and retry when participant is not player or computer" in {
    val input = new Input {
      val stringReader: StringReader = new StringReader("unknown")
      override def reader: BufferedReader = new BufferedReader(stringReader)
    }

    val errorStream = new ByteArrayOutputStream()
    input.readParticipantType(withOutput(errorStream)) should be (None)
    errorStream.toString should be("\nInvalid input 'unknown', please choose from (player,computer): ")
  }

  it should "allow to choose participant name" in {
    val input = new Input {
      val stringReader: StringReader = new StringReader("nameOfParticipant")
      override def reader: BufferedReader = new BufferedReader(stringReader)
    }

    input.readParticipantName(withOutput()) should be (Some("nameOfParticipant"))
  }

  it should "print error when participant name is empty string" in {
    val input = new Input {
      val stringReader: StringReader = new StringReader("")
      override def reader: BufferedReader = new BufferedReader(stringReader)
    }

    val errorStream = new ByteArrayOutputStream()
    input.readParticipantName(withOutput(errorStream)) should be (None)
    errorStream.toString should be("\nParticipant name should not be empty, try again: ")
  }

  it should "allow to choose participant weapon" in {
    def input(name:String) = new Input {
      val stringReader: StringReader = new StringReader(name)
      override def reader: BufferedReader = new BufferedReader(stringReader)
    }

    RPSWeaponGenerator.allWeapons.foreach{ weapon =>
      input(weapon.name).readParticipantWeapon(withOutput()) should be (Some(weapon))
    }
  }

  it should "print error when participant chooses unknown weapon" in {
    val input = new Input {
      val stringReader: StringReader = new StringReader("unknownWeapon")
      override def reader: BufferedReader = new BufferedReader(stringReader)
    }

    val errorStream = new ByteArrayOutputStream()
    input.readParticipantWeapon(withOutput(errorStream)) should be (None)
    errorStream.toString should be("\nInvalid input 'unknownWeapon', please choose from (rock,scissors,paper): ")
  }


  "An Output" should "allow printing to a writer" in {
    val outStream = new ByteArrayOutputStream()
    val output = new Output {
      override def writer: PrintStream = new PrintStream(outStream)
    }

    output.print("testString")
    outStream.toString should be("testString")
  }

  "A ConsoleInputOutput" should "allow reading to and writing from console" in {
    Console.withIn(new StringReader("testString")) {
      val inAndOut = new ConsoleInputOutput {}
      inAndOut.read() should be("testString")
    }

    val writer = new java.io.ByteArrayOutputStream()
    Console.withOut(writer) {
        val inAndOut = new ConsoleInputOutput {}
        inAndOut.print("testPrint")
        writer.toString should be ("testPrint")
    }
  }
}




