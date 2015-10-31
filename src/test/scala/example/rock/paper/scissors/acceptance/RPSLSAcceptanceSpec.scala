package example.rock.paper.scissors.acceptance

import example.rock.paper.scissors.RPSLSWeaponGenerator.{Lizard, Spock}
import example.rock.paper.scissors.{Computer, Participant, Player, WinnerFinder}
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
 * A bonus acceptance test for rock-paper-scissors-lizard-spock, created with the purpose of proving current solution extensibility
 */
class RPSLSAcceptanceSpec extends FeatureSpec with GivenWhenThen  {
  info("I’d like to play rock, paper, scissors, lizard, spock")

  feature("Player v Computer") {
    scenario("Lizard beats spock") {
      Given("Player picks lizard")
      val player = new Player("player", Lizard)
      assert(player.weapon == Lizard)

      When("Computer picks spock")
      val computer = Computer.rpsComputer("computer", Spock)
      assert(computer.weapon == Spock)


      Then("player should beat the computer")
      val winner: Option[Participant] = WinnerFinder(player, computer).winner
      assert(winner.isDefined)
      assert(winner.get == player)
    }
  }

}
