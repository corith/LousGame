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

    /*
     a hand consists of a doubly linked list of HandNodes with the head node being the "main area"
     deadwood is the players hand - anything left in here at the end of a round
     goes against the player (player.score = player.score + deadwood.total)
     deadwood.next points to another HandNode which points to one more hand node.
     the goal of that is to be able to use these other nodes as a "staging area" for cards that fit into either a run or an "of a kind"
    */
    public Hand()
    {
        deadwood = new HandNode(null , new Card[LousReady.getRound() + 1] , null);
        deadwood.setNext(new HandNode(deadwood , new Card[LousReady.getRound()] , new HandNode(deadwood.getNext() , new Card[LousReady.getRound()] , null)));
    }
    
    public boolean cardIsInHand(Card card)
    {
        for (int i = 0; i < this.deadwood.cards.length; i++)
            if (this.deadwood.cards[i].equals(card))
                return true;
        return false;
    }

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

    public void calculateWorth(Card[] cards)
    {
//        hand.findRunsAndMelds();
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

    private void clearCardStatus(Card[] hand)
    {
        for (int i = 0; i < hand.length; i++) {
            Card card = hand[i];
            card.clearStatus();
            card.release();
        }
    }


    // puts the players hand into suit arrays for sorting
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
        } // end for loop for sorting hand into arrays of suit and wilds
        sortHand(hearts , heartCount);
        sortHand(diamonds , diamondCount);
        sortHand(spades , spadeCount);
        sortHand(clubs , clubCount);
    }

    // suitArray is one of the four suits, sorted by distributeHand()
    // count = # of cards with that suit - not sure the algorithm
    // is 100% fool proof but works decently well
    private void findTheRuns(Card[] suitArray , int count)
    {
        int i = 0;
        boolean isOccupied = true;

        while (i < count - 1 && i != count - 2)     // count wil be >= 3
        {
            // test to see if the number 2 cards ahead is sequential
            // works because the cards are sorted
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

    private void findTheOfAkinds(Card[] cards , int count)
    {
        Card[] store = new Card[LousReady.getRound()];
        Card target;
        Card match;
        int tally = 0;
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
    //  helper function for maximizing the points in a players hand (use this card in a run or an ofAkind)
    private void maximizePoints()
    {

    }


    // Todo: figure out if this belongs here or in Player.java or if needed in both then change a name
    private static int tallyScore(Card[] hand , int count)
    {
        int scoreSum = 0;
        for (int i = 0; i < count; i += 1)
        if (hand[i].getCardNumber() > 0 && !hand[i].isOfAKind() && !hand[i].isARun())
        scoreSum = scoreSum + hand[i].getCardNumber();
        return scoreSum;
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



/**************** BEIGN MAIN METHOD FOR TESTS ***********/

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
