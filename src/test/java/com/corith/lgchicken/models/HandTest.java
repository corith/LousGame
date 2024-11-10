package com.corith.lgchicken.models;

import com.corith.lgchicken.enums.CardRank;
import com.corith.lgchicken.enums.Suit;
import com.corith.lgchicken.models.player.ComputerPlayer;
import com.corith.lgchicken.models.player.Player;
import com.corith.lgchicken.models.player.UserPlayer;
import com.corith.lgchicken.utility.RenderEngine;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class HandTest {

    @Test
    public void testCreateBestHand() {
        Player userPlayer = new UserPlayer();
        Card c0 = new Card(Suit.HEARTS, CardRank.SIX);
        Card c1 = new Card(Suit.HEARTS, CardRank.FOUR);
        Card c2 = new Card(Suit.HEARTS, CardRank.FIVE);
        Card c3 = new Card(Suit.HEARTS, CardRank.EIGHT);
        Card c4 = new Card(Suit.HEARTS, CardRank.NINE);
        Card c5 = new Card(Suit.HEARTS, CardRank.TEN);
        Card c6 = new Card(Suit.HEARTS, CardRank.SEVEN);
        userPlayer.getHand().deadwood.add(c0);
        userPlayer.getHand().deadwood.add(c1);
        userPlayer.getHand().deadwood.add(c2);
        userPlayer.getHand().deadwood.add(c3);
        userPlayer.getHand().deadwood.add(c4);
        userPlayer.getHand().deadwood.add(c5);
        userPlayer.getHand().deadwood.add(c6);
        userPlayer.getHand().createBestHand();
        RenderEngine.renderHand(userPlayer.getHand());
        Assert.assertEquals(0, userPlayer.getHand().getDeadWoodValue());
    }

    @Test
    public void testWildRunBasic() {
        Player computer = new ComputerPlayer();
        Card wild = new Card(Suit.CLUBS, CardRank.FOUR);
        wild.setWild(true);
        computer.getHand().deadwood.add(new Card(Suit.SPADES, CardRank.FIVE));
        computer.getHand().deadwood.add(wild);
        computer.getHand().deadwood.add(new Card(Suit.SPADES, CardRank.SEVEN));
        computer.getHand().deadwood.add(new Card(Suit.SPADES, CardRank.EIGHT));
        computer.getHand().createBestHand();
        Assert.assertEquals(0, computer.getHand().getDeadWoodValue());
    }

    @Test
    public void handSmokeTest() {
        Player userPlayer = new UserPlayer();
        CardDeck standardDeck = new CardDeck();
        for (int i = 0; i<8; i++) {
            userPlayer.getHand().getDeadwood().add(standardDeck.cards.pop());
        }
        Assert.assertEquals(52 - 8, standardDeck.cards.size());
        Assert.assertEquals(userPlayer.getHand().deadwood.size(), 8);
    }

    @Test
    public void testNoDuplicateCardsOnDeal() {
        Player player = new UserPlayer();
        player.setName("Cory Sebastian");
        Player cpuPlayer0 = new ComputerPlayer();
        cpuPlayer0.setName("CPU 0");
        Player cpuPlayer1 = new ComputerPlayer();
        cpuPlayer1.setName("CPU 1");
        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(cpuPlayer0);
        players.add(cpuPlayer1);
        CardDeck gameDeck = new CardDeck();
        player.shuffleCards(gameDeck.cards);

        int cardsToDeal = 13;
        player.deal(gameDeck.cards, players, cardsToDeal);
        Set<Card> uniqueCards = new HashSet<>();
        List<Card> allCards = new ArrayList<>();
        // Add all cards from each player's hand
        for (Player p : players) {
            allCards.addAll(p.getHand().getDeadwood());
        }
        // Add all remaining cards in the deck
        allCards.addAll(gameDeck.cards);
        // Check for duplicates by attempting to add each card to a Set
        for (Card card : allCards) {
            boolean isUnique = uniqueCards.add(card);
            Assert.assertTrue("Duplicate card found: " + card, isUnique);
        }
        Assert.assertEquals(uniqueCards.size(), 52);
    }

    @Test
    public void testBasicRunsWithWild() {
        Player userPlayer = new UserPlayer();
        Card c0 = new Card(Suit.HEARTS, CardRank.SIX);
        Card c1 = new Card(Suit.HEARTS, CardRank.FOUR);
        Card c2 = new Card(Suit.HEARTS, CardRank.FIVE);
        Card c3 = new Card(Suit.SPADES, CardRank.EIGHT);
        Card c4 = new Card(Suit.SPADES, CardRank.NINE);
        Card c5 = new Card(Suit.SPADES, CardRank.TEN);
        Card c6 = new Card(Suit.DIAMONDS, CardRank.SEVEN);
        c6.setWild(true);
        userPlayer.getHand().deadwood.add(c0);
        userPlayer.getHand().deadwood.add(c1);
        userPlayer.getHand().deadwood.add(c2);
        userPlayer.getHand().deadwood.add(c3);
        userPlayer.getHand().deadwood.add(c4);
        userPlayer.getHand().deadwood.add(c5);
        userPlayer.getHand().deadwood.add(c6);
        userPlayer.getHand().createBestHand();
        RenderEngine.renderHand(userPlayer.getHand());
        Assert.assertEquals(0, userPlayer.getHand().getDeadWoodValue());
    }

    @Test
    public void testBasicMeld() {
        Player userPlayer = new UserPlayer();
        Card c0 = new Card(Suit.HEARTS, CardRank.SIX);
        Card c1 = new Card(Suit.DIAMONDS, CardRank.SIX);
        Card c2 = new Card(Suit.SPADES, CardRank.SIX);
        Card c6 = new Card(Suit.DIAMONDS, CardRank.SEVEN);
        userPlayer.getHand().deadwood.add(c0);
        userPlayer.getHand().deadwood.add(c1);
        userPlayer.getHand().deadwood.add(c2);
        userPlayer.getHand().deadwood.add(c6);
        userPlayer.getHand().createBestHand();
        RenderEngine.renderHand(userPlayer.getHand());
        Assert.assertEquals(7, userPlayer.getHand().getDeadWoodValue());
    }

    @Test
    public void testTwoWilds() {
        Player userPlayer = new UserPlayer();
        Card c0 = new Card(Suit.HEARTS, CardRank.THREE);
        c0.setWild(true);
        Card c1 = new Card(Suit.DIAMONDS, CardRank.THREE);
        c1.setWild(true);
        Card c2 = new Card(Suit.SPADES, CardRank.NINE);
        userPlayer.getHand().deadwood.add(c0);
        userPlayer.getHand().deadwood.add(c1);
        userPlayer.getHand().deadwood.add(c2);
        userPlayer.getHand().createBestHand();
        RenderEngine.renderHand(userPlayer.getHand());
        Assert.assertEquals(0, userPlayer.getHand().getDeadWoodValue());
    }

}
