package com.corith.LG313.models;

import com.corith.LG313.enums.GroupType;
import com.corith.LG313.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
@AllArgsConstructor
public class Hand {
    Logger logger = Logger.getLogger("Hand");

    public Hand() {
       deadwood = new ArrayList<>();
    }

    List<Card> deadwood;
    List<CardGroup> sortedCards = new ArrayList<>();

    List<Card> hearts = new ArrayList<>();
    List<Card> diamonds = new ArrayList<>();
    List<Card> clubs = new ArrayList<>();
    List<Card> spades = new ArrayList<>();

    List<Card> wilds = new ArrayList<>();
    List<Card> misc = new ArrayList<>();

    public int getDeadWoodValue() {
        int deadwoodValue = 0;
        for (Card card : deadwood) {
            deadwoodValue = deadwoodValue + card.getScoreValue();
        }
        return deadwoodValue;
    }

    public void organize() {
        hearts = getAllMatchingSuits(Suit.HEARTS);
        diamonds = getAllMatchingSuits(Suit.DIAMONDS);
        clubs = getAllMatchingSuits(Suit.CLUBS);
        spades = getAllMatchingSuits(Suit.SPADES);
        System.out.println("Hearts: " + hearts.size() + " diamonds: " + diamonds.size() + " clubs: " + clubs.size() + " spades: " + spades.size() + " wilds: " + wilds.size());
        deadwood.sort(Comparator.comparing(Card::getCardRank));
    }

    //TODO: create logic to remove as many points from deadwood score as possible
    public void createBestHand() {

    }

    private List<Card> getAllMatchingSuits(Suit suit) {
        List<Card> returnList = new ArrayList<>();
        for (Card card : deadwood) {
            if (card.isWild() && !wilds.contains(card)) {
                wilds.add(card);
            } else if (card.getSuit().equals(suit)) {
                returnList.add(card);
            }
        }
        return returnList;
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class CardGroup {
    GroupType groupType;
    List<Card> cards;
    int points;
    double weight;
}