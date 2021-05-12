
import java.util.Scanner;
import java.util.Stack;

/**
 * An "assistant" driver to LousReady.java.
 * The primary method of this class is PlayLoop()
 * which is called once each round from LousReady.
 * This class and PlayWizard could be combined into
 * one file, but I felt like keeping this file as plain
 * as possible...
 */
public class AssDriver extends LousReady {

    public static Stack<Card> discardPile = new Stack<Card>();

    /**
     * This is the primary method of this class.
     * It is responsible for handling gameplay
     * until a player has won that round.
     * @param sDeck The Deck for that round
     * @param playerOne Player One
     * @param playerTwo Player Two
     * @param user Player 3 - if a real player is playing then it is the user.
     * @return returns true when there is a winner.
     */
    public static boolean playLoop(DeckOfCards sDeck, ComputerPlayer playerOne, ComputerPlayer playerTwo, ComputerPlayer user) {
        int theOption;

        while (true) {

            if (discardPile.isEmpty()) {
                discardPile.push(sDeck.cards.pop());
            }

            System.out.println("The number of cards left in the deck is: " + sDeck.cards.size());
            System.out.println("The number of cards in the discardPile is: " + discardPile.size());

//          user.getHand();
            System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + discardPile.peek());

//          theOption = gameOptions();
//          user.userTakeTurn(theOption,sDeck,discardPile);
            user.computerTakeTurn(sDeck, discardPile);
            printCurrentStateDetails(3, sDeck);

            playerOne.computerTakeTurn(sDeck, discardPile);
            printCurrentStateDetails(1, sDeck);

            playerTwo.computerTakeTurn(sDeck, discardPile);
            printCurrentStateDetails(2, sDeck);

            if (PlayWizard.checkForWinner(user, playerOne, playerTwo)) {
                return true;
            }
        }
    }

    /**
     * Prints the the current state (number of cards in each)
     * for the Deck and discardPile. Also states when a
     * person's turn has officially ended.
     * @param turn  the playerNumber
     * @param sDeck the deck for that round
     */
    private static void printCurrentStateDetails(int turn, DeckOfCards sDeck) {
        if (turn == 2) {
            System.out.println("**********************************end user (" + turn + ") turn****************************************");
            return;
        }
        System.out.println("**********************************end user (" + turn + ") turn****************************************");
        System.out.println("The " + Ansi.CYAN + "discard " + Ansi.RESET + "pile contains: " + discardPile.peek());
        System.out.println("The number of cards left in the deck is: " + sDeck.cards.size());
        System.out.println("The number of cards in the discardPile is: " + discardPile.size());
    }

    /**
     * Presents real life user with the option to either
     * draw a card from top of deck or take a card from
     * the discard pile. This method is not called if
     * all players are computer.
     * @return integer either 0 or 1. 0 = top of deck & 1 = discardPile
     */
    public static int gameOptions() {
        Scanner lou;
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
}