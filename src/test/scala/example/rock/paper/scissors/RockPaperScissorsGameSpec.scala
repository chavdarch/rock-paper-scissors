package example.rock.paper.scissors


import java.io._

import example.rock.paper.scissors.RPSWeaponGenerator.Paper
import org.scalatest.{FlatSpec, Matchers}

class RockPaperScissorsGameSpec extends FlatSpec with Matchers with InputOutputTestUtils {
  def gameHelper(input: String = "testInput", out: OutputStream = new ByteArrayOutputStream()) = new RockPaperScissorsGameDriver(withInput(input), withOutput(out))


  "An RockPaperScissorsGameDriver" should "allow to choose player" in {
    val result: Option[Participant] = gameHelper().chooseParticipant("player", "testName", Paper)
    result.isDefined should be(true)
    result.get should be(Player("testName", Paper))
  }

  it should "allow to choose computer" in {
    val result: Option[Participant] = gameHelper().chooseParticipant("computer", "testName", Paper)
    result.isDefined should be(true)
    result.get should be(Computer("testName", Paper))
  }

  it should "give error message when neither computer not player was chosen" in {
    val out = new ByteArrayOutputStream()

    val result: Option[Participant] = gameHelper(out = out).chooseParticipant("unknownParticipant", "testName", Paper)
    result.isDefined should be(false)
    out.toString should be("\nInvalid input 'unknownParticipant', please choose from (player,computer): ")
  }

  it should "allow to choose player as participant" in {
    gameHelper("player").readParticipantType() should be(Some("player"))
  }

  it should "allow to choose computer as participant" in {
    gameHelper("computer").readParticipantType() should be(Some("computer"))
  }

  it should "print error and retry when participant is not player or computer" in {
    val out = new ByteArrayOutputStream()

    val driver = gameHelper("unknown", out)
    driver.readParticipantType() should be(None)
    out.toString should be("\nInvalid input 'unknown', please choose from (player,computer): ")
  }

  it should "allow to choose participant name" in {
    gameHelper("nameOfParticipant").readParticipantName() should be(Some("nameOfParticipant"))
  }

  it should "print error when participant name is empty string" in {
    val out = new ByteArrayOutputStream()
    val driver  = gameHelper("", out)

    driver.readParticipantName() should be(None)
    out.toString should be("\nParticipant name should not be empty, try again: ")
  }

  it should "allow to choose participant weapon" in {
    RPSWeaponGenerator.allWeapons.foreach { weapon =>
      gameHelper(weapon.name).readParticipantWeapon() should be(Some(weapon))
    }
  }

  it should "print error when participant chooses unknown weapon" in {
    val out = new ByteArrayOutputStream()
    val driver  = gameHelper("unknownWeapon", out)

    driver.readParticipantWeapon() should be(None)
    out.toString should be("\nInvalid input 'unknownWeapon', please choose from (rock,scissors,paper): ")
  }


}




