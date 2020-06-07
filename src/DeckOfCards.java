 /****************************************************
  **                Cory Sebastian    done          **
  **            DeckOfCards class                   **
  ***********INCLUDES THE FOLLOWING METHODS***********
  *  - int randomInt(int min , int max)              *
  *  - getDeck() initilizeDeck() shuffleDeck()       *
  *                          && dealDeck()           *
  *  - main(String args)                             *
  ****************************************************/
public class DeckOfCards extends Card {

    public static int randomInt(int min, int max)
    {
        return (int)((Math.random()*((max-min)+1))+min);
    } // end randoInt()

    /***************work together**************************/
    public static Card[] getDeck()
    {
        Card[] deck = new Card[52];
        for (int i = 0; i < deck.length; i++)
        {
            deck[i] = new Card();
        }
        initializeDeck(deck);
        return deck;
    } // end getDeck();

    public static void initializeDeck(Card[] d)
    {
        int num = 1;
        // loads cards 1 - 13 with suit: Hearts (<3)
        for (int i = 0; i <= 12; i++)
        {
            d[i] = new Card("<3" , num);
            num++;
        }
        // loads cards 1 - 13 with suit: Diamonds (<*)
        num = 1;
        for (int i = 13; i <= 25; i++)
        {
            d[i] = new Card("<*" , num);
            num++;
        }
        // loads cards 1 - 13 with suit: Spades (^)
        num = 1;
        for (int i = 26; i <= 38; i++)
        {
            d[i] = new Card("^" , num);
            num++;
        }
        // loads cards 1 - 13 with suit: Clubs (#)
        num = 1;
        for (int i = 39; i <= 51 ; i++)
        {
            d[i] = new Card("#" , num);
            num++;
        }
      } // end initilizeDeck()

    /*************end get deck work together*******************************************/

      // begin shuffleDeck()
    public static Card[] shuffleDeck(Card[] d)
    {
        Card temp = new Card();
        Card[] deckShuffled = new Card[52];
        int t;

        for (int i = 0; i < d.length; i++)
        {
            t = randomInt(0 , 51);
            temp = d[t];
            while (temp.getSuit() == null)
            {
                t = randomInt(0 , 51);
                temp = d[t];
            }
            deckShuffled[i] = temp;
            d[t] = new Card();
        } // end for loop;
        return deckShuffled;
    } // end shuffleDeck()

      // begin dealDeck()
    public static void dealDeck(Card[] d, Player playerOne, Player playerTwo, Player user)
    {
        int nextCard = 0; // variable for pulling the next card from the shuffled deck

        for (int i = 0; i < playerOne.hand.deadwood.cards.length; i++)
        {
            //playerOne.hand[i] = d[nextCard];
            playerOne.hand.deadwood.cards[i] = d[nextCard];
            playerOne.hand.deadwood.count += 1;
            d[nextCard] = new Card();
            nextCard++;

            //playerTwo.hand[i] = d[nextCard];
            playerTwo.hand.deadwood.cards[i] = d[nextCard];
            playerTwo.hand.deadwood.count += 1;
            d[nextCard] = new Card();
            nextCard++;

            //user.hand[i] = d[nextCard];
            user.hand.deadwood.cards[i] = d[nextCard];
            user.hand.deadwood.count += 1;
            d[nextCard] = new Card();
            nextCard++;
        }
        //playerOne.hand[Player.round] = new Card();
        playerOne.hand.deadwood.cards[Player.round] = new Card();
        //playerTwo.hand[Player.round] = new Card();
        playerTwo.hand.deadwood.cards[Player.round] = new Card();
        //user.hand[Player.round] = new Card();
        user.hand.deadwood.cards[Player.round] = new Card();

    } // end dealDeck()


} // end class DeckOfCards













class DeckTest
{
// begin main method for testing purposes
    public static void main(String[] args)
    {
        Player playerOne = new Player();
        Player playerTwo = new Player();
        Player user = new Player();
        Card[] deck = DeckOfCards.getDeck();
        Card[] shuffledDeck = DeckOfCards.shuffleDeck(deck);

        DeckOfCards.dealDeck(shuffledDeck , playerOne , playerTwo , user);
        System.out.println("This is deadwood.cards");
        for (int i = 0; i < user.hand.deadwood.cards.length; i++)
        {
            System.out.println(playerOne.hand.deadwood.cards[i]);
        }
        System.out.println();
        Hand.sortHand(user.hand.deadwood.cards);
        for (int i = 0; i < user.hand.deadwood.cards.length; i++)
        {
            System.out.println(user.hand.deadwood.cards[i]);
            //System.out.println();
            System.out.println(user.hand.deadwood.cards[i]);
        }
    }
}     // end main method (used for testing purposes)
