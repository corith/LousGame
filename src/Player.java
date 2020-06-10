 /***********************************
  **          Cory Sebastian       **
  **    Player Class (constructor) **
  ***********************************/
public class Player
{
    public Hand hand;  // we want this.hand.cards to be main point of dealing
    public boolean dealer;
    public boolean turn;
    public int score;
    public static int round = 9;

    public Player()
    {
        this.hand = new Hand();
        this.dealer = false;
        this.turn = false;
        this.score = 0;
    }

    // sorts the players hand and adds up the points
    public void getHand()
    {
        Hand.sortHand(this.hand.deadwood.cards , this.hand.deadwood.count);
        this.hand.findRunsAndMelds(this.hand);
        System.out.println("\npoints in hand: " + this.tallyScore());
        System.out.println("Users Hand: \n");
        //this.hand.findRunsAndMelds(this.hand);
        for (int i = 0; i < this.hand.deadwood.cards.length; i++)
        {
            if (this.hand.deadwood.cards[i] != null)
                System.out.println(this.hand.deadwood.cards[i]);
        }
    }

    public void pickUpCard(Card[] sDeck , int top) // from top of deck (option 0)
    {
        for (int index = 0; index < this.hand.deadwood.count; index++)    // picks up the card from the top of the deck
        {
            if (this.hand.deadwood.cards[index].getSuit() == null)        // replaces the null slot in player hand
                this.hand.deadwood.cards[index] = sDeck[top + 1];
        }
        top += 1;
    }

    public void pickUpCard(Card[] playPlate)      // from discard pile (option 1)
    {
        for (int index = 0; index < this.hand.deadwood.cards.length; index++)
        {                                                                // swaps the empty spot in players hands
            if (this.hand.deadwood.cards[index].getSuit() == null)       //  with the card in playplate[]
            {
                this.hand.deadwood.cards[index] = playPlate[0];
            }
        }
    }

    public void putDownDiscard(Card discardCard)
    {
        for (int z = 0; z < this.hand.deadwood.cards.length; z++)
        {
            if (this.hand.deadwood.cards[z].equals(discardCard))
            {
                this.hand.deadwood.cards[z] = new Card();
            }
        }
        //return userHand;
    } // end doTheDiscard

    // Todo refactor this so that the check of the option happens and then
    //  the user either picks up from sDeck and topCard or from playplate then execute rest of code
    public void userTakeTurn(int theOption , Card[] sDeck , Card[] playPlate , int topCard)
    {
        Card discardCard;

        if (theOption == 0) {
            this.pickUpCard(sDeck, topCard);                         // moves ahead one card in the deck (away from the card that was just picked up from sDeck[i + 1])
        }
        else if (theOption == 1) {
            this.pickUpCard(playPlate);
        }
        this.getHand();
        System.out.println("Now, which card would you like to discard?");
        System.out.print("Please enter the number of the card first: ");
        discardCard = PlayWizard.getDiscardCard();
        while (!this.hand.cardIsInHand(discardCard))
        {
            System.out.println("**Please enter a card that is in your hand**");
            System.out.print("enter the number: ");
            discardCard = PlayWizard.getDiscardCard();
        }
        this.putDownDiscard(discardCard);                         // puts the card the player wishes to discard in playplate
        playPlate[0] = discardCard;                               // end process of physical discard and swap to playplate
    }

    // Todo: figure out if this belongs here or in Hand.java
    public int tallyScore()
    {
        int scoreSum = 0;
        for (int i = 0; i < this.hand.deadwood.count; i += 1)
        {
            if (this.hand.deadwood.cards[i].number > 0 && !this.hand.deadwood.cards[i].isOfAKind() && !this.hand.deadwood.cards[i].isARun())
            {
                scoreSum = scoreSum + this.hand.deadwood.cards[i].number;
            }
        }
        return scoreSum;
    }
} // end class player





class PlayerTest
{
/**************** BEIGN MAIN METHOD FOR TESTS ***********/
  public static void main(String[] args)
  {
    Player one = new Player();
    Player two = new Player();
    Player three = new Player();
    int round = LousReady.round;

    System.out.println(LousReady.round);
    Card[] deck = DeckOfCards.getDeck();
    DeckOfCards.initializeDeck(deck);
    Card[] shuffledDeck = DeckOfCards.shuffleDeck(deck);

    DeckOfCards.dealDeck(shuffledDeck, one , two , three);

    one.getHand();
    two.getHand();
    three.getHand();

    //one.sortPlayerHand();
    System.out.println("this should be sorted");
    one.getHand();
    //two.sortPlayerHand();
    two.getHand();

  }
}
