package example.rock.paper.scissors.acceptance

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
      val computer = Computer.rpsComputer("computer", Scissors)
      assert(computer.weapon == Scissors)


      Then("player should beat the computer")
      val winner: Option[Participant] = RPSGame(player, computer).findWinner()
      assert(winner.isDefined)
      assert(winner.get == player)
    }

    scenario("Scissors beats paper") {
      Given("Player picks scissors")
      val player = new Player("player", Scissors)
      assert(player.weapon == Scissors)

      When("Computer picks paper")
      val computer = Computer.rpsComputer("computer", Paper)
      assert(computer.weapon == Paper)


      Then("player should beat the computer")
      val winner: Option[Participant] = RPSGame(player, computer).findWinner()
      assert(winner.isDefined)
      assert(winner.get == player)
    }

    scenario("Paper beats rock") {
      Given("Player picks rock")
      val player = new Player("player", Rock)
      assert(player.weapon == Rock)

      When("Computer picks paper")
      val computer = Computer.rpsComputer("computer", Paper)
      assert(computer.weapon == Paper)


      Then("computer should beat the player")
      val winner: Option[Participant] = RPSGame(player, computer).findWinner()
      assert(winner.isDefined)
      assert(winner.get == computer)
    }

    scenario("Paper draws paper") {
      Given("Player picks paper")
      val player = new Player("player", Paper)
      assert(player.weapon == Paper)

      When("Computer picks paper")
      val computer = Computer.rpsComputer("computer", Paper)
      assert(computer.weapon == Paper)


      Then("computer should draw the player")
      val winner: Option[Participant] = RPSGame(player, computer).findWinner()
      assert(winner.isEmpty)
    }

    scenario("Rock draws rock") {
      Given("Player picks rock")
      val player = new Player("player", Rock)
      assert(player.weapon == Rock)

      When("Computer picks rock")
      val computer = Computer.rpsComputer("computer", Rock)
      assert(computer.weapon == Rock)


      Then("computer should draw the player")
      val winner: Option[Participant] = RPSGame(player, computer).findWinner()
      assert(winner.isEmpty)
    }


    scenario("Scissors draws scissors") {
      Given("Player picks scissors")
      val player = new Player("player", Scissors)
      assert(player.weapon == Scissors)

      When("Computer picks scissors")
      val computer = Computer.rpsComputer("computer", Scissors)
      assert(computer.weapon == Scissors)


      Then("computer should draw the player")
      val winner: Option[Participant] = RPSGame(player, computer).findWinner()
      assert(winner.isEmpty)
    }
  }

  feature("Computer v Computer") {
    scenario("A different game each time") {
      Given("1 million times two consecutive weapons are generated")
      val oneMillion = 1000000.0

      When("check how often weapon generations are the same ")
      val sameWeaponInSequenceCount = (1 to oneMillion.toInt).map{
        _ =>Computer.rpsComputer("computer").weapon == Computer.rpsComputer("computer").weapon
      }.filter(identity).size

      Then("verify every two consecutive weapon generations are independent, with probability 1/numberOfWeapons each")
      assert(Math.round(oneMillion / sameWeaponInSequenceCount)  == Computer.rpsComputer("computer").numberOfWeapons)
    }
  }

}