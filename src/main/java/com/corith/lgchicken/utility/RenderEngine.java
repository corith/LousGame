package com.corith.lgchicken.utility;

import com.corith.lgchicken.models.Card;
import com.corith.lgchicken.models.CardGroup;
import com.corith.lgchicken.models.Hand;

import java.util.stream.Collectors;

public class RenderEngine {


    public void renderCard(Card card) {
        System.out.println(card);
    }


    public static void renderHand(Hand hand) {
        for (CardGroup run : hand.getRuns()) {
            System.out.println(run.getCards().stream()
                    .map(card -> card.prettyPrint(true))
                    .collect(Collectors.joining(", ", "[", "]")));
        }
        for (CardGroup run : hand.getMelds()) {
            System.out.println(run.getCards().stream()
                    .map(card -> card.prettyPrint(true))
                    .collect(Collectors.joining(", ", "[", "]")));
        }

        System.out.println("⚰️"+ hand.getDeadwood().stream().filter(e->!e.isBeingUsed())
                .map(card -> card.prettyPrint(true))
                .collect(Collectors.joining(", ", "[", "]")));

        System.out.println("Deadwood value: " + hand.getDeadWoodValue());
        System.out.println(hand.getDeadwood().stream()
                .map(card -> card.prettyPrint(true))
                .collect(Collectors.joining(", ", "🖐️", "")));
    }
}
