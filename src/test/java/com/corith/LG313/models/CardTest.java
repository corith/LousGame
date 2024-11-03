package com.corith.LG313.models;

import com.corith.LG313.enums.CardRank;
import com.corith.LG313.enums.Suit;
import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void testEquals() {
        Card card1 = new Card(Suit.CLUBS, CardRank.SEVEN);
        Card card2 = new Card(Suit.CLUBS, CardRank.EIGHT);
        Assert.assertNotEquals(card1, card2);
        card1 = new Card(Suit.HEARTS, CardRank.SEVEN);
        card2 = new Card(Suit.CLUBS, CardRank.SEVEN);
        Assert.assertNotEquals(card1, card2);
        card1 = new Card(Suit.CLUBS, CardRank.NINE);
        card2 = new Card(Suit.CLUBS, CardRank.NINE);
        Assert.assertEquals(card1, card2);
    }

}
