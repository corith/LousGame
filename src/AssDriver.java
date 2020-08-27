// An assistant driver to LousReady.java

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class AssDriver extends LousReady {
  public static PlayWizard wizard = new PlayWizard();
  public static Card[] playPlate = new Card[2];
  public static Deque<Card> discardPile = new ArrayDeque<Card>();

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
  public static int playLoop(DeckOfCards sDeck, ComputerPlayer playerOne, ComputerPlayer playerTwo, Player user) {
//  public static void playLoop(DeckOfCards sDeck, Player playerOne, Player playerTwo, Player user) {
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
            System.out.println("******************************OUT OF CARDS ALERT***********************");
            return 0;
          }
          if (sDeck.deck[topCard].getSuit() != null && discardPile.isEmpty()) {
            discardPile.push(sDeck.deck[topCard]);
  //          playPlate[0] = sDeck.deck[topCard];
          }
      }

      sDeck.deck[topCard] = new Card();
      user.getHand();
      System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + discardPile.peek());
      theOption = gameOptions();
      user.userTakeTurn(theOption,sDeck,discardPile.pop(), topCard);
//      user.computerTakeTurn(sDeck, playPlate , topCard);
      System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + discardPile.peek());
      playerOne.computerTakeTurn(sDeck, discardPile.pop(), topCard);
        System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + discardPile.peek());
      playerTwo.computerTakeTurn(sDeck, discardPile.pop(), topCard);
//      System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + playPlate[0]);

      if(user.isAWinner())
      {
        running = false;
        user.getHand();
        System.out.println("YOU HAVE WON");
        return 1;
      }
      else if(playerOne.isAWinner())
      {
        running = false;
        playerOne.getHand();
        System.out.println("Computer One has WON yalll ...... amazing");
        return 1;
      }
      else if(playerTwo.isAWinner())
      {
        running = false;
        playerTwo.getHand();
        System.out.println("Computer Two has WON yalll ...... amazing");
        return 1;
      }
    }
    return 0;
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
