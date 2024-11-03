package com.corith.lgchicken.utility;

import com.corith.lgchicken.models.CardDeck;
import com.corith.lgchicken.models.player.ComputerPlayer;
import com.corith.lgchicken.models.player.Player;
import com.corith.lgchicken.models.player.UserPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayWizard {
    static int cardLimit = 13;

    public static String playLoop() {
        System.out.println(Ansi.CYAN+"Lous Game!"+Ansi.RESET );
        Player player = new UserPlayer();
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

        CardDeck gameDeck = new CardDeck();

        player.shuffleCards(gameDeck.cards);
        player.deal(gameDeck.cards, players, cardLimit);

        for (Player p : players) {
            System.out.println(p.getName());
            p.getHand().createBestHand();
            RenderEngine.renderHand(p.getHand());
        }

        System.out.println("\n\nDiscard pile: " + gameDeck.cards.pop().prettyPrint(true));
        if (gameDeck.cards.peek() != null) {
            System.out.println("Peek Draw card: " + gameDeck.cards.peek().prettyPrint(true));
        }

        return "Game Over.";
    }
}
