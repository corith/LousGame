package com.corith.lgchicken.utility;

import com.corith.lgchicken.models.CardDeck;
import com.corith.lgchicken.models.PlayPlate;
import com.corith.lgchicken.models.player.ComputerPlayer;
import com.corith.lgchicken.models.player.Player;
import com.corith.lgchicken.models.player.UserPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PlayWizard {
    public static int cardLimit = 5;

    public static String playLoop() {
        if (RenderEngine.shouldRender()) {
            System.out.println(Ansi.CYAN+"Lous Game!"+Ansi.RESET );
        }
        Player player = new ComputerPlayer();
        player.setDealer(true);
        player.setName("Cory Sebastian");
        Player cpuPlayer0 = new ComputerPlayer();
        cpuPlayer0.setName("CPU 0");
        Player cpuPlayer1 = new ComputerPlayer();
        cpuPlayer1.setName("CPU 1");

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(cpuPlayer0);
        players.add(cpuPlayer1);

        CardDeck gameDeck = new CardDeck(cardLimit);

        cpuPlayer1.shuffleCards(gameDeck.cards);
        cpuPlayer1.deal(gameDeck.cards, players, cardLimit);

        PlayPlate playPlate = new PlayPlate(gameDeck);
        playPlate.initializeDiscardPile();

        while(cardLimit < 14) {
            try {
                for (Player p : players) {
                    if (playPlate.getDeck().cards.isEmpty()) {
                        LousLogger.printYellow(Ansi.BLINK+"Deck is out of cards"+Ansi.RESET);
                        playPlate.redistributeDiscards();
                    }
                    if (RenderEngine.shouldRender()) {
                        System.out.println("\n\n\n\n");
                        System.out.println(p.getName() + "'s turn!");
                    }
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            // check if we should go on to next iteration or b
            for (Player p : players) {
                if (p.getHand().getDeadWoodValue() == 0) {
                    LousLogger.printGreen("\n\n\n");
                    LousLogger.printGreen(Ansi.BLINK+"------------------- Winner -------------------"+Ansi.RESET);
                    LousLogger.printGreen(Ansi.BLINK+p.getName()+ " has gone out."+Ansi.RESET);
                    LousLogger.printGreen(Ansi.BLINK+"----------------------------------------------"+Ansi.RESET);
                    RenderEngine.renderHand(p.getHand());
                    cardLimit = 14;
                    break;
                }
            }
            if (RenderEngine.shouldRender()) {
                System.out.println("Play plate: " + playPlate.getDeck().cards.size());
            }
            if (playPlate.getShuffleCount() > 10) {
                return "shuffle count > 10";
            }
        }
        if (RenderEngine.shouldRender()) {
            System.out.println("Cycled playplate: "+ playPlate.getShuffleCount());
        }
        return "Game Over.";
    }
}
