package com.corith.lgchicken.models.player;

import com.corith.lgchicken.models.Card;
import com.corith.lgchicken.models.PlayPlate;
import com.corith.lgchicken.utility.Ansi;
import com.corith.lgchicken.utility.LousLogger;
import com.corith.lgchicken.utility.RenderEngine;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerPlayer extends Player {

    @Override
    public void takeTurn(PlayPlate playPlate) {

        boolean chooseFromDiscard = willCardBeUsed(playPlate.getDiscardCards().peek());
        Card discardedCard = null;
        if (chooseFromDiscard) {
            LousLogger.printRed("Choosing discard card " + playPlate.getDiscardCards().peek().prettyPrint(true));
            Card discardCard = playPlate.getDiscardCards().pop();
            discardCard.setBeingUsed(true);
            getHand().getDeadwood().add(discardCard);
            discardedCard = discard();
            playPlate.getDiscardCards().push(discardedCard);
            LousLogger.printRed("Discarded card " + discardedCard.prettyPrint(true));
        } else {
            Card drawCard = playPlate.drawFromDeck();
            LousLogger.printRed("Drawing from deck " + drawCard.prettyPrint(true) + (drawCard.isWild() ? "wild": ""));
            boolean willBeUsed = willCardBeUsed(drawCard);
            if (willBeUsed) {
                LousLogger.printRed("Will use " + drawCard.prettyPrint(true));
                drawCard.setBeingUsed(true);
                getHand().getDeadwood().add(drawCard);
                discardedCard = discard();

                playPlate.getDiscardCards().push(discardedCard);
                LousLogger.printRed("Discarded card " + discardedCard.prettyPrint(true));
            } else {
                LousLogger.printRed(Ansi.HIGH_INTENSITY+"Will not use draw card. Putting it in discard pile."+Ansi.RESET);
                getHand().getDeadwood().removeIf(e -> e.prettyPrint(true).equals(drawCard.prettyPrint(true)));
                playPlate.getDiscardCards().push(drawCard);
            }
        }
    }

    @Override
    public void takeCard() {
        System.out.println("Pick up card computer");
    }

    @Override
    public Card discard() {
        List<Card> cardsToDiscard = getHand().getDeadwood().stream().sorted(Comparator.comparing(Card::getCardRank)).collect(Collectors.toList());

        Card discardCard = null;
        for (Card c : cardsToDiscard) {
            if (!c.isBeingUsed()) {
                discardCard = c;
                break;
            }
        }
        if (discardCard == null) {
            discardCard = cardsToDiscard.remove(cardsToDiscard.size() - 1);
        } else {
            cardsToDiscard.remove(discardCard);
        }
        getHand().getDeadwood().clear();
        getHand().getDeadwood().addAll(cardsToDiscard);
        return discardCard;
    }

    public boolean willCardBeUsed(Card card) {
        getHand().getDeadwood().add(card);
        getHand().createBestHand();
        if (card.isBeingUsed()) {
            if (RenderEngine.shouldRender())
                System.out.println("Hand with card used:");
            RenderEngine.renderHand(getHand());
            getHand().getDeadwood().remove(card);
            card.setBeingUsed(false);
            return true;
        }
        getHand().getDeadwood().remove(card);
        return false;
    }


}
