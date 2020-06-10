import java.util.Scanner;

class PlayWizard extends AssDriver
{

    public static Card getDiscardCard()
    {
        Scanner lou = new Scanner(System.in);
        int discardNumber = -1;
        String discardSuit = null;
        Card discardCard = new Card(discardSuit , discardNumber);
//        System.out.println("Now, which card would you like to discard?");
//        System.out.print("Please enter the number of the card first: ");

        while (discardNumber < 1 || discardNumber == 0)
        {
            lou = new Scanner(System.in);
            if (lou.hasNextInt())
            {
                discardNumber = lou.nextInt();
            }
            if (discardNumber < 1)
            {
                System.out.print("Please enter a number greater than 0: ");
            }
        }

        discardCard.setNumber(discardNumber);
        System.out.print("Now, enter the suit: ");
        while (discardCard.suit == null)
        {
            lou = new Scanner(System.in);
            discardSuit = lou.nextLine();
            // the following if else blocks translate user input into the
            // Ansi colored representation
            if (discardSuit.equals("<3"))
            {
                discardCard.setSuit(discardSuit);
            }
            else if (discardSuit.equals("<*"))
            {
                discardCard.setSuit(discardSuit);
            }
            else if (discardSuit.equals("^"))
            {
                discardCard.setSuit(discardSuit);
            }
            else if (discardSuit.equals("#"))
            {
                discardCard.setSuit(discardSuit);
            }
            else
            {
                System.out.print("Please enter one of the suits: ");
            }
        } // end while loop
        return discardCard;
    } // end getDiscardCardcard()

} // end PlayWizard
