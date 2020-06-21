class LousReady
{
    public static Player playerOne = new Player();
    public static Player playerTwo = new Player();
    public static Player user = new Player();
    public static int round = 3;

    public static void main(String[] args)
    {
        Card[] deck = DeckOfCards.getDeck();
        deck = DeckOfCards.shuffleDeck(deck);

        if (!Greeting.userGreeting())
        {
            System.out.println("Okay.....goodbye.");
            return;
        }
        else
        {
            System.out.println("Okay sweet! Let's begin! Here is your hand!");
        }

        DeckOfCards.dealDeck(deck , playerOne , playerTwo , user);          // players hands are full. playplate gets filled in playLoop()
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
