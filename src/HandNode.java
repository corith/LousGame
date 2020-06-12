// this class should most likely be encapsulated
// inside Hand.java....maybe

class HandNode
{
    public HandNode next;
    public Card[] cards;            // = new Card[Player.round + 1];
    public int powerRank;           // might be used for "ranking" a collection of cards by points it counts against the player
    public int count;
    public HandNode prev;

    public HandNode(HandNode prev , Card[] hand , HandNode next)
    {
        this.prev = prev;
        this.cards = hand;
        this.next = next;
        this.powerRank = 0;
        this.count = 0;
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
