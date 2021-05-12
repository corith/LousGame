# LousG-fr-2.0
Round two of Lous Stupid Game - AKA "Chicken" - The Command Line implementation :)


This game is known as Diablo, 3-13, Chicken, Lous Stupid Game, etc...

Here is a good source for getting up to speed with the game
    - https://www.pagat.com/rummy/3-13.html

##### THIS GAME DOES NOT FOLLOW THE SOURCE(s) EXACTLY

## How to run

1. git clone https://github.com/corith/LousG-fr-2.0.git
2. cd into LousG-fr-2.0/src
3. ./run.py
4. OR run `javac LousReady.java && java LousReady`


## Testing scripts

- There are several testing scripts that can be used to run and remove .class files as well as test the Hand.java class functionality.

- The scripts should "just work" on \*nix systems
    - If they do not though, try making an 'out' directory in the root of the repo

- These are all python scripts and for now are simply contained within the src directory
    - these will probably be moved to their own directory as more are created
    - run.py will run the program and remove .class files after it terminates
    - cpuVcpu.py will run a simulation until a succesful game is played and remove .class files after terminating (will time out after 10 attempts)

## Contributing

- If you would like to contribute or fork feel free :) There are some potential things to implement below, feel free to add additional implementation ideas as well!

- If you wish to contribute please just make a descriptive branch and then make your PR! :)

- LousReady.java is main driver for the program.

### Todos!

- implement wild cards (LG-100)
    - incorporate them into the computer decision making system

- improve "decision" system of the computer player (LG-101)
    - it currently it prioritizes ofAKinds over runs
        - add the ability for computer to prioritize specifc runs over ofAKinds and vice versa 

- fine tune the "discard" and "pick-up" interface for the player (LG-102)
    - possibly a "vim like" interface for the four suits
    - allow player to type letter instead of number for face cards and Ace (1,11,12,13)

- fine tune the way cards are displayed as well as how runs and melds are displayed (LG-103)

- create an interface that allows for selection of "user play" or simulation "mode" at game start (LG-104)

- looking to refactor the players hand so that instead of playerOne.hand.deadwood.cards[i] it will be playerOne.hand.cards[i]

- add the ability for a player to insert their name at start of game

#### Far out
    - try to implement an SSH multiplayer version
    - turn it into a gui game with something like swing or an iOS app...
