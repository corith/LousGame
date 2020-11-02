import java.util.Scanner;

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
        Scanner lou = new Scanner(System.in);
        int discardNumber = -1;
        String discardSuit = null;
        Card discardCard = new Card(discardSuit , discardNumber);

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

        int theSuitput = 5;
        while (theSuitput % 2 != 0)
        {
            lou = new Scanner(System.in);
            if (lou.hasNextInt()) {
                theSuitput = lou.nextInt();
            }
            boolean aSuitNumber =  theSuitput == 2 || theSuitput == 4 || theSuitput == 6 || theSuitput == 8;
            if (!aSuitNumber)
            {
                theSuitput = 5;
                System.out.print("\n***Please enter a number representing a suit***\n");
                System.out.print(Ansi.RED + "| 8 = <3 | 6 = <* | 4 = # | 2 = ^ |" + Ansi.RESET + "\n");
                System.out.print("Please enter one of the suit numbers: ");
//                System.out.print("Enter number for suit: ");
            }
        }
//        System.out.println(Ansi.ANSI_CLS);
        // this switch statement provides suit validation
        switch (theSuitput) {
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
//                System.out.print("Please enter one of the suits: ");
                break;
        }

        return discardCard;
    }

    public static int checkForWinner(Player user , Player p1, Player p2) {
        if(user.isAWinner())
        {
            System.out.println(Ansi.BACKGROUND_MAGENTA + Ansi.CYAN + "YOU HAVE WON" + Ansi.RESET);
            user.getHand();
            return 1;
        }
        else if(p1.isAWinner())
        {
            System.out.println(Ansi.BACKGROUND_MAGENTA + Ansi.CYAN + "Computer One has WON yalll ...... amazing" + Ansi.RESET);
            p1.getHand();
            return 1;
        }
        else if(p2.isAWinner())
        {
            System.out.println(Ansi.BACKGROUND_MAGENTA + Ansi.CYAN + "Computer Two has WON yalll ...... amazing" + Ansi.RESET);
            p2.getHand();
            return 1;
        }
        return 0;
    }

} // end PlayWizard
