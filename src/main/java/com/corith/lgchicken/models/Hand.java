package com.corith.lgchicken.models;

import com.corith.lgchicken.enums.CardRank;
import com.corith.lgchicken.enums.GroupType;
import com.corith.lgchicken.enums.Suit;
import com.corith.lgchicken.utility.Ansi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class Hand {

    public Hand() {
       deadwood = new ArrayList<>();
    }

    List<Card> deadwood;
    List<CardGroup> cardGroups = new ArrayList<>();

    List<Card> hearts = new ArrayList<>();
    List<Card> diamonds = new ArrayList<>();
    List<Card> clubs = new ArrayList<>();
    List<Card> spades = new ArrayList<>();
    List<Card> wilds = new ArrayList<>();

    /**
     * Goal: to minimize the amount of points in deadwood.
     * <p>
     * This method creates the best possible hand out of the cards in deadwood.
     * It will  attempt to find all the runs, melds, and to distribute wild cards. Marking
     * cards as "being used" when organizing them.
     */
    public void createBestHand() {
        organize();
        cardGroups = findRuns(hearts);
        cardGroups.addAll(findRuns(spades));
        cardGroups.addAll(findRuns(diamonds));
        cardGroups.addAll(findRuns(clubs));
        cardGroups.addAll(findMelds(deadwood));
        disperseWilds();
    }

    public List<CardGroup> findRuns(List<Card> cards) {
        List<CardGroup> theRuns = new ArrayList<>();
        if (cards.size() < 3) {
            return theRuns;
        }

        CardGroup group = new CardGroup(GroupType.RUN);
        group.cards.add(cards.get(0));
        CardRank previousRank = cards.get(0).getCardRank();

        for (int i = 1; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            CardRank currentRank = currentCard.getCardRank();

            if (currentRank.getRank() == previousRank.getRank() + 1) {
                group.cards.add(currentCard);
            } else {
                if (group.cards.size() >= 3) {
                    theRuns.add(group);
                    for (Card card : group.cards) {
                        card.setBeingUsed(true);
                    }
                }
                // Start a new group
                group = new CardGroup(GroupType.RUN);
                group.cards.add(currentCard);
            }
            previousRank = currentRank;
        }

        // Handle the last group after the loop
        if (group.cards.size() >= 3) {
            theRuns.add(group);
            for (Card card : group.cards) {
                card.setBeingUsed(true);
            }
        }

        return theRuns;
    }

    public List<CardGroup> findMelds(List<Card> cards) {
        Map<Integer, CardGroup> rankGroups = new HashMap<>();
        for (Card card : cards) {
            if (card.isBeingUsed() || card.isWild()) {
                // Skip cards that are used. Wilds too, b/c no reason to make a meld out of all wilds...
                continue;
            }
            int rank = card.getCardRank().getRank();
            CardGroup group = rankGroups.computeIfAbsent(rank, k -> new CardGroup(GroupType.MELD));
            group.cards.add(card);
        }

        List<CardGroup> theMelds = new ArrayList<>();
        for (CardGroup group : rankGroups.values()) {
            if (group.cards.size() >= 3) {
                for (Card card : group.cards) {
                    card.setBeingUsed(true);
                }
                theMelds.add(group);
            }
        }
        return theMelds;
    }

    public void disperseWilds() {
        // Collect all unused, non-wild cards (deadwood)
        List<Card> unusedNonWildCards = deadwood.stream()
                .filter(card -> !card.isBeingUsed() && !card.isWild())
                .collect(Collectors.toList());

        List<CardGroup> candidateGroups = new ArrayList<>();

        // Find 'almost' runs for each suit
        List<Suit> suits = Arrays.asList(Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS, Suit.SPADES);
        for (Suit suit : suits) {
            List<Card> suitCards = unusedNonWildCards.stream()
                    .filter(card -> card.getSuit() == suit)
                    .sorted(Comparator.comparingInt(card -> card.getCardRank().getRank()))
                    .collect(Collectors.toList());

            for (int i = 0; i < suitCards.size() - 1; i++) {
                Card card1 = suitCards.get(i);
                Card card2 = suitCards.get(i + 1);

                if (card2.getCardRank().getRank() == card1.getCardRank().getRank() + 1) {
                    // Found an 'almost' run
                    CardGroup group = new CardGroup(GroupType.RUN);
                    group.cards.add(card1);
                    group.cards.add(card2);
                    group.points = card1.getCardRank().getRank() + card2.getCardRank().getRank();
                    candidateGroups.add(group);
                }
            }
        }

        // Find 'almost' melds
        Map<Integer, List<Card>> rankGroups = unusedNonWildCards.stream()
                .collect(Collectors.groupingBy(card -> card.getCardRank().getRank()));

        for (Map.Entry<Integer, List<Card>> entry : rankGroups.entrySet()) {
            List<Card> cardsOfSameRank = entry.getValue();
            if (cardsOfSameRank.size() == 2) {
                // Found an 'almost' meld
                CardGroup group = new CardGroup(GroupType.MELD);
                group.cards.addAll(cardsOfSameRank);
                group.points = cardsOfSameRank.get(0).getCardRank().getRank() * 2; // sum of ranks
                candidateGroups.add(group);
            }
        }

        // Sort candidate groups by points in descending order
        candidateGroups.sort((g1, g2) -> Integer.compare(g2.points, g1.points));

        // Assign wild cards to the highest-pointed groups
        Iterator<CardGroup> groupIterator = candidateGroups.iterator();
        while (!wilds.isEmpty() && groupIterator.hasNext()) {
            CardGroup group = groupIterator.next();
            if (group.cards.stream().anyMatch(Card::isBeingUsed)) {
                group.cards.removeIf(Card::isBeingUsed);
            }
            if (group.cards.size() == 2) {
                // Assign a wild card
                Card wildCard = wilds.remove(0);
                group.cards.add(wildCard);
                // Optionally, adjust points if wild cards have a score value
                // group.points += wildCard.getCardRank().getRank();
                // Mark cards as being used
                for (Card card : group.cards) {
                    card.setBeingUsed(true);
                }
                cardGroups.add(group);
                // Remove the group from candidateGroups
                groupIterator.remove();


            }
        }

        // Takes care of unused wilds if any still remain.
        for (Card card : wilds) {
            if (!card.isBeingUsed()) {

                long unusedWilds = wilds.stream().filter(card1 -> !card1.isBeingUsed()).count();
                if (!cardGroups.isEmpty() && unusedWilds < 2) {
                    card.setBeingUsed(true);
                    cardGroups.get(0).cards.add(card);
                } else {
                    if (unusedWilds >= 2) {
                        List<Card> l = deadwood.stream().filter(card1 -> !card1.isBeingUsed()).sorted(Comparator.comparing(Card::getCardRank)).collect(Collectors.toList());
                        CardGroup group = new CardGroup(GroupType.MELD);
                        group.cards.add(l.get(l.size()-1));
                        l.get(l.size()-1).setBeingUsed(true);
                        for (Card wildCard: wilds) {
                            if (!wildCard.isBeingUsed()) {
                                group.cards.add(wildCard);
                                wildCard.setBeingUsed(true);
                            }
                        }
                        cardGroups.add(group);
                        break;
                    }

                }


            }
        }
    }

    /**
     * Sorts deadwood by ascending card rank.
     */
    public void sortDeadwood() {
        deadwood.sort(Comparator.comparing(Card::getCardRank));
    }

    public int getDeadWoodValue() {
        int deadwoodValue = 0;
        for (Card card : deadwood) {
            if (!card.isBeingUsed()) {
                deadwoodValue = deadwoodValue + card.getScoreValue();
            }
        }
        return deadwoodValue;
    }

    /**
     * <p>
     *     Goes through deadwood and copies the cards into "category" lists.
     *     <br><br>
     *     Categories: Wild, Hearts, Diamonds, Spades, & Clubs.
     *     <br><br>
     *     Sets all cards isBeingUsed to FALSE.
     * <p>
     */
    private void organize() {
        wilds    = extractWildCards();
        hearts   = extractAllCardsWithSuit(Suit.HEARTS);
        diamonds = extractAllCardsWithSuit(Suit.DIAMONDS);
        clubs    = extractAllCardsWithSuit(Suit.CLUBS);
        spades   = extractAllCardsWithSuit(Suit.SPADES);

        hearts.sort(Comparator.comparing(Card::getCardRank));
        clubs.sort(Comparator.comparing(Card::getCardRank));
        diamonds.sort(Comparator.comparing(Card::getCardRank));
        spades.sort(Comparator.comparing(Card::getCardRank));
        sortDeadwood();
        System.out.println(Ansi.RED+"Hearts: " + hearts.size() + " diamonds: " + diamonds.size() + " clubs: " + clubs.size() + " spades: " + spades.size() + " wilds: " + wilds.size()+Ansi.RESET);
        for (Card card : deadwood) {
            card.setBeingUsed(false);
        }
    }

    /**
     * Finds all the cards from deadwood that have matching Suit & returns them in a List.
     * Any wilds that it finds are added the wilds list instead.
     * <p>
     * Does not remove any cards from deadwood.
     * @param suit suit to match
     * @return List with matching suit cards.
     */
    private List<Card> extractAllCardsWithSuit(Suit suit) {
        List<Card> returnList = new ArrayList<>();
        for (Card card : deadwood) {
          if (card.getSuit().equals(suit) && !card.isWild()) {
                returnList.add(card);
            }
        }
        return returnList;
    }

    private List<Card> extractWildCards() {
        return deadwood.stream().filter(Card::isWild).collect(Collectors.toList());
    }

}