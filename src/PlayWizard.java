import java.util.Scanner;

class PlayWizard extends AssDriver
{

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
        System.out.print("\nP***lease enter a number representing a suit***\n");
        System.out.print(Ansi.CYAN + "| 8 = <3 | 6 = <* | 4 = # | 2 = ^ |" + Ansi.RESET + "\n");
        System.out.print("Enter number for suit: ");

        int theSuitput = 5;
        while (theSuitput % 2 != 0 && theSuitput != 0)
        {
            lou = new Scanner(System.in);
            if (lou.hasNextInt()) {
                theSuitput = lou.nextInt();
            }
            boolean isSuitNumber =  theSuitput == 2 || theSuitput == 4 || theSuitput == 6 || theSuitput == 8;
            if (!isSuitNumber)
            {
                theSuitput = 5;
                System.out.print("\n***Please enter a number representing a suit***\n");
                System.out.print(Ansi.RED + "| 8 = <3 | 6 = <* | 4 = # | 2 = ^ |" + Ansi.RESET + "\n");
                System.out.print("Please enter one of the suit numbers: ");
//                System.out.print("Enter number for suit: ");
            }
        }


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

} // end PlayWizard
