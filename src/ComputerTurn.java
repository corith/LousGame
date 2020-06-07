// Todo: the whole class pretty much

class CompWizard extends Player {
  private Card[][] compHand;

  public CompWizard()
  {
    super();
    this.compHand = new Card[8][13];  // a way for the computer to keep track of the cards that have been played
  }



  public void addCard(Card pickupCard)
  {
    
  }
  //System.out.print(test.compHand[i][j][k] + " ");

  public static void main(String[] args) {
    CompWizard test = new CompWizard();
    Card[] deck = DeckOfCards.getDeck();
    DeckOfCards.initializeDeck(deck);

/*
    for (Card card : deck) {
      if (card.number == 13) {
        System.out.print(card);
        System.out.println();
      }
      else {
        System.out.print(card);
      }
    }

*/

/*
    System.out.println("the length of compHand is : " + test.compHand.length);
    System.out.println(test.compHand[3].length);
    int i = 0;
    for (int row = 0; row < test.compHand.length; row += 1) {
      for (int col = 0; col < test.compHand[row].length; col += 1) {
        if (i < deck.length) {
          test.compHand[row][col] = deck[i];
        }
        else {
          test.compHand[row][col] = new Card();
        }
        i += 1;
      }
    }
*/


    for (int row = 0; row < test.compHand.length; row += 1) {
      //System.out.println();
      for (int col = 0; col < test.compHand[row].length; col += 1) {
        System.out.print(test.compHand[row][col] + " ");
      }
      System.out.println();
    }


    //System.out.println("The last card (13 #) is : " + test.compHand[3]);

  }
}
