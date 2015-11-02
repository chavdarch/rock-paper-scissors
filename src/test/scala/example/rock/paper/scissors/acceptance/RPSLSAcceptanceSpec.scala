package example.rock.paper.scissors.acceptance

import example.rock.paper.scissors.RPSLSWeaponGenerator.{Lizard, Spock}
import example.rock.paper.scissors._
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
      val computer = Computer("computer", Spock)
      assert(computer.weapon == Spock)


      Then("player should beat the computer")
      val winner: Option[Participant] = WinnerFinder(player, computer).winner
      assert(winner.isDefined)
      assert(winner.get == player)
    }
  }
  feature("Computer v Computer") {
    scenario("A different game each time") {
      Given("1 thousand times two consecutive weapons are generated")
      val oneThousand = 1000.0

      When("check how often weapon generations are the same ")
      val sameWeaponInSequenceCount = (1 to oneThousand.toInt).map {
        _ => RPSLSWeaponGenerator.randomWeapon == RPSLSWeaponGenerator.randomWeapon
      }.count(identity)

      Then("verify every two consecutive weapon generations are independent, with probability 1/numberOfWeapons each")
      assert(Math.round(oneThousand / sameWeaponInSequenceCount) == RPSLSWeaponGenerator.allWeapons.size)
    }
  }
}
