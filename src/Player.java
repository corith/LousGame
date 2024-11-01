import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * This class represents a real human Player
 * and is extended by ComputerPlayer.
 */
public class Player
{
    private int score;
    private boolean dealer;
    private boolean turn;
    private String playerName;

    public Hand hand;

    // getters
    public int getScore() { return score; }
    public boolean isDealer() { return dealer; }
    public boolean isTurn() { return turn; }
    public String getPlayerName() { return  playerName; }

    //setters
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

    /**
     * Checks the player's hand to see if it is a winner.
     * The expected behavior is if the player's hand score
     * equals 0 then all the cards belong to a set.
     * (total score is < 0)
     * @return true if the hand is a winner.
     */
    public boolean isAWinner() {
        this.hand.findRunsAndMelds(false);
        return this.tallyScore() == 0;
    }

    /**
     * Sorts the players hand. Adds up the points.
     * And prints the hand.
     */
    public void getHand()
    {
        System.out.println(Ansi.BACKGROUND_GREEN + Ansi.CYAN + this.playerName + "'s Hand" + Ansi.RESET);
        Hand.sortHand(this.hand.deadwood.cards , this.hand.deadwood.getCount());
        this.hand.findRunsAndMelds(true);
        System.out.println("points in hand: " + this.tallyScore() + "\n");

        for (int i = 0; i < this.hand.deadwood.cards.length; i++) {
            if (this.hand.deadwood.cards[i] != null) {
                System.out.println(this.hand.deadwood.cards[i]);
            }
        }
    }

    /**
     * Same as getHand() but overloaded to allow
     * for different behavior inside findRunsAndMelds.
     * Only used for experimenting and debugging.
     * @param on - used only to overload getHand()
     */
    public void getHand(int on)
    {
        System.out.println(Ansi.BACKGROUND_GREEN + Ansi.CYAN + this.playerName + "'s Hand (After turn)" + Ansi.RESET);
        Hand.sortHand(this.hand.deadwood.cards , this.hand.deadwood.getCount());
        this.hand.findRunsAndMelds(false);
        System.out.println("points in hand: " + this.tallyScore() + "\n");

        for (int i = 0; i < this.hand.deadwood.cards.length; i++)
            if (this.hand.deadwood.cards[i] != null)
                System.out.println(this.hand.deadwood.cards[i]);
    }

    /**
     * This method is responsible for picking up a
     * card from the top of the Deck.
     * @param sDeck the current deck for the round
     */
    public void pickUpCard(DeckOfCards sDeck) // from top of deck (option 0)
    {
        System.out.println("Top card was: " + sDeck.cards.peek());

        // picks up the card from the top of the deck
        for (int index = 0; index < this.hand.deadwood.cards.length; index++) {
            if (this.hand.deadwood.cards[index].getSuit() == null) {
                try {
                    this.hand.deadwood.cards[index] = sDeck.cards.pop();
                } catch (NoSuchElementException e) {
                    LousReady.logger.info(e.toString());
                    if (sDeck.cards.size() == 0) {
                        LousReady.logger.info("deck has ran out of cards....");
                        System.out.println("******************************OUT OF CARDS ALERT***********************");
                        System.out.println("Reloading deck deque with "+AssDriver.discardPile.size()+" cards");
                        while(!AssDriver.discardPile.empty()) {
                            sDeck.cards.push(AssDriver.discardPile.pop());
                        };
                        System.out.println("The new top card was: " + sDeck.cards.peek());
                        this.hand.deadwood.cards[index] = sDeck.cards.pop();
                    }
                }
            }

        }
    }

    /**
     * This method is responsible for picking up a
     * card from the top of the discardPile
     * @param playPlate the current discardPile for the round
     */
    public void pickUpCard(Stack<Card> playPlate)      // from discard pile (option 1)
    {
        // swaps the empty spot in players hands
        for (int index = 0; index < this.hand.deadwood.cards.length; index++) {
            if (this.hand.deadwood.cards[index].getSuit() == null)       //  with the card in playplate[]
            {
                try {
                    this.hand.deadwood.cards[index] = playPlate.pop();
                } catch (EmptyStackException e) {
                    System.out.println("playPlate is empty");
                    LousReady.logger.info("Caught EmptyStackException trying to pop from discardPile");
                    LousReady.logger.info(e.getMessage());
                }

            }
        }
    }

    /**
     * This method is responsible for discarding
     * the unwanted card to the top of the discard
     * pile.
     * @param discardCard the card the user wishes to discard.
     *                    should be passed by PlayWizard.getDiscardCard().
     */
    public void putDownDiscard(Card discardCard)
    {
        for (int z = 0; z < this.hand.deadwood.cards.length; z++) {
            if (this.hand.deadwood.cards[z].equals(discardCard)) {
                AssDriver.discardPile.push(this.hand.deadwood.cards[z]);
                this.hand.deadwood.cards[z] = new Card();
                break;
            }
        }
    }

    /**
     * This method puts several other methods together
     * inorder for a real human user to take a turn.
     * @param theOption int either 1 or 0 depending on if the user
     *                  wants to draw from Deck or discardPile.
     *                  This variable comes from AssDriver.gameOptions().
     * @param sDeck The Deck for the current round
     * @param playPlate the discardPile declared in  AssDriver.
     */
    public void userTakeTurn(int theOption, DeckOfCards sDeck, Stack<Card> playPlate)
    {
        Card discardCard;

        if (theOption == 0)
            this.pickUpCard(sDeck);                         // moves ahead one card in the deck (away from the card that was just picked up from sDeck[i + 1])
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
        this.putDownDiscard(discardCard);
    }

    /**
     * This method provides the logic for counting
     * points in a Player's hand. It takes into account
     * runs (666..) and melds (678...).
     * @return the score as an integer
     */
    public int tallyScore()
    {
        int wildCount;
        int scoreSum = 0;
        wildCount = (int) Arrays.stream(this.hand.deadwood.cards).filter(e -> e.getCardNumber() == LousReady.getRound()).count();
        for (int i = 0; i <= this.hand.deadwood.getCount(); i += 1) {

            boolean shouldCount = this.hand.deadwood.cards[i].getCardNumber() > 0 && !this.hand.deadwood.cards[i].isOfAKind() && !this.hand.deadwood.cards[i].isARun();
//            shouldCount = shouldCount ||
            if (shouldCount) {
                scoreSum = scoreSum + this.hand.deadwood.cards[i].getCardNumber();
            }

            if (wildCount > 2) {
                scoreSum = 0;
            }
        }
        return scoreSum;
    }
}


class PlayerTest
{
  public static void main(String[] args)
  {
    Player one = new Player();
    Player two = new Player();
    ComputerPlayer three = new ComputerPlayer(3);
    int round = LousReady.getRound();

    System.out.println(LousReady.getRound());
    DeckOfCards deck = new DeckOfCards();
    deck.getDeck();
    deck.shuffleDeck();


    deck.dealDeck(one , two , three);

    one.getHand();
    two.getHand();
    three.getHand();

    //one.sortPlayerHand();
    System.out.println("this should be sorted");
    one.getHand(1);
    //two.sortPlayerHand();
    two.getHand(1);
    three.getHand(1);

  }
}
