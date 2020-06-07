/****************************************************
 **             Cory Sebastian                     **
 **               Card class                       **
 ********INCLUDES THE FOLLOWING METHODS**************
 *  - getNumber(),getSuit(),setNumber(),setSuit()   *
 *  - Card() / Card(String suit, int number)        *
 *  - String toString() && equals()                 *
 *  - Card[] discard()                              *
 *  - main(String args)                             *
 ****************************************************
 ************************src*************************/

public class Card
{
    public int number;
    public String suit;
    private boolean isARun;
    private boolean isAMeld;
    private boolean isWild = false;
// getters
    public int getNumber() { return number; }
    public String getSuit() { return suit; }
    public boolean isARun() { return isARun; }
    public boolean isAMeld() { return isAMeld; }
    public boolean isWild() { return isWild; }
// setters
    public void makeItRun() { this.isARun = true; }
    public void makeItMeld() { this.isAMeld = true; }
    public void makeItWild() { this.isWild = true; }
    public void setNumber(int number) { this.number = number; }
    public void setSuit(String suit)  { this.suit = suit; }

    public Card()
    {
        this.suit = null;
        this.number = -1;
        isARun = false;
        isAMeld = false;
        isWild = false;
    }

    public Card(String suit , int number)
    {
        this.suit = suit;
        this.number = number;
        isARun = false;
        isAMeld = false;
        if (this.number == Player.round)
        {
            isWild = true;
        }
        else
        {
            isWild = false;
        }
    }

    public String toString()
    {
        if (this.suit == null)
            return Ansi.HIGH_INTENSITY + Ansi.MAGENTA + "|_|" + Ansi.RESET;
        else if (this.suit.equals("<3"))
        {
            return this.number + " " + Ansi.RED + Ansi.HIGH_INTENSITY + "<3" + Ansi.RESET + "";
        }
        else if (this.suit.equals("<*"))
        {
            return this.number + " " + Ansi.RED + Ansi.HIGH_INTENSITY + "<*" + Ansi.RESET + "";
        }
        else if (this.suit.equals("^"))
        {
            return this.number + " " + Ansi.BACKGROUND_WHITE + Ansi.BLACK + Ansi.HIGH_INTENSITY + "^" + Ansi.RESET + "";
        }
        else if (this.suit.equals("#"))
        {
            return this.number + " " + Ansi.BACKGROUND_WHITE + Ansi.BLACK + Ansi.HIGH_INTENSITY + "#" + Ansi.RESET + "";
        }
        else
            return this.number + " " + this.suit + "";
    }




    public boolean equals(Object exampleTwo)
    {
        if (this == exampleTwo)
        {
            return true;
        }
        if (exampleTwo == null)
        {
            return false;
        }
        Card exampleDeux = (Card)exampleTwo;
        return suit.equals(exampleDeux.suit) && number == exampleDeux.number;
    }
} // end class Card



class CardTest
{

/************** BEGIN MAIN METHOD FOR TESTING PURPOSES ******************/
  public static void main(String[] args) {

    Card c1 = new Card("<3" , 5);
    Card c2 = new Card("<3" , 5);
    Card c3 = new Card("<*" , 3);
    Object test = new Card("<3" , 5);



    Card[] testCard = new Card[5];
    for (int i = 0; i < testCard.length; i++) {
      testCard[i] = new Card("<*" , 4);
      if (i == 3) {
        testCard[i] = new Card("^" , 9);
      }
      System.out.println(testCard[i]);
    }

    Card death = new Card("^" , 9);
    Card ref;
    for (int i = 0; i < testCard.length; i++) {
      ref = testCard[i];
      if (death.equals(ref)) {
        testCard[i] = new Card();
        System.out.println(testCard[i]);
      } else {
        System.out.println(testCard[i]);
      }
    }


    if (c1.equals(c2)) {
      System.out.println("EQUAL BITCH");
    }
    if (c1.equals(test)) {
      System.out.println("the object is equal");
    }
    if (!c1.equals(c3)) {
      System.out.println("they aint equal hehe");
    }
    else {
      System.out.println("STUPID BITCH");
    }

    //System.out.println(c3.isWild);
    //Card one = new Card("<3" , 2);
    //Card two = new Card("Spade" , 3);

    //System.out.print(one);
    //System.out.println(one.suit);

    //System.out.println(two.suit);
    //System.out.println(two.number);
  }
}
