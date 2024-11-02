package com.corith.LG313.models;

import com.corith.LG313.models.player.ComputerPlayer;
import com.corith.LG313.models.player.Player;
import com.corith.LG313.models.player.UserPlayer;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class HandTest {



    @Test
    public void testUserHand() {
        Player userPlayer = new UserPlayer();
        CardDeck standardDeck = new CardDeck();
        for (int i = 0; i<8; i++) {
            userPlayer.getHand().getDeadwood().add(standardDeck.cards.pop());
        }
        Assert.assertEquals(52 - 8, standardDeck.cards.size());
        Assert.assertEquals(userPlayer.getHand().deadwood.size(), 8);
    }


    @Test
    public void testDeal() {
        Player userPlayer = new UserPlayer();
        Player userPlayer1 = new UserPlayer();
        Player userPlayer2 = new UserPlayer();
        CardDeck standardDeck = new CardDeck();
        userPlayer.shuffleCards(standardDeck.cards);

        List<Player> players = new ArrayList<>();
        players.add(userPlayer);
        players.add(userPlayer1);
        players.add(userPlayer2);
        userPlayer.deal(standardDeck.cards, players, 13);

        Assert.assertEquals(13, userPlayer.getHand().deadwood.size());
        Assert.assertEquals(13, userPlayer1.getHand().deadwood.size());
        Assert.assertEquals(13, userPlayer2.getHand().deadwood.size());
        Assert.assertEquals(13, standardDeck.cards.size());
    }

    @Test
    public void testOverDeal() {
        Player userPlayer = new UserPlayer();
        Player userPlayer1 = new UserPlayer();
        Player userPlayer2 = new UserPlayer();
        CardDeck standardDeck = new CardDeck();
        userPlayer.shuffleCards(standardDeck.cards);

        List<Player> players = new ArrayList<>();
        players.add(userPlayer);
        players.add(userPlayer1);
        players.add(userPlayer2);
        try {
            userPlayer.deal(standardDeck.cards, players, 63);
            Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            Assert.assertEquals("Ran out of cards while dealing", e.getMessage());
        }
    }

    @Test
    public void testNoDuplicateCardsInHandsOrDeck() {
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

}
