package com.corith.lgchicken.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayPlate {
    CardDeck deck;
    Deque<Card> discardCards = new ArrayDeque<>();

    private static int shuffleCount = 0;

    public PlayPlate(CardDeck deck) {
        this.deck = deck;
    }

    public Card drawFromDeck() {
        return deck.cards.pop();
    }

    public void redistributeDiscards() {
        deck.cards.addAll(discardCards);
        discardCards = new ArrayDeque<>();
        discardCards.add(deck.cards.pop());
        shuffleCount+=1;
    }

    public int getShuffleCount() {
        return shuffleCount;
    }
}
