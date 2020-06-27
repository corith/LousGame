/****************************************************
 ** Cory Sebastian Main Class for Lous stupid game **
 ** (first from scratch project. Start: 12/20/18)  **
 *****************************************************/

class LousReady
{
//    public static Player playerOne = new Player();
    public static ComputerPlayer playerOne = new ComputerPlayer(1);
//    public static Player playerTwo = new Player();
    public static ComputerPlayer playerTwo = new ComputerPlayer(2);
    public static Player user = new Player();
//    public static  ComputerPlayer user = new ComputerPlayer(3);
    public static int round = 3;

    public static void main(String[] args)
    {
        DeckOfCards deck = new DeckOfCards(1);
        deck.getDeck();
        deck = deck.shuffleDeck();

//        if (!Greeting.userGreeting())
//        {
//            System.out.println("Okay.....goodbye.");
//            return;
//        }
//        else
//        {
//            System.out.println("Okay sweet! Let's begin! Here is your hand!");
//        }

        deck.dealDeck(playerOne , playerTwo , user);          // players hands are full. playplate gets filled in playLoop()
        AssDriver.playLoop(deck, playerOne , playerTwo , user);             // commences game
/*
        while (round < 14)
        {
            deck = DeckOfCards.getDeck();
            deck = DeckOfCards.shuffleDeck(deck);
            DeckOfCards.dealDeck(deck , playerOne , playerTwo , user);

            if (AssDriver.playLoop(deck, playerOne , playerTwo , user) )
            {
                round += 1;
            }
        }
*/
    }
}
