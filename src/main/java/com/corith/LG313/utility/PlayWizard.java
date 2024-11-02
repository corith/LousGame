package com.corith.LG313.utility;

import com.corith.LG313.enums.CardRank;
import com.corith.LG313.models.CardDeck;
import com.corith.LG313.models.player.ComputerPlayer;
import com.corith.LG313.models.player.Player;
import com.corith.LG313.models.player.UserPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//        while(cardLimit < 14) {
//            cardLimit++;
//        }

        for (Player p : players) {
            System.out.println(p.getName());
            p.getHand().organize();
            p.getHand().createBestHand();
            System.out.println("Points in hand: " + p.getHand().getDeadWoodValue());
            System.out.println(p.getHand().getDeadwood().stream()
                    .map(card -> card.prettyPrint(true))
                    .collect(Collectors.joining(", ", "🖐️", "")));
        }

        System.out.println("\n\nFirst Card in discard pile: " + gameDeck.cards.pop().prettyPrint(true));
        if (gameDeck.cards.peek() != null) {
            System.out.println("Sneak peak at draw card: " + gameDeck.cards.peek().prettyPrint(true));
        }

        return "Game Over.";
    }
}
