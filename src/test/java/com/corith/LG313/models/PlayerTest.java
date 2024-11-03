package com.corith.LG313.models;

import com.corith.LG313.models.player.ComputerPlayer;
import com.corith.LG313.models.player.Player;
import com.corith.LG313.models.player.UserPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PlayerTest {



    @Test
    public void testPlayerTakeTurn() {
        Player userPlayer = new UserPlayer();
        Player computerPlayer = new ComputerPlayer();
        List<Player> players = new ArrayList<>();
        players.add(userPlayer);
        players.add(computerPlayer);
        for (Player player : players) {
            player.takeTurn();
        }
    }


    @Test
    public void testPlayerShuffleDeck() {
        Player userPlayer = new UserPlayer();
        CardDeck standardDeck = new CardDeck();
        CardDeck testDeck = new CardDeck();

        userPlayer.shuffleCards(testDeck.cards);
        Assert.assertNotEquals(standardDeck.cards, testDeck.cards); // The order should differ
        Assert.assertTrue(testDeck.cards.containsAll(standardDeck.cards));
        Assert.assertEquals(testDeck.cards.size(), standardDeck.cards.size()); // Sizes should match
    }


    @Test
    public void testPlayerDeal() {
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
    public void testPlayerOverDeal() {
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


}
