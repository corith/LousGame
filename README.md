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

## Contributing

- If you would like to contribute or fork, I would be honored ;). There are some potential things to implement below, feel free to add additional implementation ideas!

- If you wish to contribute please just make a descriptive branch and then make your PR! :)

### Potential Todos!

- fix the "overstayed welcome" discard card
    - currently has an issue with discard card where player may be presented with the card they discarded

- make it so the discard cards are saved and reshuffled when the deck runs out of cards
    - make this true for added decks as well

- implement wild cards
    - incorporate them into the computer decision making system

- improve "decision" system of the computer player
    - currently it prioritizes ofAKinds over runs
    - it also does not consider the value of cards in a pair - will drop a card in a pair sometimes instead of a single unused card

- fine tune the "discard" and "pick-up" interface for the player
    - possibly a "vim like" interface for the four suits
    - allow player to type letter instead of number for face cards and Ace (1,11,12,13)

- fine tune the way cards are displayed as well as how runs and melds are displayed

- create an interface that allows for selection of play or simulation "mode" at game start

- fix any crashes that occur sporadically
    - I believe it occurs when sorting the hand

- looking to refactor the players hand so that instead of playerOne.hand.deadwood.cards[i] it will be playerOne.hand.cards[i]

- look into the function between AssDriver and PlayWizard
    - I like the idea of PlayWizard but it only has one function right now and I am not sure it belongs in that class.
    - but this class may have a good purpose for other stuff
    - potentially work PlayWizard into checking for winning hand? - or add a referee class?

- add the ability for a player to insert their name at start of game

#### Far out
    - try to implement an SSH multiplayer version
    - turn it into a gui game with something like swing
