// An assistant driver to LousReady.java

import java.util.Scanner;

public class AssDriver extends LousReady {
  public static PlayWizard wizard = new PlayWizard();
  public static Card[] playPlate = new Card[2];

  public static int randomInt(int min, int max) {
      return (int)((Math.random()*((max-min)+1))+min);
  }

  public static int gameOptions() {
    Scanner lou = new Scanner(System.in);
    int decision = 2;

    System.out.println("Would you like to draw a card? (0)\n" +
            "Or take the one from the " + Ansi.CYAN + "discard" + Ansi.RESET + " pile? (1)");

    while (true) {
      lou = new Scanner(System.in);

      if (lou.hasNextInt())
        decision = lou.nextInt();
      if (decision == 0 || decision == 1)
        return decision;

      System.out.println("Please enter either a 1 or a 0!");
    }
  }

  public static void playLoop(DeckOfCards sDeck, Player playerOne, Player playerTwo, Player user) {
    boolean running    = true;
    int topCard        = 0; // represents the top of the deck i think
    int theOption;

    System.out.println(user.hand.deadwood.getCount());

    while (running)
    {
      // this while loop moves the index passed shuffled/dealt cards
      while (sDeck.deck[topCard].getSuit() == null)
      {
        topCard += 1;
        // check for end of deck
        // Todo: something more game like (reshuffle playplate)
        if (topCard == 51)
        {
          System.out.println("******************************OUT OF FUCKING CARDS ALERT ALERT***********************");
          sDeck.getDeck();
          sDeck = sDeck.shuffleDeck();;
          topCard = 0;
        }
        if (sDeck.deck[topCard].getSuit() != null && playPlate[0] == null)
          playPlate[0] = sDeck.deck[topCard];
      }

      sDeck.deck[topCard] = new Card();
      user.getHand();
      System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + playPlate[0]);
      theOption = gameOptions();
      System.out.println("sDeck.deck+1: " + sDeck.deck[topCard+1] + "\n" + "topcard: " + topCard);
      user.userTakeTurn(theOption , sDeck.deck , playPlate , topCard);

      // next turn()
      // next turn()
      // if (user.hand.isAWinner())
      if(user.tallyScore() == 0)
      {
        running = false;
        user.getHand();
        System.out.println("YOU HAVE WON");
      }
    }
  }
} // end class













//**************************begin computer turn **************************************

  // computerTurn(PlayerOne)
  // computerTurn(PlayerTwo)


    // check playerHands: if True for one of the players
    // return something to do with class Score.
    // else continue playLoop loop!
    // return a score of some sort.
    // or update each player score variable??
