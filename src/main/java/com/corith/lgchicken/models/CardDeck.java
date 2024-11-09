package com.corith.lgchicken.models;

import com.corith.lgchicken.enums.CardRank;
import com.corith.lgchicken.enums.Suit;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.stream.Collectors;


public class CardDeck {

    public Deque<Card> cards;

    public CardDeck() {
        this.cards = getCleanDeck();
    }

    public CardDeck(int wildRank) {
        this.cards = getCleanDeck();
        for (Card c : cards) {
            if (c.getCardRank().getRank() == wildRank) {
                c.setWild(true);
            }
        }
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

    public int countWildsInDeck() {
        return (int) cards.stream().filter(Card::isWild).count();
    }

}
