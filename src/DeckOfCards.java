import java.util.ArrayDeque;
import java.util.Deque;


/**
 * This class represents a standard deck of 52 Cards. Currently this does not include jokers but it will.
 */
public class DeckOfCards extends Card {

    public Deque<Card> cards;

    public DeckOfCards()
     {
         this.cards = new ArrayDeque<Card>();
     }

    /**
     * This method initializes a deck. When this method is called
     * it fills the current deck instance with 52 cards which are not
     * shuffled. After calling this method you should have a deck
     * that is equivalent to opening a new deck from a store.
     */
    @Deprecated
    public void getDeck()
    {
        this.initializeDeck();
    }

    /**
     * This method provides functionality for shuffling a deck.
     * It pops all the cards from a freshly initialized deck into
     * an array and then "randomly" selects cards from that array
     * and puts them into another array making that array "shuffled".
     * Then finally pops the cards from the shuffled array back into the deck.
     * It checks to make sure it did not index into a spot in the array that has already
     * been selected.
     */
    public void shuffleDeck()
    {
        Card[] unShuffledCards = new Card[this.cards.size()];
        Card[] shuffledCards = new Card[this.cards.size()];
        Card temp;
        int t;

        System.out.println("SHUFFLING DECK - taking from a stack of size: " + this.cards.size() );

        for (int i = 0; i < unShuffledCards.length; i++) {
            unShuffledCards[i] = this.cards.pop();
        }

        for (int i = 0; i < unShuffledCards.length; i++)
        {
            t = this.getRandomCard(0 , unShuffledCards.length - 1);
            temp = unShuffledCards[t];
            while (temp.getSuit() == null)
            {
                t = this.getRandomCard(0 , unShuffledCards.length - 1);
                temp = unShuffledCards[t];
            }
            shuffledCards[i] = temp;
            unShuffledCards[t] = new Card();
        }

        for (Card card : shuffledCards) {
            this.cards.push(card);
        }
    }

    /**
     * This method is responsible for dealing a deck presumably shuffled,
     * out to all the Players. After dealing the right amount of cards it
     * sets an empty card in each player's hand for space to pick up cards.
     * @param playerOne Player one
     * @param playerTwo Player two
     * @param user Player three (if real user then it's the user)
     */
    public void dealDeck(Player playerOne, Player playerTwo, Player user)
    {
        for (int i = 0; i < playerOne.hand.deadwood.cards.length - 1; i++)
        {
            playerOne.hand.deadwood.cards[i] = this.cards.pop();
            playerOne.hand.deadwood.setCount(playerOne.hand.deadwood.getCount() + 1);

            playerTwo.hand.deadwood.cards[i] = this.cards.pop();
            playerTwo.hand.deadwood.setCount(playerTwo.hand.deadwood.getCount() + 1);

            user.hand.deadwood.cards[i] = this.cards.pop();
            user.hand.deadwood.setCount(user.hand.deadwood.getCount() + 1);
        }
        playerOne.hand.deadwood.cards[LousReady.getRound()] = new Card();
        playerTwo.hand.deadwood.cards[LousReady.getRound()] = new Card();
        user.hand.deadwood.cards[LousReady.getRound()] = new Card();

    }

    /**
     * This method is responsible for filling the deck with 52 cards unshuffled.
     * After calling this method on a newly created Deck object the deck will
     * look the same as a new deck just opened from the store. Currently does not
     * load jokers, but it will eventually.
     */
     public void initializeDeck()
     {
         String[] suits = {"<3", "<*" , "^" , "#"};
         int num = 1;
         int suitIndex = 0;

         for (int i = 0; i < 52; i++)
         {
             if (suitIndex == 4)
                 suitIndex-=1;

             this.cards.push(new Card(suits[suitIndex] , num));
             num++;

             if (num == 14)
             {
                 num = 1;
                 suitIndex+=1;
             }
         }
     }

    /**
     * Helper function for selecting random cards from the non shuffled array inside shuffleDeck()
     * @param min min number - will be 0 for first card in the non shuffled array
     * @param max max number - will be 52
     * @return random int between 0 and 51 - the index into the non shuffled array.
     */
     private int getRandomCard(int min, int max)
     {
         return (int)((Math.random()*((max-min)+1))+min);
     }

 }




class DeckTest
{
    public static void main(String[] args)
    {
        Player playerOne = new Player();
        Player playerTwo = new Player();
        Player user = new Player();
        DeckOfCards deck = new DeckOfCards();
        deck.getDeck();

        //DeckOfCards.dealDeck(shuffledDeck , playerOne , playerTwo , user);
        System.out.println("This is the deck of cards");
//        for (int i = 0; i < deck.deck.size(); i++)
        while (!deck.cards.isEmpty())
        {
            System.out.println(deck.cards.pop());
        }
        System.out.println("*********************");
        deck.getDeck();
        deck.shuffleDeck();
        while (!deck.cards.isEmpty())
        {
            System.out.println(deck.cards.pop());
        }
    }
}     // end main method (used for testing purposes)
