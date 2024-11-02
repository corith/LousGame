package com.corith.LG313.models;

import com.corith.LG313.enums.CardRank;
import com.corith.LG313.enums.Suit;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;


public class CardDeck {

    public Deque<Card> cards;

    public CardDeck() {
        this.cards = getCleanDeck();
    }

    private Deque<Card> getCleanDeck() {
        Deque<Card> freshCards = new ArrayDeque<>();
        for (Suit suit : Suit.values()) {
            if (!suit.equals(Suit.JOKER)) {
                for (CardRank rank : CardRank.values()) {
                    if (!rank.equals(CardRank.JOKER)) {
                        Card cardToAdd = new Card(suit, rank);
                        cardToAdd.setScoreValue(cardToAdd.getCardRank().getRank());
                        freshCards.add(cardToAdd);
                    }
                }
            }
        }
        return freshCards;
    }

    public Card getTopCard() {
        try {
            return cards.pop();
        } catch (EmptyStackException e) {
            throw new EmptyStackException();
        }
    }

}
