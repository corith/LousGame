// this class should most likely be encapsulated
// inside Hand.java....maybe

/**
 * This class is intended to represent different
 * "staging" areas of a players hand. For example,
 * one HandNode would hold the players runs, another
 * would hold the players melds (ofAKinds) and another
 * would hold the players dead would. This design has not
 * been fully implemented though and so only one node is
 * used inside Hand.
 */
class HandNode
{
    private int count;
    private int powerRank;          // might be used for "ranking" a collection of cards by points it counts against the player
    private HandNode next;
    private HandNode prev;

    public Card[] cards;            // = new Card[LousReady.getRound + 1];

    //getters
    public int getCount() { return count; }
    public int getPowerRank() { return powerRank; }
    public HandNode getNext() { return next; }
    public HandNode getPrev() { return prev; }

    // setters
    public void setCount(int count) { this.count = count; }
    public void setPowerRank(int powerRank) { this.powerRank = powerRank; }
    public void setNext(HandNode next) { this.next = next; }
    public void setPrev(HandNode prev) { this.prev = prev; }

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
        cards = new Card[LousReady.getRound() + 1];
        next = null;
    }
} // end class HandNode
