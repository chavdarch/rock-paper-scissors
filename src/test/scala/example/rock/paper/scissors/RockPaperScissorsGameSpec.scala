package example.rock.paper.scissors


import java.io._

import example.rock.paper.scissors.RPSWeaponGenerator.{Rock, Paper}
import org.scalatest.{FlatSpec, Matchers}

class RockPaperScissorsGameSpec extends FlatSpec with Matchers with InputOutputTestUtils {
  def gameDriver(input: String = "testInput", out: OutputStream = new ByteArrayOutputStream()) = new RockPaperScissorsGameDriver(withInput(input), withOutput(out))


  "An RockPaperScissorsGameDriver" should "allow to choose player" in {
    val result: Option[Participant] = gameDriver().chooseParticipant("player", "testName", Paper)
    result.isDefined should be(true)
    result.get should be(Player("testName", Paper))
  }

  it should "allow to choose computer" in {
    val result: Option[Participant] = gameDriver().chooseParticipant("computer", "testName", Paper)
    result.isDefined should be(true)
    result.get should be(Computer("testName", Paper))
  }

  it should "give error message when neither computer not player was chosen" in {
    val out = new ByteArrayOutputStream()

    val result: Option[Participant] = gameDriver(out = out).chooseParticipant("unknownParticipant", "testName", Paper)
    result.isDefined should be(false)
    out.toString should be("\nInvalid input 'unknownParticipant', please choose from (player,computer): ")
  }

  it should "allow to choose player as participant" in {
    gameDriver("player").readParticipantType() should be(Some("player"))
  }

  it should "allow to choose computer as participant" in {
    gameDriver("computer").readParticipantType() should be(Some("computer"))
  }

  it should "print error and retry when participant is not player or computer" in {
    val out = new ByteArrayOutputStream()

    val driver = gameDriver("unknown", out)
    driver.readParticipantType() should be(None)
    out.toString should be("\nInvalid input 'unknown', please choose from (player,computer): ")
  }

  it should "allow to choose participant name" in {
    gameDriver("nameOfParticipant").readParticipantName() should be(Some("nameOfParticipant"))
  }

  it should "print error when participant name is empty string" in {
    val out = new ByteArrayOutputStream()
    val driver  = gameDriver("", out)

    driver.readParticipantName() should be(None)
    out.toString should be("\nParticipant name should not be empty, try again: ")
  }

  it should "allow to choose participant weapon" in {
    RPSWeaponGenerator.allWeapons.foreach { weapon =>
      gameDriver(weapon.name).readParticipantWeapon() should be(Some(weapon))
    }
  }

  it should "print error when participant chooses unknown weapon" in {
    val out = new ByteArrayOutputStream()
    val driver  = gameDriver("unknownWeapon", out)

    driver.readParticipantWeapon() should be(None)
    out.toString should be("\nInvalid input 'unknownWeapon', please choose from (rock,scissors,paper): ")
  }

  it should "choose computer or player participant" in {
    val driver = gameDriver()

    RPSWeaponGenerator.allWeapons.foreach{ weapon =>
      driver.chooseParticipant("player", "testName", weapon) should be(Some(Player("testName", weapon)))
      driver.chooseParticipant("computer", "testName", weapon) should be(Some(Computer("testName", weapon)))
    }
  }

  it should "print error when trying to choose unknown participant type" in {
    RPSWeaponGenerator.allWeapons.foreach { weapon =>
      val out = new ByteArrayOutputStream()
      val driver  = gameDriver(out = out)

      driver.chooseParticipant("unknownParticipant", "testName", weapon) should be(None)
      out.toString should be("\nInvalid input 'unknownParticipant', please choose from (player,computer): ")
    }
  }

  it should "be able to register participant choice as player" in {
    val out = new ByteArrayOutputStream()
    val mockDriver = new RockPaperScissorsGameDriver(withInput(""), withOutput(out)){
      override def readParticipantType(validTypes: Seq[String] = Input.validParticipantTypes): Option[String] = Some("player")
      override def readParticipantName(): Option[String] = Some("testName")
      override def readParticipantWeapon(validWeapons: Set[Weapon] = RPSWeaponGenerator.allWeapons): Option[Weapon] = Some(Paper)
    }

    mockDriver.requestParticipantChoice() should be(Player("testName", Paper))
  }

  it should "be able to register participant choice as computer" in {
    val out = new ByteArrayOutputStream()
    val mockDriver = new RockPaperScissorsGameDriver(withInput(""), withOutput(out)){
      override def readParticipantType(validTypes: Seq[String] = Input.validParticipantTypes): Option[String] = Some("computer")
      override def readParticipantName(): Option[String] = Some("testName")
    }

   val result = mockDriver.requestParticipantChoice() match {
     case Computer(name, _)  if name == "testName" => true
     case _ => false
   }

    result should be (true)
  }

  it should "be able to print winning game result" in {
    val player = Player("testPlayer", Paper)
    val computer = Computer("testComputer", Rock)

    gameDriver().gameResult(player, computer) should be("testPlayer is the winner with weapon paper!")
  }

}




