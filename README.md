# rock-paper-scissors [![Build Status](https://travis-ci.org/chavdarch/rock-paper-scissors.svg?branch=master)](https://travis-ci.org/chavdarch/rock-paper-scissors) [![Coverage Status](https://coveralls.io/repos/chavdarch/rock-paper-scissors/badge.svg?branch=master&service=github)](https://coveralls.io/github/chavdarch/rock-paper-scissors?branch=master)
Rock paper scissors game implementation

This is game between two players with ability to chose player type(human, computer) and name.
Computer generates weapon randomly using secure random generation, which requires sudo access on the machine running the project

Game can be easily extended for more weapons( `rock-paper-scissors-lizard-Spock` example provided in the code)

## Prerequisites
 scala 2.11.7
 java 8
 sbt

## How to run
Clone the repo and call `sbt run` prom the project directory

You can also run the project by executing bin/start.sh script from any directory.

## How to contribute
 Create a new branch do your changes there and submit pull request.
 Make sure travis build is succesfull and code coverage is at least 80%
## How to run test & check code coverage
  `sbt clean coverage test`
## Continuous integration and code coverage tools
   * travis build - https://travis-ci.org/chavdarch/rock-paper-scissors
   * coveralls - https://coveralls.io/github/chavdarch/rock-paper-scissors
  
## Sample output

```
    Choose first participant
    Choose participant from (player,computer): player
    Participant type is: player
    Choose participant name: Chavdar
    Participant name is: Chavdar
    Choose participant weapon from (rock,scissors,paper): paper
    Participant weapon is: paper
    Choose second participant
    Choose participant from (player,computer): player
    Participant type is: player
    Choose participant name: Enemy
    Participant name is: Enemy
    Choose participant weapon from (rock,scissors,paper): rock
    Participant weapon is: rock
    Chavdar is the winner with weapon paper!
```

  


