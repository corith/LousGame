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
        System.out.print("Now, enter the suit: ");
        while (discardCard.getSuit() == null)
        {
            lou = new Scanner(System.in);
            discardSuit = lou.nextLine();
            // the following switch statement translate user input into the
            // Ansi colored representation

            switch (discardSuit) {
                case "<3":
                    discardCard.setSuit(discardSuit);
                    break;
                case "<*":
                    discardCard.setSuit(discardSuit);
                    break;
                case "^":
                    discardCard.setSuit(discardSuit);
                    break;
                case "#":
                    discardCard.setSuit(discardSuit);
                    break;
                default:
                    System.out.print("Please enter one of the suits: ");
                    break;
            }
        }
        return discardCard;
    } // end getDiscardCardcard()

} // end PlayWizard
