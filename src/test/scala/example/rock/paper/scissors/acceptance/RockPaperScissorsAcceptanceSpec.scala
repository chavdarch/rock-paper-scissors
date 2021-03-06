package example.rock.paper.scissors.acceptance

import example.rock.paper.scissors.RPSWeaponGenerator.{Paper, Scissors, Rock}
import example.rock.paper.scissors._
import org.scalatest.{FeatureSpec, GivenWhenThen}

class RockPaperScissorsAcceptanceSpec  extends FeatureSpec with GivenWhenThen {
  info("As a frequent games player")
  info("I’d like to play rock, paper, scissors")
  info("So that I can spend an hour of my day having fun")

  feature("Player v Computer") {
    scenario("Rock beats scissors") {
      Given("Player picks rock")
      val player = new Player("player", Rock)
      assert(player.weapon == Rock)

      When("Computer picks scissors")
      val computer = Computer("computer", Scissors)
      assert(computer.weapon == Scissors)


      Then("player should beat the computer")
      val winner: Option[Participant] = WinnerFinder(player, computer).winner
      assert(winner.isDefined)
      assert(winner.get == player)
    }

    scenario("Scissors beats paper") {
      Given("Player picks scissors")
      val player = new Player("player", Scissors)
      assert(player.weapon == Scissors)

      When("Computer picks paper")
      val computer = Computer("computer", Paper)
      assert(computer.weapon == Paper)


      Then("player should beat the computer")
      val winner: Option[Participant] = WinnerFinder(player, computer).winner
      assert(winner.isDefined)
      assert(winner.get == player)
    }

    scenario("Paper beats rock") {
      Given("Player picks rock")
      val player = new Player("player", Rock)
      assert(player.weapon == Rock)

      When("Computer picks paper")
      val computer = Computer("computer", Paper)
      assert(computer.weapon == Paper)


      Then("computer should beat the player")
      val winner: Option[Participant] = WinnerFinder(player, computer).winner
      assert(winner.isDefined)
      assert(winner.get == computer)
    }

    scenario("Paper draws paper") {
      Given("Player picks paper")
      val player = new Player("player", Paper)
      assert(player.weapon == Paper)

      When("Computer picks paper")
      val computer = Computer("computer", Paper)
      assert(computer.weapon == Paper)


      Then("computer should draw the player")
      val winner: Option[Participant] = WinnerFinder(player, computer).winner
      assert(winner.isEmpty)
    }

    scenario("Rock draws rock") {
      Given("Player picks rock")
      val player = new Player("player", Rock)
      assert(player.weapon == Rock)

      When("Computer picks rock")
      val computer = Computer("computer", Rock)
      assert(computer.weapon == Rock)


      Then("computer should draw the player")
      val winner: Option[Participant] = WinnerFinder(player, computer).winner
      assert(winner.isEmpty)
    }


    scenario("Scissors draws scissors") {
      Given("Player picks scissors")
      val player = new Player("player", Scissors)
      assert(player.weapon == Scissors)

      When("Computer picks scissors")
      val computer = Computer("computer", Scissors)
      assert(computer.weapon == Scissors)


      Then("computer should draw the player")
      val winner: Option[Participant] = WinnerFinder(player, computer).winner
      assert(winner.isEmpty)
    }
  }

  feature("Computer v Computer") {
    scenario("A different game each time") {
      Given("1 thousand times two consecutive weapons are generated")
      val oneThousand = 1000.0

      When("check how often weapon generations are the same ")
      val sameWeaponInSequenceCount = (1 to oneThousand.toInt).map {
        _ =>  RPSWeaponGenerator.randomWeapon == RPSWeaponGenerator.randomWeapon
      }.count(identity)

      Then("verify every two consecutive weapon generations are independent, with probability 1/numberOfWeapons each")
      assert(Math.round(oneThousand / sameWeaponInSequenceCount) == RPSWeaponGenerator.allWeapons.size)
    }

    scenario("Rock beats scissors") {
      Given("Computer one picks rock")
      val computerOne = Computer("computerOne", Rock)
      assert(computerOne.weapon == Rock)

      When("Computer two picks scissors")
      val computerTwo = Computer("computer", Scissors)
      assert(computerTwo.weapon == Scissors)


      Then("computer one should beat the computer two")
      val winner: Option[Participant] = WinnerFinder(computerOne, computerTwo).winner
      assert(winner.isDefined)
      assert(winner.get == computerOne)
    }

    scenario("Scissors beats paper") {
      Given("Computer one picks scissors")
      val computerOne = Computer("computerOne", Scissors)
      assert(computerOne.weapon == Scissors)

      When("Computer two picks paper")
      val computerTwo = Computer("computerTwo", Paper)
      assert(computerTwo.weapon == Paper)


      Then("computer one should beat compuer two")
      val winner: Option[Participant] = WinnerFinder(computerOne, computerTwo).winner
      assert(winner.isDefined)
      assert(winner.get == computerOne)
    }

    scenario("Paper beats rock") {
      Given("Computer one picks rock")
      val computerOne = Computer("computerOne", Rock)
      assert(computerOne.weapon == Rock)

      When("Computer two picks paper")
      val computerTwo = Computer("computerTwo", Paper)
      assert(computerTwo.weapon == Paper)


      Then("computer two should beat computer one")
      val winner: Option[Participant] = WinnerFinder(computerOne, computerTwo).winner
      assert(winner.isDefined)
      assert(winner.get == computerTwo)
    }

    scenario("Paper draws paper") {
      Given("Computer one picks paper")
      val computerOne = Computer("computerOne", Paper)
      assert(computerOne.weapon == Paper)

      When("Computer two picks paper")
      val computerTwo = Computer("computerTwo", Paper)
      assert(computerTwo.weapon == Paper)


      Then("computer one should draw computer two")
      val winner: Option[Participant] = WinnerFinder(computerOne, computerTwo).winner
      assert(winner.isEmpty)
    }

    scenario("Rock draws rock") {
      Given("Computer one picks rock")
      val computerOne = Computer("computerOne", Rock)
      assert(computerOne.weapon == Rock)

      When("Computer two picks rock")
      val computerTwo = Computer("computerTwo", Rock)
      assert(computerTwo.weapon == Rock)


      Then("computer one should draw computer two")
      val winner: Option[Participant] = WinnerFinder(computerOne, computerTwo).winner
      assert(winner.isEmpty)
    }


    scenario("Scissors draws scissors") {
      Given("Computer one picks scissors")
      val computerOne = Computer("computerOne", Scissors)
      assert(computerOne.weapon == Scissors)

      When("Computer picks scissors")
      val computerTwo= Computer("computerTwo", Scissors)
      assert(computerTwo.weapon == Scissors)


      Then("computer one should draw computer two")
      val winner: Option[Participant] = WinnerFinder(computerOne, computerTwo).winner
      assert(winner.isEmpty)
    }
  }

}