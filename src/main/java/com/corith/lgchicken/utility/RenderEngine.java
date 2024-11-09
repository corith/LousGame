package com.corith.lgchicken.utility;

import com.corith.lgchicken.enums.GroupType;
import com.corith.lgchicken.models.Card;
import com.corith.lgchicken.models.CardGroup;
import com.corith.lgchicken.models.Hand;
import com.corith.lgchicken.models.PlayPlate;

import java.util.stream.Collectors;

public class RenderEngine {
    private static boolean render = true;

    public static void disable() {
        render = false;
    }

    public static void enable() {
        render = true;
    }

    public static boolean shouldRender() {
        return render;
    }


    public void renderCard(Card card) {
        System.out.println(card);
    }


    public static void renderHand(Hand hand) {
        if (!render) {
            return;
        }
        for (CardGroup cardGroup : hand.getCardGroups()) {
            System.out.println((cardGroup.getGroupType().equals(GroupType.RUN) ? "r" : "m")+cardGroup.getCards().stream()
                    .map(card -> card.prettyPrint(true))
                    .collect(Collectors.joining(", ", "[", "]")));
        }

        System.out.println("⚰️"+ hand.getDeadwood().stream().filter(e->!e.isBeingUsed())
                .map(card -> card.prettyPrint(true))
                .collect(Collectors.joining(", ", "[", "]")));

        System.out.println("Deadwood value: " + hand.getDeadWoodValue());
        System.out.println(hand.getDeadwood().stream()
                .map(card -> card.prettyPrint(true))
                .collect(Collectors.joining(", ", "🖐️ ", "")));
    }

    public static void renderPlayPlate(PlayPlate playPlate) {
        if (!render) {
            return;
        }
        System.out.println();
        System.out.println(Ansi.HIGH_INTENSITY+Ansi.MAGENTA+"|_|    " + playPlate.getDiscardCards().peek().prettyPrint(true));
        System.out.println();
    }
}
