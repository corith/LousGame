package com.corith.LG313;

import com.corith.LG313.enums.CardRank;
import com.corith.LG313.enums.Suit;
import com.corith.LG313.models.Card;
import com.corith.LG313.models.CardDeck;
import com.corith.LG313.models.player.ComputerPlayer;
import com.corith.LG313.models.player.Player;
import com.corith.LG313.models.player.UserPlayer;

import com.corith.LG313.utility.Ansi;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class AppTest {

    @Test
    public void testCard() {
        Card card = new Card(Suit.CLUBS, CardRank.SEVEN);
        Assert.assertEquals(card.getSuit(), Suit.CLUBS);
        Assert.assertEquals(card.getCardRank(), CardRank.SEVEN);
        System.out.println(card);
    }

    @Test
    public void testDeckCreation() {
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
//        for (Card card : cards) {
//            System.out.println(card);
//            System.out.println();
//            System.out.println();
//        }
    }

    @Test
    public void testCardCompare() {
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

    @Test
    public void testPlayerShuffle() {
        Player userPlayer = new UserPlayer();
        CardDeck standardDeck = new CardDeck();
        CardDeck testDeck = new CardDeck();

        userPlayer.shuffleCards(testDeck.cards);
        Assert.assertNotEquals(standardDeck.cards, testDeck.cards); // The order should differ
        Assert.assertTrue(testDeck.cards.containsAll(standardDeck.cards));
        Assert.assertEquals(testDeck.cards.size(), standardDeck.cards.size()); // Sizes should match
    }

}
