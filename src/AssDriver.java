// An assistant driver to LousReady.java

import java.util.Scanner;
import java.util.Stack;

public class AssDriver extends LousReady {

  public static Stack<Card> discardPile = new Stack<Card>();

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

  public static int playLoop(DeckOfCards sDeck, ComputerPlayer playerOne, ComputerPlayer playerTwo, ComputerPlayer user) {
//  public static void playLoop(DeckOfCards sDeck, Player playerOne, Player playerTwo, Player user) {
    boolean running    = true;
    int theOption;

    while (running)
    {
      // check for end of deck
      if (sDeck.deck.size() < 1)
      {
          System.out.println("******************************OUT OF CARDS ALERT (called from beginning of playloop ***********************");
          System.out.println("Reloading deck deque with "+discardPile.size()+" cards");
          while (!discardPile.empty())
          {
              sDeck.deck.push(discardPile.pop());
          }
      }

      if (discardPile.isEmpty()) {
        discardPile.push(sDeck.deck.pop());
      }

      System.out.println("The number or cards left in the deck is: " + sDeck.deck.size());
      System.out.println("The number or cards in the discardPile is: " + discardPile.size());

//      user.getHand();
      System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + discardPile.peek());

//      theOption = gameOptions();
//      user.userTakeTurn(theOption,sDeck,discardPile);
      user.computerTakeTurn(sDeck,discardPile);
      System.out.println("**********************************end user (3) turn****************************************");
      System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + discardPile.peek());
      System.out.println("The number or cards left in the deck is: " + sDeck.deck.size());
      System.out.println("The number or cards in the discardPile is: " + discardPile.size());

      playerOne.computerTakeTurn(sDeck, discardPile);
      System.out.println("**********************************end Comp 1 turn****************************************");
      System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + discardPile.peek());
      System.out.println("The number or cards left in the deck is: " + sDeck.deck.size());
      System.out.println("The number or cards in the discardPile is: " + discardPile.size());

      playerTwo.computerTakeTurn(sDeck, discardPile);
      System.out.println("**********************************end Comp 2 turn****************************************");
      if (PlayWizard.checkForWinner(user, playerOne, playerTwo) == 1) {
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
