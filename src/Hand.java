class Hand
{
    public Card[] hearts            = new Card[Player.round];
    public Card[] diamonds          = new Card[Player.round];
    public Card[] spades            = new Card[Player.round];
    public Card[] clubs             = new Card[Player.round];
    public Card[] wildCards         = new Card[Player.round];
    public Card[] sameNumber        = new Card[Player.round];
    public int heartCount           = 0;
    public int diamondCount         = 0;
    public int spadeCount           = 0;
    public int clubCount            = 0;
    public int wildCount            = 0;
    public int sameNumCount         = 0;
    public int deadwoodCount        = 0;

    public HandNode deadwood;

    // a hand consists of a doubly linked list of HandNodes with the head node being the "main area"
    // deadwood is the players hand - anything left in here at the end of a round
        // goes against the player (player.score = player.score + deadwood.total)
    // deadwood.next points to another HandNode which points to one more hand node.
    // the goal of that is to be able to use these other nodes as a "staging area" for cards that fit into either a run or an "of a kind"
    public Hand()
    {
        deadwood        = new HandNode(null , new Card[Player.round + 1] , null);
        deadwood.next   = new HandNode(deadwood , new Card[Player.round] , new HandNode(deadwood.next , new Card[Player.round] , null));
    }

//*******************************************begin quicksort******************************
    // organizes cards strictly by number

    public static void sortHand(Card[] hand)
    {
        quickCardSort(hand , 0 , hand.length - 1);
    }

    public static void sortHand(Card[] hand , int right)
    {
        if (right > 0)
            quickCardSort(hand , 0 , right - 1);
        else
            return;
    }

    public static void quickCardSort(Card[] array , int leftEnd , int rightEnd)
    {
        int left = leftEnd;
        int right = rightEnd;
        Card pivot = array[(left + right) / 2];

        do
        {
            while (array[left].number < pivot.number) { left += 1; }
            while (array[right].number > pivot.number) { right -= 1; }

            if (left <= right)
            {
                swap(array , left , right);
                left += 1;
                right -= 1;
            }
        }
        while (left <= right);

        if (leftEnd < right)
        {
            quickCardSort(array , leftEnd , right);
        }
        if (left < rightEnd)
        {
            quickCardSort(array , left , rightEnd);
        }
    }

    public static void swap(Card[] hand , int left , int right)
    {
        Card temp = hand[left];
        hand[left] = hand[right];
        hand[right] = temp;
    }

//**************************end quicksort methods*********************

// puts the players hand into suit arrays for sorting
public void distributeHand(Hand hand)
{
    // resets the suit arrays to avoid duplicate entries
    heartCount           = 0;
    diamondCount         = 0;
    spadeCount           = 0;
    clubCount            = 0;
    wildCount            = 0;
    sameNumCount         = 0;
    for (int i = 0; i < hand.deadwood.cards.length; i += 1)
    {
        if (hand.deadwood.cards[i].isWild())
        {
            wildCards[wildCount] = hand.deadwood.cards[i];
            wildCount += 1;
            //hand.deadwood.cards[i] = null;
        }
        else if (hand.deadwood.cards[i].suit == "<3")
        {
            hearts[heartCount] = hand.deadwood.cards[i];
            heartCount += 1;
            //hand.deadwood.cards[i] = null;
        }
        else if (hand.deadwood.cards[i].suit == "<*")
        {
            diamonds[diamondCount] = hand.deadwood.cards[i];
            diamondCount += 1;
            //hand.deadwood.cards[i] = null;
        }
        else if (hand.deadwood.cards[i].suit == "^")
        {
            spades[spadeCount] = hand.deadwood.cards[i];
            spadeCount += 1;
            //hand.deadwood.cards[i] = null;
        }
        else if (hand.deadwood.cards[i].suit == "#")
        {
            clubs[clubCount] = hand.deadwood.cards[i];
            clubCount += 1;
            //hand.deadwood.cards[i] = null;
        }
    } // end for loop for sorting hand into arrays of suit and wilds
    sortHand(hearts , heartCount);
    sortHand(diamonds , diamondCount);
    sortHand(spades , spadeCount);
    sortHand(clubs , clubCount);
}

    // this probably where the decision should be made
    // call some helper function after we've found the runs
    // and the ofAkinds.
    public void findRunsAndMelds(Hand hand)
    {
        distributeHand(hand);
        // make sure distribute is working (debugging code)
        System.out.println();
        System.out.println(wildCards.length + " " + wildCount);
        System.out.println(heartCount + " <3");
        System.out.println(diamondCount + " <*");
        System.out.println(spadeCount + " ^");
        System.out.println(clubCount + " #");
        System.out.println();
        // end distribute test

        if (heartCount >= 3)
            findTheRuns(hearts , heartCount);
        if (diamondCount >= 3)
            findTheRuns(diamonds , diamondCount);
        if (spadeCount >= 3)
            findTheRuns(spades , spadeCount);
        if (clubCount >= 3)
            findTheRuns(clubs , clubCount);

        findTheOfAkinds(deadwood.cards , Player.round);


// loops for testing finding the runs.
        testMethod(heartCount, hearts);
        System.out.println("hearts score is : " + tallyScore(hearts , heartCount));
        testMethod(diamondCount, diamonds);
        System.out.println("diamonds score is : " + tallyScore(diamonds , diamondCount));
        testMethod(spadeCount, spades);
        System.out.println("spades score is : " + tallyScore(spades , spadeCount));
        testMethod(clubCount, clubs);
        System.out.println("clubs score is : " + tallyScore(clubs , clubCount));
        System.out.println("\n***same numbers (" + sameNumCount+") *** ");
        testMethod(sameNumCount , sameNumber);
// end of testing for loops
} // end find runs and melds method

    private void testMethod(int cardCount, Card[] suit) {
        for (int index = 0; index < cardCount; index += 1)
        {
            System.out.println();
            System.out.println(suit[index]);
            System.out.println(suit[index].isARun());
            if (suit[index].isOfAKind())
                System.out.println("yes");
            else
                System.out.println("nah");
        }
    }


    // suitArray is one of the four suits, sorted by distributeHand()
    // count = # of cards with that suit - not sure the algorithm
    // is 100% fool proof but works decently well
    public void findTheRuns(Card[] suitArray , int count)
    {
        int i = 0;
        while (i < count - 1 && i != count - 2)     // count wil be >= 3
        {
            // test to see if the number 2 cards ahead is sequential
            // works because the cards are sorted
            if (suitArray[i].number == (suitArray[i + 2].number) - 2)
            {
                suitArray[i].makeItRun();
                suitArray[i + 1].makeItRun();
                suitArray[i + 2].makeItRun();
                i += 2;
                // check if the next card fits into the run or not
                while (i < count - 1 && suitArray[i].number == (suitArray[i + 1].number - 1))
                {
                    i += 1;
                    suitArray[i].makeItRun();
                }
            }
            i += 1;
        }
    }

    // Todo
    //  find the ofAKinds (3 3's 4 5's etc.) sameNumber[]
    public void findTheOfAkinds(Card[] cards , int count)
    {
        Card[] temp = new Card[Player.round];
        Card target;
        Card match;
        int tally = 0;
        sortHand(cards,count);

        System.out.println("count = " + count);
        for (int i = 0; i < count-1; i++)
        {
            target = cards[i]; // get the card that we are looking for matches of
            for (int j = i + 1; j < count-1; j++)
            {
                match = cards[j];
                if (match.number == target.number)
                {
                    tally+=1;
                    temp[tally-1] = match;
                    if (tally >= 3) {
                        sameNumber[0] = target;
                        sameNumber[0].makeItOfAKind();
                        for (int k = 0; k < tally; k++ ) {
                            temp[k].makeItOfAKind();
                            sameNumber[sameNumCount] = temp[k];
                            sameNumCount+=1;
                        }
                    }
//                        tally = 0;
                }
            }
        }
    }

    // Todo
    //  distribute the wild cards so that they negate the most points possible
    public void useWilds()
    {

    }

    // Todo
    //  helper function for maximizing the points in a players hand (use this card in a run or an ofAkind)
    public void maximizePoints()
    {

    }

    // Todo: figure out if this belongs here or in Player.java
    public static int tallyScore(Card[] hand , int count)
    {

        int scoreSum = 0;
        for (int i = 0; i < count; i += 1)
        {
            if (hand[i].number > 0 && !hand[i].isOfAKind() && !hand[i].isARun())
            {
                scoreSum = scoreSum + hand[i].number;
            }
        }
        return scoreSum;
    }

} // end class Hand











/**************** BEIGN MAIN METHOD FOR TESTS ***********/

class HandTest
{
    public static void main(String[] args)
    {
        Player test = new Player();
        Player test1 = new Player();
        Player test2 = new Player();

        Card[] deck = DeckOfCards.getDeck();
        deck = DeckOfCards.shuffleDeck(deck);
        DeckOfCards.dealDeck(deck , test , test1 , test2);

        System.out.println(test.hand.deadwood.count);
        System.out.println("deadwood count: " + test.hand.deadwood.count);
        System.out.println("deadwood sorted:");
        test.getHand();

//        test.hand.findRunsAndMelds(test.hand);
        System.out.println("score = " + test.tallyScore());
    }
}
