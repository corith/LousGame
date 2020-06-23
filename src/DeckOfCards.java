 /****************************************************
  **                Cory Sebastian                  **
  **               DeckOfCards class                **
  ****************************************************/

public class DeckOfCards extends Card {

     public Card[] deck;
     private final int numOfDecks;

     public DeckOfCards(int numberOfDecks)
     {
         this.deck = new Card[52 * numberOfDecks];
         this.numOfDecks = numberOfDecks;
     }

    public void getDeck()
    {
        this.deck = new Card[52 * this.numOfDecks];
        this.initializeDeck();
    }

     // returns a new deck that has been scrambled
     // will make every card in d null in the process
    public DeckOfCards shuffleDeck()
    {
        DeckOfCards deckShuffled = new DeckOfCards(this.numOfDecks);
        Card temp = new Card();
        int t;

        for (int i = 0; i < this.deck.length; i++)
        {
            t = randomInt(0 , 51);
            temp = this.deck[t];
            while (temp.getSuit() == null)
            {
                t = randomInt(0 , 51);
                temp = this.deck[t];
            }
            deckShuffled.deck[i] = temp;
            this.deck[t] = new Card();
        }
        return deckShuffled;
    }

    public void dealDeck(Player playerOne, Player playerTwo, Player user)
    {
        int nextCard = 0; // variable for pulling the next card from the shuffled deck

        for (int i = 0; i < playerOne.hand.deadwood.cards.length; i++)
        {
            //playerOne.hand[i] = this.deck[nextCard];
            playerOne.hand.deadwood.cards[i] = this.deck[nextCard];
            playerOne.hand.deadwood.setCount(playerOne.hand.deadwood.getCount() + 1);
            this.deck[nextCard] = new Card();
            nextCard++;

            //playerTwo.hand[i] = this.deck.deck[nextCard];
            playerTwo.hand.deadwood.cards[i] = this.deck[nextCard];
            playerTwo.hand.deadwood.setCount(playerTwo.hand.deadwood.getCount() + 1);
            this.deck[nextCard] = new Card();
            nextCard++;

            //user.hand[i] = this.deck.deck[nextCard];
            user.hand.deadwood.cards[i] = this.deck[nextCard];
            user.hand.deadwood.setCount(user.hand.deadwood.getCount() + 1);
            this.deck[nextCard] = new Card();
            nextCard++;
        }
        //playerOne.hand[Player.getRound(] = new Card();
        playerOne.hand.deadwood.cards[Player.getRound()] = new Card();
        //playerTwo.hand[Player.getRound(] = new Card();
        playerTwo.hand.deadwood.cards[Player.getRound()] = new Card();
        //user.hand[Player.getRound(] = new Card();
        user.hand.deadwood.cards[Player.getRound()] = new Card();

    }

     private void initializeDeck()
     {
         String[] suits = {"<3", "<*" , "^" , "#"};
         int num = 1;
         int suitIndex = 0;

         for (int i = 0; i < 52; i++)
         {
             if (suitIndex == 4)
                 suitIndex-=1;

             this.deck[i] = new Card(suits[suitIndex] , num);
             num++;

             if (num == 14)
             {
                 num = 1;
                 suitIndex+=1;
             }
         }
     }

     private static int randomInt(int min, int max)
     {
         return (int)((Math.random()*((max-min)+1))+min);
     }

 } // end class DeckOfCards




class DeckTest
{
// begin main method for testing purposes
    public static void main(String[] args)
    {
        Player playerOne = new Player();
        Player playerTwo = new Player();
        Player user = new Player();
        DeckOfCards deck = new DeckOfCards(1);
        deck.getDeck();

        //DeckOfCards.dealDeck(shuffledDeck , playerOne , playerTwo , user);
        System.out.println("This is deadwood.cards");
        for (int i = 0; i < deck.deck.length; i++)
        {
            System.out.println(deck.deck[i]);
        }
        System.out.println("*********************");
        deck = deck.shuffleDeck();
//        Hand.sortHand(user.hand.deadwood.cards);
        for (int i = 0; i < deck.deck.length; i++)
        {
            System.out.println(deck.deck[i]);
        }
    }
}     // end main method (used for testing purposes)
