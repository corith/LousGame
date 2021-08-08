/**
 * This class represents a Player's Hand.
 * A Hand consists of a doubly linked list of HandNodes with the head node being the "main card area".
 * Deadwood is the players hand - anything left in here at the end of a round
 * goes against the player (player.score = player score + deadwood total).
 * Deadwood.next points to another HandNode which points to one more hand node.
 * The goal of this is to be able to use these other nodes as "staging areas"
 * for cards that fit into either a run or an "of a kind" and could make it easier
 * to print/display the hand to a player as well as for ComputerPlayers to make more
 * advanced decisions.
 */
class Hand
{
    private Card[] hearts            = new Card[LousReady.getRound() + 1];
    private Card[] diamonds          = new Card[LousReady.getRound() + 1];
    private Card[] spades            = new Card[LousReady.getRound() + 1];
    private Card[] clubs             = new Card[LousReady.getRound() + 1];
    private Card[] wildCards         = new Card[LousReady.getRound() + 1];
    private Card[] sameNumber        = new Card[LousReady.getRound() + 1];
    private int heartCount           = 0;
    private int diamondCount         = 0;
    private int spadeCount           = 0;
    private int clubCount            = 0;
    private int wildCount            = 0;
    private int sameNumCount         = 0;
    private int deadwoodCount        = 0;

    public HandNode deadwood;

    /**
     * Creates a Hand object. This is a doubly linked list.
     * The first node, called deadwood, it's prev points to null and
     * next points to another HandNode that is used to store cards
     * that fit into a 3+ set (333/4567 etc). That node's prev points
     * back to deadwood, and it's next points to another HandNode that
     * contains any remaining cards that fit into a "pair" (3,4/5,5).
     * This node's next points to null. The remaining cards that do not
     * have a set or a pair reside in deadwood and so do wild cards until
     * used either with a set or to complete a pair.
     */
    public Hand()
    {
        deadwood = new HandNode(null , new Card[LousReady.getRound() + 1] , null);
        deadwood.setNext(new HandNode(deadwood , new Card[LousReady.getRound()] , new HandNode(deadwood.getNext() , new Card[LousReady.getRound()] , null)));
    }

    /**
     * This method is used by Player to validate user input.
     * @param card The card the user inputted
     * @return true if the card is in the player's hand and false if not
     */
    public boolean cardIsInHand(Card card)
    {
        for (int i = 0; i < this.deadwood.cards.length; i++)
            if (this.deadwood.cards[i].equals(card))
                return true;
        return false;
    }

    /**
     * This method contains the logic for finding the runs (678...)
     * and melds (888...). It first checks for melds (ofAKinds) and
     * then splits all the cards into arrays based on suits and looks
     * for runs. Currently, if a meld is found first but also fits into
     * a run, then the card will become part of the run. This is currently
     * sort of broken as depending on the situation it may end up breaking
     * a run but not scoring it as such. For example: a hand containing a
     * run of 1234<*5 and a meld of {4<* 4<3 4#} may result in the scoring
     * being 0 points in the hand but this is because the run is counted as
     * 1235 and the meld is counted as 444. This is not correct and will be fixed
     * soon. Also soon this method will add functionality for including wild cards
     * in the finding of runs and melds.
     * @param on a param used for debug/test purposes - won't be here in production
     */
    public void findRunsAndMelds(boolean on)
    {
        clearCardStatus(deadwood.cards);
        this.distributeHand();

        findTheOfAkinds(deadwood.cards , LousReady.getRound());
        if (heartCount >= 3)
            findTheRuns(hearts , heartCount);
        if (diamondCount >= 3)
            findTheRuns(diamonds , diamondCount);
        if (spadeCount >= 3)
            findTheRuns(spades , spadeCount);
        if (clubCount >= 3)
            findTheRuns(clubs , clubCount);

        this.calculateWorth(deadwood.cards);
        //maximizePoints(deadwood.cards);
        //useWilds();

        // loops for testing finding the runs.
        if (on) {
            System.out.println(Ansi.BACKGROUND_RED + Ansi.YELLOW + "***Your Runs***" + Ansi.RESET);
            printRuns(heartCount , hearts);
            printRuns(diamondCount , diamonds);
            printRuns(spadeCount , spades);
            printRuns(clubCount , clubs);
            System.out.println();
            System.out.println(Ansi.BACKGROUND_RED + Ansi.YELLOW + "***Your OfAKinds***" + Ansi.RESET);
            printKinds(sameNumCount , sameNumber);
        }
    }

    /**
     * TODO
     * This method is called after the cards have been added either
     * to a run or a meld. Its intended behavior is to add "worth"
     * to cards as to be used improve ComputerPlayer decisions by
     * maximizing their hand. For example: if you have a run of 1234
     * and a meld of 333, how should the shared 3 be used to maximize
     * the most point reduction.
     * @param cards the players hand once it has "foundTheRunsAndMelds"
     */
    public void calculateWorth(Card[] cards)
    {
        for (int i = 0; i < cards.length; i++) {

            if (cards[i].isOfAKind()) {
                cards[i].setWorth(cards[i].getCardNumber());
            } else if (cards[i].isARun()) {
                cards[i].setWorth(69);
            } else {
                cards[i].setWorth(0);
            }

        }
    }

    /**
     * Helper method for refreshing the cards status once a new card has been added
     * @param hand the players hand
     */
    private void clearCardStatus(Card[] hand)
    {
        for (Card card : hand) {
            card.clearStatus();
            card.release();
        }
    }


    /**
     * This method is responsible for putting a Player's Hand into
     * arrays organized by suit.
     */
    private void distributeHand()
    {
        // resets the suit arrays to avoid duplicate entries
        heartCount           = 0;
        diamondCount         = 0;
        spadeCount           = 0;
        clubCount            = 0;
        wildCount            = 0;
        sameNumCount         = 0;
        for (int i = 0; i < this.deadwood.cards.length; i += 1)
        {
            if (this.deadwood.cards[i].isWild())
            {
                wildCards[wildCount] = this.deadwood.cards[i];
                wildCount += 1;
            }
            else if (this.deadwood.cards[i].getSuit() == "<3")
            {
                hearts[heartCount] = this.deadwood.cards[i];
                heartCount += 1;
            }
            else if (this.deadwood.cards[i].getSuit() == "<*")
            {
                diamonds[diamondCount] = this.deadwood.cards[i];
                diamondCount += 1;
            }
            else if (this.deadwood.cards[i].getSuit() == "^")
            {
                spades[spadeCount] = this.deadwood.cards[i];
                spadeCount += 1;
            }
            else if (this.deadwood.cards[i].getSuit() == "#")
            {
                clubs[clubCount] = this.deadwood.cards[i];
                clubCount += 1;
            }
        }
        sortHand(hearts , heartCount);
        sortHand(diamonds , diamondCount);
        sortHand(spades , spadeCount);
        sortHand(clubs , clubCount);
    }

    // suitArray is one of the four suits, sorted by distributeHand()
    // count = # of cards with that suit - not sure the algorithm
    // is 100% fool proof but works decently well

    /**
     * This method provides logic for finding the runs within a Player's Hand.
     * It finds the runs and marks each card in the run as "being used" and part
     * of a "run".
     * @param suitArray one of for suit arrays sorted by distributeHand()
     * @param count the number of cards with that suit -- needed because we use
     *              primitive arrays of same length so may contain empty space. Will always be >= 3.
     */
    private void findTheRuns(Card[] suitArray , int count)
    {
        int i = 0;
        boolean isOccupied;

        while (i < count - 1 && i != count - 2)
        {
            // test to see if the number 2 cards ahead is sequential - works because the cards are sorted
            if (suitArray[i] != null) {
                isOccupied = suitArray[i].isBeingUsed() || suitArray[i+1].isBeingUsed() || suitArray[i + 2].isBeingUsed();

                if (suitArray[i].getCardNumber() == (suitArray[i + 2].getCardNumber()) - 2 && !isOccupied)
                {
                    if (!suitArray[i].isBeingUsed()) {
                        suitArray[i].makeItRun();
                        suitArray[i + 1].makeItRun();
                        suitArray[i + 2].makeItRun();

                        suitArray[i].use();
                        suitArray[i + 1].use();
                        suitArray[i + 2].use();
                        i += 2;
                    }
                    // check if the next card fits into the run or not
                    while (i < count - 1 && suitArray[i].getCardNumber() == (suitArray[i + 1].getCardNumber() - 1))
                    {
                        i += 1;
                        suitArray[i].makeItRun();
                        suitArray[i].use();
                    }
                }
            }
            i += 1;
        }
    }

    /**
     * This method provides the logic for finding melds (ofAKinds) in a Player's Hand.
     * Works similarly to findTheRuns(). It checks for 3 of the same number in a row and
     * if it finds that it keeps checking until the number changes. It turns all the cards
     * found in an iteration to "ofAKind" and "in use". Also adds them to a "same number array"
     * similar to the suits array, although this isn't used greatly right now it is designed
     * for enhanced ComputerPlayer decision making.
     * @param cards sorted Player Hand
     * @param count number of cards in the Hand. Same as cards.length - used only for consistency
     */
    private void findTheOfAkinds(Card[] cards , int count)
    {
        Card target;

        sortHand(cards,count);

        for (int i = 0; i < cards.length; i++)
        {
            target = cards[i];
            for (int j = i + 1; j < cards.length; j++)
            {
                // check if the card 3 ahead of target matches target
                // if it does then make all three ofAkind and then check for a fourth
                if (j < cards.length-1 && target.getCardNumber() == cards[j + 1].getCardNumber())
                {
                    boolean isOccupied = cards[i].isBeingUsed() || cards[i+1].isBeingUsed() || cards[i + 2].isBeingUsed();
                    if (!isOccupied)
                    {
                        cards[i].makeItOfAKind();
                        cards[i+1].makeItOfAKind();
                        cards[i+2].makeItOfAKind();
                        cards[i].use();
                        cards[i + 1].use();
                        cards[i + 2].use();

                        // check if there is a "natural" (no wilds) 4 of a kind
                        if (i + 3 < cards.length && cards[i + 3].getCardNumber() == target.getCardNumber()) {
                            cards[i + 3].makeItOfAKind();
                            cards[i + 3].use();
                        }
                    }
                }
            }
        }

        for (int i = 0; i < cards.length; i++)
        {
            if (cards[i].isOfAKind())
            {
                sameNumber[sameNumCount] = cards[i];
                sameNumCount+=1;

            }
        }
    }

    // Todo
    //  distribute the wild cards so that they negate the most points possible
    private void useWilds()
    {

    }

    // Todo
    //  helper function for maximizing the points in a players hand (should this card be used in a run or an ofAkind)
    private void maximizePoints()
    {

    }

    //****************************begin quicksort**************************************

    // organizes cards strictly by number
    public static void sortHand(Card[] hand)
    {
        quickCardSort(hand , 0 , hand.length - 1);
    }

    public static void sortHand(Card[] hand , int right)
    {
        if (right > 0)
            quickCardSort(hand , 0 , right);
    }

    private static void quickCardSort(Card[] array , int leftEnd , int rightEnd)
    {
        int left = leftEnd;
        int right = rightEnd;
        Card pivot = array[(left + right) / 2];

        do
        {
            if (array[left] == null) {
                left += 1;
            }
            if (right > array.length - 1 || array[right] == null) {
                right -= 1;
            }
            while (array[left].getCardNumber() < pivot.getCardNumber()) { left += 1; }
            while (array[right].getCardNumber() > pivot.getCardNumber()) { right -= 1; }

            if (left <= right)
            {
                swap(array , left , right);
                left += 1;
                right -= 1;
            }
        }
        while (left <= right);

        if (leftEnd < right)
            quickCardSort(array, leftEnd, right);
        if (left < rightEnd)
            quickCardSort(array, left, rightEnd);
    }

    private static void swap(Card[] hand , int left , int right)
    {
        Card temp = hand[left];
        hand[left] = hand[right];
        hand[right] = temp;
    }

    //**************************end quicksort methods**********************************



    // **************************** testing methods - will be deleted after hand.java is done ************************
    private void testMethod(int cardCount, Card[] suit) {
        for (int index = 0; index < cardCount; index += 1)
        {
            System.out.println();
            System.out.println(suit[index]);
            System.out.println(suit[index].isARun());
            if (suit[index].isOfAKind() && sameNumCount >= 3)
                System.out.println("yes");
            else
                System.out.println("nah");
        }
    }
    private void printRuns(int cardCount, Card[] suit) {
        for (int index = 0; index < cardCount; index += 1)
        {
            if (suit[index] != null && suit[index].isARun())
            {
                System.out.println(suit[index]);
                System.out.println(suit[index].isARun());
            }
        }
    }
    private void printKinds(int cardCount, Card[] suit)
    {
        for (int index = 0; index < cardCount; index += 1)
        {
            if (suit[index].isOfAKind() && sameNumCount >= 3)
                System.out.println(suit[index]);
            if (suit[index].isOfAKind() && sameNumCount >= 3)
                System.out.println("yes");
        }
    }
//******************************** end testing methods *******************************************************
}




class HandTest
{
    public static void main(String[] args)
    {
        Player test = new Player();
        Player test1 = new Player();
        Player test2 = new Player();


        DeckOfCards deck = new DeckOfCards();
        deck.getDeck();
        deck.shuffleDeck();

        deck.dealDeck(test , test1 , test2);
        System.out.println(test.hand.deadwood.getCount());
        System.out.println("deadwood count: " + test.hand.deadwood.getCount());
        System.out.println("deadwood sorted:");

//        make 3 or 4 of a kind for testing purposes
//        test.hand.deadwood.cards[0] = new Card("<3" , 10);
//        test.hand.deadwood.cards[1] = new Card("<*" , 10);
//        test.hand.deadwood.cards[2] = new Card("^" , 10);
//        test.hand.deadwood.cards[3] = new Card("#" , 10);
        test.getHand();

        System.out.println("Array Length: " + test.hand.deadwood.cards.length);
        System.out.println("card at index 0: " + test.hand.deadwood.cards[0]);
        System.out.println("card at index " + LousReady.getRound() + ": " + test.hand.deadwood.cards[LousReady.getRound()]);
        System.out.println("The number or cards left in the deck is: " + deck.cards.size());
        System.out.println("The number or cards in the discardPile is: " + AssDriver.discardPile.size());

//        test.hand.findRunsAndMelds(test.hand);
        System.out.println("score = " + test.tallyScore());
    }
}
