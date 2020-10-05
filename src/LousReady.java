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
//    public static Player user = new Player();
    public static  ComputerPlayer user = new ComputerPlayer(3);
    public static int round = 5;

    public static void main(String[] args)
    {
//        deck.getDeck();
//        deck.shuffleDeck();
//        deck.shuffleDeck();

//        if (!Greeting.userGreeting())
//        {
//            System.out.println("Okay.....goodbye.");
//            return;
//        }
//        else
//        {
//            System.out.println("Okay sweet! Let's begin! Here is your hand!");
//        }

        while (Player.getRound() < 6)
        {
            //Makes sure that each players hand size corresponds to round number
            playerOne.hand = new Hand();
            playerTwo.hand = new Hand();
            user.hand = new Hand();


            DeckOfCards deck = new DeckOfCards();
            AssDriver.discardPile.clear();
            deck.getDeck();
            deck.shuffleDeck();
            deck.dealDeck(playerOne , playerTwo , user);

            int wasAwinner = AssDriver.playLoop(deck, playerOne , playerTwo , user);
            if (wasAwinner == 1) {
                PlayWizard.grossScore();
            }
            Player.setRound(Player.getRound() + 1);
        }
        System.out.println("GAME OVER");
        System.out.println("Player one point total: " + playerOne.getScore());
        System.out.println("Player two point total: " + playerTwo.getScore());
        System.out.println("Player user point total: " + user.getScore());
    }
}
