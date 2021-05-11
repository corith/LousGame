/***************************************************
 **             Cory Sebastian                    **
 *       Main Class for Lous stupid game          **
 ** (first from scratch project. Start: 12/20/18) **
 ***************************************************/

class LousReady
{
//    public static Player playerOne = new Player();
//    public static Player playerTwo = new Player();
//    public static Player user = new Player();
    public static ComputerPlayer playerOne = new ComputerPlayer(1);
    public static ComputerPlayer playerTwo = new ComputerPlayer(2);
    public static ComputerPlayer user      = new ComputerPlayer(3);
    private static int round = 3;

    public static int getRound() {
        return round;
    }

    public static void setRound(int round) {
        LousReady.round = round;
    }

    public static void main(String[] args)
    {
//        if (!Greeting.handleRealUser()) {
//            return;
//        }

        while (round < 14)
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

            if (AssDriver.playLoop(deck, playerOne , playerTwo , user)) {
                PlayWizard.grossScore();
            }
            round = round + 1;
        }
        PlayWizard.endGame();
    }
}
