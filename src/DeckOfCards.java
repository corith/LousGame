 /****************************************************
  **                Cory Sebastian                  **
  **               DeckOfCards class                **
  ****************************************************/

public class DeckOfCards extends Card {

    public static int randomInt(int min, int max)
    {
        return (int)((Math.random()*((max-min)+1))+min);
    } // end randoInt()

    public static Card[] getDeck()
    {
        Card[] deck = new Card[52];
        for (int i = 0; i < deck.length; i++)
            deck[i] = new Card();
        initializeDeck(deck);
        return deck;
    } // end getDeck();

    public static void initializeDeck(Card[] d)
    {
        String[] suits = {"<3", "<*" , "^" , "#"};
        int num = 1;
        int suitIndex = 0;

        for (int i = 0; i < 52; i++)
        {
            if (suitIndex == 4)
                suitIndex-=1;

            d[i] = new Card(suits[suitIndex] , num);
            num++;

            if (num == 14)
            {
                num = 1;
                suitIndex+=1;
            }
        }
      } // end initializeDeck()

     // returns a new deck that has been scrambled
     // will make every card in d null in the process
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
        }
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
            playerOne.hand.deadwood.setCount(playerOne.hand.deadwood.getCount() + 1);
            d[nextCard] = new Card();
            nextCard++;

            //playerTwo.hand[i] = d[nextCard];
            playerTwo.hand.deadwood.cards[i] = d[nextCard];
            playerTwo.hand.deadwood.setCount(playerTwo.hand.deadwood.getCount() + 1);
            d[nextCard] = new Card();
            nextCard++;

            //user.hand[i] = d[nextCard];
            user.hand.deadwood.cards[i] = d[nextCard];
            user.hand.deadwood.setCount(user.hand.deadwood.getCount() + 1);
            d[nextCard] = new Card();
            nextCard++;
        }
        //playerOne.hand[Player.getRound(] = new Card();
        playerOne.hand.deadwood.cards[Player.getRound()] = new Card();
        //playerTwo.hand[Player.getRound(] = new Card();
        playerTwo.hand.deadwood.cards[Player.getRound()] = new Card();
        //user.hand[Player.getRound(] = new Card();
        user.hand.deadwood.cards[Player.getRound()] = new Card();

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
        Card[] shuffledDeck = DeckOfCards.getDeck();
        shuffledDeck = DeckOfCards.shuffleDeck(shuffledDeck);

        //DeckOfCards.dealDeck(shuffledDeck , playerOne , playerTwo , user);
        System.out.println("This is deadwood.cards");
        for (int i = 0; i < deck.length; i++)
        {
            System.out.println(deck[i]);
        }
        System.out.println();
//        Hand.sortHand(user.hand.deadwood.cards);
        for (int i = 0; i < shuffledDeck.length; i++)
        {
            System.out.println(shuffledDeck[i]);
        }
    }
}     // end main method (used for testing purposes)
