package example.rock.paper.scissors

case class WinnerFinder(participantOne: Participant, participantTwo: Participant) {
  def winner: Option[Participant] = {
    if(participantOne.weapon.beats.contains(participantTwo.weapon))
      Some(participantOne)
    else if(participantTwo.weapon.beats.contains(participantOne.weapon))
      Some(participantTwo)
    else
      None
  }

}
