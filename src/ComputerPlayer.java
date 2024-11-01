import java.util.Stack;
import java.util.logging.Logger;

/**
 * This class extends Player and is intended to model a computer player.
 * Pretty similar setup to Player. NOTE: this class does not override user
 * take turn but instead has it's own method called "computerTakeTurn".
 */
class ComputerPlayer extends Player {
    private static Logger logger  = Logger.getLogger("Computer Player");

    public int playerNumber;

    public ComputerPlayer(int playerNumber) {
        super(("Computer " + playerNumber));
        this.playerNumber = playerNumber;
    }

    /**
     * This method decides if the computer is going to
     * pick from the top of the deck or the discardPile.
     * This method is equivalent to AssDriver.getDecision()
     * for a real player. It checks to see if the card showing
     * on the discardPile will lower the score in the hand. If
     * it does, then it picks it up, if not then it picks from deck.
     *
     * @param playPlate the Card showing on the discardPile
     * @return integer - 0 for top of deck and 1 for discardPile
     */
    private int getComputerDecision(Card playPlate) {
        this.hand.findRunsAndMelds(false);
        int scoreBefore = this.tallyScore();
        int scoreAfterPickUp = 0;

        // put the "what if" card from discardPile in the player's hand
        for (int index = 0; index < this.hand.deadwood.cards.length; index++) {
            if (this.hand.deadwood.cards[index].getSuit() == null) {
                this.hand.deadwood.cards[index] = playPlate;
            }
        }

        // re count the hand and see if it improved
        this.hand.findRunsAndMelds(false);
        // if score after pick up is less then decision is to pick up the card at the top of discardPile
        scoreAfterPickUp = this.tallyScore();

        removeHypotheticalPlayPlateCard(playPlate);
        if (scoreAfterPickUp < scoreBefore || playPlate.getCardNumber() == LousReady.getRound()) {
            System.out.println("Picking up from playPlate");
            return 1;
        } else {
            System.out.println("Picking up from top of deck");
            return 0;
        }
    }

    /**
     * Helper method for removing hypothetical discardCard from
     * the player's hand. This is necessary regardless of if
     * the computer chose to draw from deck or discardPile.
     *
     * @param playPlate the hypothetical card to be removed
     */
    private void removeHypotheticalPlayPlateCard(Card playPlate) {
        for (int index = 0; index < this.hand.deadwood.cards.length; index++) {
            if (this.hand.deadwood.cards[index].equals(playPlate)) {
                this.hand.deadwood.cards[index] = new Card();
            }
        }
    }

    /**
     * Similar to userTakeTurn. Picks up a card and discards a card for the computer.
     * NOTE: the variable pp is required because if the player picks up from playPlate
     * then playPlate.peek() will no longer show us the right card.
     *
     * @param sDeck the current deck for the round
     * @param playPlate discardPile
     */
    public void computerTakeTurn(DeckOfCards sDeck, Stack<Card> playPlate) {
        System.out.println(Ansi.RED + "COMPUTER (" + this.playerNumber + ") IS TAKING ITS TURN" + Ansi.RESET);
        this.getHand();
        Card discardCard = new Card();
        Card pp = playPlate.peek();

        boolean fromDeck = this.getComputerDecision(playPlate.peek()) == 0;
        if (fromDeck) {
            this.pickUpCard(sDeck);
        } else {
            this.pickUpCard(playPlate);
        }


        int highestCardIndex = -1;

        for (int i = 0; i < this.hand.deadwood.getCount(); i++) {
            boolean isEligableForDiscard = (!this.hand.deadwood.cards[i].isBeingUsed() && !this.hand.deadwood.cards[i].equals(pp)  && this.hand.deadwood.cards[i].getCardNumber() != LousReady.getRound()  );
            if ( isEligableForDiscard ) {

                if ( highestCardIndex == -1   ) {
                    // discard card = first that is eligible
                    discardCard = this.hand.deadwood.cards[i];
                    highestCardIndex = i;
                } else {
                    // test if the next candidate is higher than the current highest card.
                    if (this.hand.deadwood.cards[i].getCardNumber() > this.hand.deadwood.cards[highestCardIndex].getCardNumber()) {
                        highestCardIndex = i;
                        discardCard = this.hand.deadwood.cards[i];
                    }
                }
//                highestCard = Math.max(discardCard.getCardNumber(), highestCard);
//                discardCard = this.hand.deadwood.cards[this.hand.deadwood.getCount()];
            }

        }

        // if all cards are being used or it tried to discard playPlate then the discardCard suit will be null
        if (discardCard.getSuit() == null) {
            discardCard = this.hand.deadwood.cards[this.hand.deadwood.getCount()];
            System.out.println("Discarded the hihgest card in my hand-[");
            LousReady.logger.info("Discareded Highest Card in Hand....");
        }


        this.putDownDiscard(discardCard);
        System.out.println("Discarded " + discardCard);
        this.getHand(1);
        this.hand.findRunsAndMelds(false);
    }

}



class ComputerPlayerTest {
    public static void main(String[] args) {
        System.out.println("The test class for CompTurn class");
    }
}
