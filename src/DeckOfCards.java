import java.util.ArrayDeque;
import java.util.Deque;

/*****************************************************
  **                Cory Sebastian                  **
  **               DeckOfCards class                **
  ****************************************************/

public class DeckOfCards extends Card {

     public Deque<Card> deck;

     public DeckOfCards()
     {
         this.deck = new ArrayDeque<Card>();
     }

    public void getDeck()
    {
        this.initializeDeck();
    }

    public void shuffleDeck()
    {
        Card[] cards = new Card[this.deck.size()];
        Card[] shuffledCards = new Card[this.deck.size()];
        Card temp;
        int t;

        System.out.println("SHUFFLING DECK - taking from a stack of size: " + deck.size() );

        for (int i = 0; i < cards.length; i++) {
            cards[i] = this.deck.pop();
        }

        for (int i = 0; i < cards.length; i++)
        {
            t = this.getRandomCard(0 , 51);
            temp = cards[t];
            while (temp.getSuit() == null)
            {
                t = this.getRandomCard(0 , 51);
                temp = cards[t];
            }
            shuffledCards[i] = temp;
            cards[t] = new Card();
        }

        for (Card card : shuffledCards) {
            deck.push(card);
        }
    }

    public void dealDeck(Player playerOne, Player playerTwo, Player user)
    {
        for (int i = 0; i < playerOne.hand.deadwood.cards.length-1; i++)
        {
            playerOne.hand.deadwood.cards[i] = this.deck.pop();
            playerOne.hand.deadwood.setCount(playerOne.hand.deadwood.getCount() + 1);

            playerTwo.hand.deadwood.cards[i] = this.deck.pop();
            playerTwo.hand.deadwood.setCount(playerTwo.hand.deadwood.getCount() + 1);

            user.hand.deadwood.cards[i] = this.deck.pop();
            user.hand.deadwood.setCount(user.hand.deadwood.getCount() + 1);
        }
        playerOne.hand.deadwood.cards[Player.getRound()] = new Card();
        playerTwo.hand.deadwood.cards[Player.getRound()] = new Card();
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

             this.deck.push(new Card(suits[suitIndex] , num));
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
        while (!deck.deck.isEmpty())
        {
            System.out.println(deck.deck.pop());
        }
        System.out.println("*********************");
        deck.getDeck();
        deck.shuffleDeck();
        while (!deck.deck.isEmpty())
        {
            System.out.println(deck.deck.pop());
        }
    }
}     // end main method (used for testing purposes)
