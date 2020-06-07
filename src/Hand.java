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

    public Hand()
    {
        deadwood        = new HandNode(null , new Card[Player.round + 1] , null);
        deadwood.next   = new HandNode(deadwood , new Card[Player.round] , new HandNode(deadwood.next , new Card[Player.round] , null));
    }

//*******************************************begin quicksort******************************

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
    //while (hand.deadwood. != null)
    //{
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
        //hand.deadwood = hand.deadwood.next;
    //}
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

        // find the of a kinds x 4
        // redistribute(Hand hand); ????


// loops for testing finding the runs.
        for (int index = 0; index < heartCount; index += 1)
        {
            System.out.println();
            System.out.println(hearts[index]);
            System.out.println(hearts[index].isARun());
        }
        System.out.println("hearts score is : " + tallyScore(hearts , heartCount));
        for (int index = 0; index < diamondCount; index += 1)
        {
            System.out.println();
            System.out.println(diamonds[index]);
            System.out.println(diamonds[index].isARun());
        }
        System.out.println("diamonds score is : " + tallyScore(diamonds , diamondCount));
        for (int index = 0; index < spadeCount; index += 1)
        {
            System.out.println();
            System.out.println(spades[index]);
            System.out.println(spades[index].isARun());
        }
        System.out.println("spades score is : " + tallyScore(spades , spadeCount));
        for (int index = 0; index < clubCount; index += 1)
        {
            System.out.println();
            System.out.println(clubs[index]);
            System.out.println(clubs[index].isARun());
        }
        System.out.println("clubs score is : " + tallyScore(clubs , clubCount));
// end of testing for loops
} // end find runs and melds method


    // suitArray is one of the four suits, sorted by distributeHand()
    // count = # of cards with that suit - not sure the algorithm
    // is 100% fool proof but works decently well
    public void findTheRuns(Card[] suitArray , int count)
    {
        int i = 0;
        while (i < count - 1 && i != count - 2)     // count wil be >= 3
        {
            System.out.println("one time through");
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
    //  find the ofAKinds (3 3's 4 5's etc.)
    public void findTheOfAkinds()
    {

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
            if (hand[i].number > 0 && !hand[i].isAMeld() && !hand[i].isARun())
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
