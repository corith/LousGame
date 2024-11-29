package com.corith.lgchicken.models;

import com.corith.lgchicken.enums.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;

public class DeckTest {

    @Test
    public void testStandardDeckCreation() {
        CardDeck cardDeck = new CardDeck();
        Deque<Card> cards = cardDeck.cards;
        boolean hearts = cards.stream().filter(e->e.getSuit().equals(Suit.HEARTS)).count() == 13;
        boolean diamonds = cards.stream().filter(e->e.getSuit().equals(Suit.DIAMONDS)).count() == 13;
        boolean spades = cards.stream().filter(e->e.getSuit().equals(Suit.SPADES)).count() == 13;
        boolean clubs = cards.stream().filter(e->e.getSuit().equals(Suit.CLUBS)).count() == 13;
        Assert.assertTrue(hearts);
        Assert.assertTrue(diamonds);
        Assert.assertTrue(spades);
        Assert.assertTrue(clubs);
    }

}
