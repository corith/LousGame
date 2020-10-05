// Todo: the whole class pretty much

import java.util.Stack;

class ComputerPlayer extends Player {

    int playerNumber;

  public ComputerPlayer(int playerNumber)
  {
      super(("Computer " + playerNumber));
      this.playerNumber = playerNumber;
  }

  private int getComputerDecision(Card playPlate)
  {
      this.hand.findRunsAndMelds(false);
      int scoreBefore = this.tallyScore();
      int scoreAfterPickUp = 0;
      Card plateCheck = playPlate;

      // put the card in the hand
      for (int index = 0; index < this.hand.deadwood.cards.length; index++) {
          if (this.hand.deadwood.cards[index].getSuit() == null) {
              this.hand.deadwood.cards[index] = playPlate;
          }
      }

      // re count the hand and see if it improved
      this.hand.findRunsAndMelds(false);
      scoreAfterPickUp = this.tallyScore();

      // if score after pick up is less then go ahead and pick up the card at the top of playplate
      if (scoreAfterPickUp < scoreBefore )
      {
          System.out.println("Picked up from playPlate");
          return 1;
      }
      else
      {
          System.out.println("Picked up from top of deck" );
          // remove the playPlate card from hand so pick up from the deck works
          for (int index = 0; index < this.hand.deadwood.cards.length; index++) {
              if (this.hand.deadwood.cards[index].equals(playPlate)) {
                  this.hand.deadwood.cards[index] = new Card();
              }
          }
          return 0;
      }
  }

  public int decide() {
      if (this.playerNumber == 1) {
          System.out.println("Picked up from playPlate");
          return 1;
      } else if (this.playerNumber == 2) {
          System.out.println("Picked up from top of deck" );
          return 0;
      } else {
          System.out.println("Picked up from playPlate");
          return 1;
      }
  }

  public void computerTakeTurn(DeckOfCards sDeck, Stack<Card> playPlate)
  {
      System.out.println(Ansi.RED + "COMPUTER (" + this.playerNumber + ") IS TAKING ITS TURN" + Ansi.RESET);
      this.getHand();
      Card discardCard = new Card();
      Card pp = playPlate.peek();

      boolean fromDeck = this.getComputerDecision(playPlate.peek()) == 0;
//      boolean temp = this.decide() == 0;
      if (fromDeck) {
          this.pickUpCard(sDeck);
      } else {
          this.pickUpCard(playPlate);
      }

      for (int i = 0; i < this.hand.deadwood.getCount(); i++)
      {
          if (!this.hand.deadwood.cards[i].isBeingUsed() && !this.hand.deadwood.cards[i].equals(pp))
          {
              discardCard = this.hand.deadwood.cards[i];
          }
      }
      if (discardCard.getSuit() == null)
          discardCard = this.hand.deadwood.cards[this.hand.deadwood.getCount()];
      this.putDownDiscard(discardCard);
      System.out.println("Discarded " + discardCard);
      this.getHand(1);
  }







  public static void main(String[] args) {

      System.out.println("The test class for CompTurn class");
  }
}
