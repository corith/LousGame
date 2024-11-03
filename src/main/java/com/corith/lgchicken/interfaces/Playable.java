package com.corith.lgchicken.interfaces;

import com.corith.lgchicken.models.Card;
import com.corith.lgchicken.models.player.Player;

import java.util.Deque;
import java.util.List;

public interface Playable {

    void takeTurn();
    void takeCard();
    void discard();
    void shuffleCards(Deque<Card> cards);
    void deal(Deque<Card> cards, List<Player> players, int limit);


}
