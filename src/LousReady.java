import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LongSummaryStatistics;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/***************************************************
 **             Cory Sebastian                    **
 *       Main Class for Lous stupid game          **
 ** (first from scratch project. Start: 12/20/18) **
 ***************************************************/

class LousReady {
//    public static Player playerOne = new Player();
//    public static Player playerTwo = new Player();
//    public static Player user = new Player();
    public static ComputerPlayer playerOne = new ComputerPlayer(1);
    public static ComputerPlayer playerTwo = new ComputerPlayer(2);
    public static ComputerPlayer user      = new ComputerPlayer(3);
    public static Logger logger = Logger.getLogger(LousReady.class.getName());
    private static int round = 3;

    public static int getRound() {
        return round;
    }

    public static void setRound(int round) {
        LousReady.round = round;
    }

    public static void main(String[] args) throws IOException {
//        if (!Greeting.handleRealUser()) {
//            return;
//        }
        configureLogger();
        logger.info("Starting main loop");
        while (round < 8)
        {
            logger.info("Beginning round " + round);
            //Makes sure that each player's hand size corresponds to round number
            playerOne.hand = new Hand();
            playerTwo.hand = new Hand();
            user.hand = new Hand();

            DeckOfCards deck = new DeckOfCards();
            logger.info("Cleared the discard pile");
            AssDriver.discardPile.clear();

            deck.initializeDeck();
            deck.shuffleDeck();
            deck.dealDeck(playerOne , playerTwo , user);
            logger.info("Initialized, shuffled, and dealt the deck.");

            if (AssDriver.playLoop(deck, playerOne , playerTwo , user)) {
                PlayWizard.grossScore();
            }
            round = round + 1;
        }
        PlayWizard.endGame();
    }

    private static void configureLogger() throws IOException {
        String path = "/Users/corysebastian/Desktop/LousLogs/LousGLog.log";
        File log = new File(path);
        //noinspection ResultOfMethodCallIgnored
        if ( !log.createNewFile() )  {
            log = new File(path+ new Date() +".log");
//            log = new File(path+ UUID.randomUUID() +".log");
        }

        FileHandler fileHandler = new FileHandler(log.getAbsolutePath());
        Formatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
    }
}
