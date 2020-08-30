import java.util.Deque;

/****************************************************
 ** Cory Sebastian Main Class for Lous stupid game **
 ** (first from scratch project. Start: 12/20/18)  **
 *****************************************************/

class LousReady
{
    public static ComputerPlayer playerOne = new ComputerPlayer(1);
    public static ComputerPlayer playerTwo = new ComputerPlayer(2);
    public static ComputerPlayer user = new ComputerPlayer(3);
    public static int round = 3;

    public static void main(String[] args)
    {

        while (Player.getRound() < 14)
        {
            //Makes sure that each players hand size corresponds to round number
            playerOne.hand = new Hand();
            playerTwo.hand = new Hand();
            user.hand = new Hand();

            DeckOfCards deck = new DeckOfCards();
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
