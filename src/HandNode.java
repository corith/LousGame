// this class should most likely be encapsulated
// indside Hand.java

class HandNode
{
    public HandNode next;
    public Card[] cards    = new Card[Player.round + 1];
    public int powerRank   = 0;
    public int count       = 0;
    public HandNode prev;

    public HandNode(HandNode prev , Card[] hand , HandNode next)
    {
        this.prev = prev;
        this.cards = hand;
        this.next = next;
    }

    public HandNode(Card[] userHand)
    {
        prev = null;
        cards = userHand;
        next = null;
    }

    public HandNode()
    {
        prev = null;
        cards = new Card[Player.round + 1];
        next = null;
    }
} // end class HandNode
