package com.corith.lgchicken.utility;

import com.corith.lgchicken.models.CardDeck;
import com.corith.lgchicken.models.PlayPlate;
import com.corith.lgchicken.models.player.ComputerPlayer;
import com.corith.lgchicken.models.player.Player;

import java.util.*;

public class PlayWizard {
    public static int cardLimit = 3;
    public static int roundLimit = 14;

    public static String playLoop() {
        if (RenderEngine.shouldRender()) {
            System.out.println(Ansi.CYAN+"Lous Game!"+Ansi.RESET );
        }
        List<Player> players = setUpPlayers("Cory Sebastian");
        CardDeck gameDeck = new CardDeck(cardLimit);
        getDealer(players).shuffleCards(gameDeck.cards);
        getDealer(players).deal(gameDeck.cards, players, cardLimit);
        PlayPlate playPlate = new PlayPlate(gameDeck);
        playPlate.initializeDiscardPile();
        // Ready to go
        while(cardLimit < roundLimit) {
            if (hasWinner(players)) {
                cardLimit+=1;
                // Setup for next round.
                for (Player p: players) {
                    p.setScore(p.getScore() + p.deadwoodScore());
                    if (p.getHand().getDeadWoodValue() == 0) {
                        LousLogger.printGreen("\n\n\n");
                        LousLogger.printGreen(Ansi.BLINK+"------------------- 0 D-wood -------------------"+Ansi.RESET);
                        LousLogger.printGreen(Ansi.BLINK+p.getName()+ " has zero wood..."+Ansi.RESET);
                        LousLogger.printGreen(Ansi.BLINK+"------------------------------------------------"+Ansi.RESET);
                        RenderEngine.renderHand(p.getHand());
                    }
                    p.clearHand();
                }
                switchDealer(players);
                gameDeck = new CardDeck(cardLimit);
                getDealer(players).shuffleCards(gameDeck.cards);
                getDealer(players).deal(gameDeck.cards, players, cardLimit);
                playPlate = new PlayPlate(gameDeck);
                playPlate.initializeDiscardPile();
//                System.out.println("New Dealer = " + getDealer(players).getName());
            }
            // take turns
            try {
                for (Player p : players) {
                    if (playPlate.getDeck().cards.isEmpty()) {
                        LousLogger.printYellow(Ansi.BLINK+"Deck is out of cards"+Ansi.RESET);
                        playPlate.redistributeDiscards();
                    }
                    RenderEngine.renderTurnTitle(p.getName());
                    RenderEngine.renderPlayPlate(playPlate);
                    p.getHand().createBestHand();
                    RenderEngine.renderHand(p.getHand());
                    p.takeTurn(playPlate);
                    p.getHand().createBestHand();
                    RenderEngine.renderHand(p.getHand());
                    RenderEngine.renderPlayPlate(playPlate);
                }
            } catch (NoSuchElementException e) {
                if (playPlate.getDeck().cards.isEmpty()) {
                    LousLogger.printRed(Ansi.BLINK+"Deck is out of cards"+Ansi.RESET);
                    playPlate.redistributeDiscards();
                }
            }
            // CPU is too stupid to build a winning hand this round.
            if (playPlate.getShuffleCount() > 10) {
                return "shuffle count > 10";
            }
        }
        RenderEngine.renderFinalScores(players);
        return "Game Over.";
    }

    private static void switchDealer(List<Player> players) {
        int dealerIndex = -1;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isDealer()) {
                players.get(i).setDealer(false);
                dealerIndex = i;
                break;
            }
        }
        int nextDealerIndex = (dealerIndex + 1) % players.size();
        players.get(nextDealerIndex).setDealer(true);
    }

    private static Player getDealer(List<Player> players) {
        for (Player p : players) {
            if (p.isDealer()) {
                return p;
            }
        }
        return new ComputerPlayer();
    }

    private static List<Player> setUpPlayers(String name) {
        Player player = new ComputerPlayer();
        player.setDealer(true);
        player.setName(name);

        Player cpuPlayer0 = new ComputerPlayer();
        cpuPlayer0.setName("CPU 0");

        Player cpuPlayer1 = new ComputerPlayer();
        cpuPlayer1.setName("CPU 1");

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(cpuPlayer0);
        players.add(cpuPlayer1);
        return players;
    }

    private static boolean hasWinner(List<Player> players) {
        for (Player p : players) {
            if (p.deadwoodScore() == 0) {
                return true;
            }
        }
        return false;
    }
}
