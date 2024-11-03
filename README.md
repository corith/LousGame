# Lous Game

This is an implementation of "3-13", "diablo", "Chicken". 

This is basically *Gin Rummy*. 

Start with 3 cards, go up to 13. Jokers are wild. And whatever number corresponds to the number of cards total in your hand is wild. (first round 3s are wild).
Each player takes a turn drawing and discarding cards attempting to make runs and melds. First person to have 0 points in their hand "goes out". 

Whatever is in your hand at the end of the round that doesn't fit into a run or a meld counts against you.

## About the software
I created this a while back as a practice program and this is the final version. Finally.

### How to use
```
git clone this repo
cd thisRepo
mvn clean package
java -jar target/LousGame-3.0.jar
```
