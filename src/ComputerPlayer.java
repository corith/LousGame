// Todo: the whole class pretty much

class ComputerPlayer extends Player {

    int playerNumber;

  public ComputerPlayer(int playerNumber)
  {
      super(("Computer " + playerNumber));
      this.playerNumber = playerNumber;
  }

//  public void computerPickUpCard(Card[] sDeck, int top)
//  {
//      for (int index = 0; index < this.hand.deadwood.getCount(); index++)    // picks up the card from the top of the deck
//          if (this.hand.deadwood.cards[index].getSuit() == null)        // replaces the null slot in player hand
//              this.hand.deadwood.cards[index] = sDeck[top + 1];
//
//  }
//
//  public void computerPickUpCard(Card[] playPlate)
//  {
//      // swaps the empty spot in players hands
//      for (int index = 0; index < this.hand.deadwood.cards.length; index++)
//          if (this.hand.deadwood.cards[index].getSuit() == null)       //  with the card in playplate[]
//              this.hand.deadwood.cards[index] = playPlate[0];
//  }

  private int getComputerDecision(Card playPlate)
  {
      this.hand.findRunsAndMelds(false);
      int scoreBefore = this.tallyScore();
      int scoreAfterPickUp = 0;

      // put the card in the hand
      for (int index = 0; index < this.hand.deadwood.cards.length; index++)
          if (this.hand.deadwood.cards[index].getSuit() == null)       //  with the card in playplate[]
              this.hand.deadwood.cards[index] = playPlate;

      // re count the hand and see if it improved
      this.hand.findRunsAndMelds(false);
      scoreAfterPickUp = this.tallyScore();

      // if score after pick up is less then go ahead and pick up the card at the top of playplate
      if (scoreAfterPickUp < scoreBefore && playPlate.isBeingUsed())
      {
          System.out.println("Picked up from playPlate");
          return 1;
      }
      else
      {
          System.out.println("Picked up from top of deck" );
          return 0;
      }
  }

  public void computerTakeTurn(DeckOfCards sDeck , Card[] playPlate , int topCard)
  {
      System.out.println(Ansi.RED + "COMPUTER (" + this.playerNumber + ") IS TAKING ITS TURN" + Ansi.RESET);
      Card discardCard = new Card();

      boolean fromDeck = this.getComputerDecision(playPlate[0]) == 0;
      if (fromDeck)
          this.pickUpCard(sDeck, topCard);
      else
          this.pickUpCard(playPlate);

      for (int i = 0; i < this.hand.deadwood.getCount(); i++)
      {
          if (!this.hand.deadwood.cards[i].isBeingUsed())
          {
              discardCard = this.hand.deadwood.cards[i];
          }
      }
      playPlate[0] = discardCard;                               // end process of physical discard and swap to playplate
      this.putDownDiscard(discardCard);
      System.out.println("Discarded " + discardCard);
      this.getHand();
  }

  public static void main(String[] args) {

      System.out.println("The test class for CompTurn class");
  }
}
