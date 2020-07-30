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

### Potential Things to Implement!
- potentially work PlayWizard into checking for winning hand?
    - or add a referee class? 

- Enhance the computers turn
    - currently has an issue with discard card where player may be presented with the card they discarded 
    - add more intelligent decision system
        - currently it prioritizes ofAKinds over runs
        - it also does not consider the value of cards in a pair - will drop a card in a pair sometimes instead of a single unused card

- improve interface 
    - allow player to type letter instead of number for face cards and Ace (1,11,12,13)

- look into the dynamic of AssDriver and PlayWizard
    - I like the idea of PlayWizard but it only has one function right now and I am not sure it belongs in that class.
    - but this class may have a good purpose for other stuff

### Refactor
    - looking to refactor the players hand so that instead of playerOne.hand.deadwood.cards[i] it will be
	playerOne.hand.cards[i]

