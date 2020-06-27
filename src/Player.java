 /***********************************
  **          Cory Sebastian       **
  **           Player Class        **
  ***********************************/
public class Player
{
    private static int round = 9;
    private int score;
    private boolean dealer;
    private boolean turn;
    private String playerName;

    public Hand hand;  // we want this.hand.cards to be main point of dealing

    // getters
    public static int getRound() { return round; }
    public int getScore() { return score; }
    public boolean isDealer() { return dealer; }
    public boolean isTurn() { return turn; }

    //setters
    public void setRound(int round) { Player.round = round; }
    public void setScore(int score) { this.score = score; }
    public void setDealer(boolean dealer) { this.dealer = dealer; }
    public void setTurn(boolean turn) { this.turn = turn; }

    public Player()
    {
        this.hand = new Hand();
        this.dealer = false;
        this.turn = false;
        this.score = 0;
        this.playerName = "User";
    }

    public Player(String name)
    {
        this.hand = new Hand();
        this.dealer = false;
        this.turn = false;
        this.score = 0;
        this.playerName = name;
    }

    public boolean isAWinner() {
        return this.tallyScore() == 0;
    }

    // sorts the players hand and adds up the points
    public void getHand()
    {
        Hand.sortHand(this.hand.deadwood.cards , this.hand.deadwood.getCount());
        this.hand.findRunsAndMelds(true);
        System.out.println(this.playerName + "'s Hand");
        System.out.println("points in hand: " + this.tallyScore() + "\n");

        for (int i = 0; i < this.hand.deadwood.cards.length; i++)
            if (this.hand.deadwood.cards[i] != null)
                System.out.println(this.hand.deadwood.cards[i]);
    }

    public void pickUpCard(Card[] sDeck , int top) // from top of deck (option 0)
    {
        for (int index = 0; index < this.hand.deadwood.getCount(); index++)    // picks up the card from the top of the deck
            if (this.hand.deadwood.cards[index].getSuit() == null)        // replaces the null slot in player hand
                this.hand.deadwood.cards[index] = sDeck[top + 1];
    }

    public void pickUpCard(Card[] playPlate)      // from discard pile (option 1)
    {
        // swaps the empty spot in players hands
        for (int index = 0; index < this.hand.deadwood.cards.length; index++)
            if (this.hand.deadwood.cards[index].getSuit() == null)       //  with the card in playplate[]
                this.hand.deadwood.cards[index] = playPlate[0];
    }

    public void putDownDiscard(Card discardCard)
    {
        for (int z = 0; z < this.hand.deadwood.cards.length; z++)
            if (this.hand.deadwood.cards[z].equals(discardCard))
                this.hand.deadwood.cards[z] = new Card();
    }

    // takes a turn - picking up a card from one of two piles
    // putting down a discard
    public void userTakeTurn(int theOption , DeckOfCards sDeck , Card[] playPlate , int topCard)
    {
        Card discardCard;

        if (theOption == 0)
            this.pickUpCard(sDeck.deck, topCard);                         // moves ahead one card in the deck (away from the card that was just picked up from sDeck[i + 1])
        else if (theOption == 1)
            this.pickUpCard(playPlate);

        this.getHand();
        System.out.println("Now, which card would you like to discard?");
        System.out.print("Please enter the " + Ansi.CYAN + "number" + Ansi.RESET + " of the card first: ");
        discardCard = PlayWizard.getDiscardCard();
        while (!this.hand.cardIsInHand(discardCard))
        {
            System.out.println("**Please enter a card that is in your hand**");
            System.out.print("Enter the number: ");
            discardCard = PlayWizard.getDiscardCard();
        }
        this.putDownDiscard(discardCard);                         // puts the card the player wishes to discard in playplate
        playPlate[0] = discardCard;                               // end process of physical discard and swap to playplate
    }

    public int tallyScore()
    {
        int scoreSum = 0;
        for (int i = 0; i < this.hand.deadwood.getCount(); i += 1)
            if (this.hand.deadwood.cards[i].getCardNumber() > 0 && !this.hand.deadwood.cards[i].isOfAKind() && !this.hand.deadwood.cards[i].isARun())
                scoreSum = scoreSum + this.hand.deadwood.cards[i].getCardNumber();
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
    DeckOfCards deck = new DeckOfCards(1);
    deck.getDeck();
    deck = deck.shuffleDeck();


    deck.dealDeck(one , two , three);

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
