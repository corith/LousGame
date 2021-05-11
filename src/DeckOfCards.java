import java.util.ArrayDeque;
import java.util.Deque;

/*****************************************************
  **                Cory Sebastian                  **
  **               DeckOfCards class                **
  ****************************************************/

public class DeckOfCards extends Card {

    public Deque<Card> cards;

    public DeckOfCards()
     {
         this.cards = new ArrayDeque<Card>();
     }

    public void getDeck()
    {
        this.initializeDeck();
    }

    public void shuffleDeck()
    {
        Card[] cards = new Card[this.cards.size()];
        Card[] shuffledCards = new Card[this.cards.size()];
        Card temp;
        int t;

        System.out.println("SHUFFLING DECK - taking from a stack of size: " + this.cards.size() );

        for (int i = 0; i < cards.length; i++) {
            cards[i] = this.cards.pop();
        }

        for (int i = 0; i < cards.length; i++)
        {
            t = this.getRandomCard(0 , cards.length - 1);
            temp = cards[t];
            while (temp.getSuit() == null)
            {
                t = this.getRandomCard(0 , cards.length - 1);
                temp = cards[t];
            }
            shuffledCards[i] = temp;
            cards[t] = new Card();
        }

        for (Card card : shuffledCards) {
            this.cards.push(card);
        }
    }

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

     private void initializeDeck()
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

     private int getRandomCard(int min, int max)
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
