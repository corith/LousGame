# LousG-fr-2.0
Round two of 3-13 command line Implementation :)


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

- If you would like to contribute or fork, I would be honored ;). There are some potential thigns to implement below. But,of course implement anything you want!

- If you wish to contribute please just make a descriptive branch...and then make your  PR! :)


### Potential Things to Implement!
    - recognize an "out" hand
	- this is a hand where every card is part of some set of 3 or more cards

    - potentially work PlayWizard into checking for winning hand?
        - or add a referee class? 

    - make a computer turn (probably make the best move possible to start?)

    - improve interface
        - 1) potentially display cards better
        - 2) accept KQJA or 11\12\13\1

    - work on scoring the hand
        - still need to implement a decision system
            - currently it prioritizes ofAKinds over runs

    - probably do a complete overhaul of AssDriver.java
        - clean it up as well AND set it up for more functionality

    - look into the dynamic of AssDriver and PlayWizard
        - I like the idea of PlayWizard but it only has one function right now and I am not sure
            it belongs in that class.
        - but this class may have a good purpose for other stuff


### Refactor
    - looking to refactor the players hand so that instead of playerOne.hand.deadwood.cards[i] it will be
	playerOne.hand.cards[i]

    - userTakeTurn()
        - maybe put theOption has player attribute like getPlayerChoice(return this.playersChoice;)
        - maybe put topCard as an attribute of DeckOfCards ?? 
