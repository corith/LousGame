# Lous Game

This is Lous Game. 

Or "3-13", "diablo", "Chicken". 

This is basically an implementation of *Gin Rummy*. 

Start with 3 cards, go up to 13. Jokers are wild. And whatever number corresponds to the number of cards total in your hand is wild. (first round 3s are wild).
Each player takes a turn drawing and discarding cards attempting to make runs and melds. First person to have 0 points in their hand "goes out". 

Whatever is in your hand at the end of the round that doesn't fit into a run or a meld counts against you.

The player with the fewest amount of points at the end of the final round wins.

### About the software
This was the first ever computer program that I developed on my own when I started learning how to code. 
I learned a lot but never really finished it. Was a giant pile of spaghetti and I finally decided to finish it.

Originally I wanted to use the most basic code possible and really did everything from scratch. I learned a lot 
but ended up making things way more complicated than needed. 

With this final implementation I tried to keep it as simple as possible while not doing everything from scratch just because.

This is the final version.

#### What it can do
- 3 player computer simulation of Gin Rummy style game

#### What's coming?
- Support for ```UserPlayer```
- Optimized hand creation algorithm
- Polished UI/UX
- Enhanced "statistic"
- Enhanced simulations
- Difficulty levels

#### Issues
- The computer's decision-making needs optimizing
- Humans can't participate
- 🐛

### Build & Run

- *git clone* ```git@github.com:corith/LousGame.git```
- ```cd``` *LousGame*
- *mvn clean package* ```-DskipTests```
- *java -jar* target/LousGame-3.0.<u>jar</u>

#### Developed by
Cory Sebastian