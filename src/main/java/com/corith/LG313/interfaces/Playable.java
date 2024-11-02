package com.corith.LG313.interfaces;

import com.corith.LG313.models.Card;
import com.corith.LG313.models.player.Player;

import java.util.Deque;
import java.util.List;

public interface Playable {

    void takeTurn();
    void takeCard();
    void discard();
    void shuffleCards(Deque<Card> cards);
    void deal(Deque<Card> cards, List<Player> players, int limit);


}
