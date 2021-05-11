import java.util.Scanner;

/**
 * This class is a utility class. When making it
 * I viewed it as almost a referee / game host /
 * game manager type of "being". A place for methods
 * that could probably be located somewhere else,
 * but that I didn't want cluttering up other files.
 */
class PlayWizard extends AssDriver
{
    // calculate all the players total score
    public static void grossScore()
    {
        playerOne.setScore(playerOne.getScore() + playerOne.tallyScore());
        playerTwo.setScore(playerTwo.getScore() + playerTwo.tallyScore());
        user.setScore(user.getScore() + user.tallyScore());
    }

    public static Card getDiscardCard()
    {
        Scanner lou;
        int discardNumber = -1;
        Card discardCard = new Card();

        while (discardNumber < 1 || discardNumber > 13)
        {
            lou = new Scanner(System.in);
            if (lou.hasNextInt())
            {
                discardNumber = lou.nextInt();
            }
            if (discardNumber < 1 || discardNumber > 13)
            {
                System.out.print("Please enter a number between (A)1 & 13(K) -> ");
            }
        }

        discardCard.setNumber(discardNumber);
        System.out.println("\nCard Number = " + Ansi.CYAN + discardCard.getCardNumber() + Ansi.RESET);
        System.out.print("\n***Please enter a number representing a suit***\n");
        System.out.print(Ansi.CYAN + "| 8 = <3 | 6 = <* | 4 = # | 2 = ^ |" + Ansi.RESET + "\n");
        System.out.print("Enter number for suit: ");

        // provides suit validation
        int discardSuit = 5;
        while (discardSuit % 2 != 0)
        {
            lou = new Scanner(System.in);
            if (lou.hasNextInt()) {
                discardSuit = lou.nextInt();
            }
            boolean aSuitNumber =  discardSuit == 2 || discardSuit == 4 || discardSuit == 6 || discardSuit == 8;
            if (!aSuitNumber)
            {
                discardSuit = 5;
                System.out.print("\n***Please enter a number representing a suit***\n");
                System.out.print(Ansi.RED + "| 8 = <3 | 6 = <* | 4 = # | 2 = ^ |" + Ansi.RESET + "\n");
                System.out.print("Please enter one of the suit numbers: ");
//                System.out.print("Enter number for suit: ");
            }
        }
//        System.out.println(Ansi.ANSI_CLS);
        switch (discardSuit) {
            case 8:
                discardCard.setSuit("<3");
                break;
            case 6:
                discardCard.setSuit("<*");
                break;
            case 2:
                discardCard.setSuit("^");
                break;
            case 4:
                discardCard.setSuit("#");
                break;
            default:
                break;
        }

        return discardCard;
    }

    public static boolean checkForWinner(Player user , Player p1, Player p2) {
        if(user.isAWinner())
        {
            System.out.println(Ansi.BACKGROUND_MAGENTA + Ansi.CYAN + "YOU HAVE WON" + Ansi.RESET);
            user.getHand();
            return true;
        }
        else if(p1.isAWinner())
        {
            System.out.println(Ansi.BACKGROUND_MAGENTA + Ansi.CYAN + "Computer One has WON yalll ...... amazing" + Ansi.RESET);
            p1.getHand();
            return true;
        }
        else if(p2.isAWinner())
        {
            System.out.println(Ansi.BACKGROUND_MAGENTA + Ansi.CYAN + "Computer Two has WON yalll ...... amazing" + Ansi.RESET);
            p2.getHand();
            return true;
        }
        return false;
    }

    public static void endGame() {
        Player theWinner = playerOne.getScore() < playerTwo.getScore() ? playerOne : playerTwo;
        theWinner = theWinner.getScore() < user.getScore() ? theWinner : user;
        System.out.println("GAME OVER");
        System.out.print("The winner of the game is: ");
        System.out.println("Player " + (  ((ComputerPlayer)theWinner).playerNumber == 3  ? "user" :  ((ComputerPlayer)theWinner).playerNumber ) );
        System.out.println("Player one point total: " + playerOne.getScore());
        System.out.println("Player two point total: " + playerTwo.getScore());
        System.out.println("Player user point total: " + user.getScore());
    }
} // end PlayWizard
