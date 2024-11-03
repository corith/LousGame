package com.corith.lgchicken.models.player;

import com.corith.lgchicken.models.Card;
import com.corith.lgchicken.models.CardDeck;
import com.corith.lgchicken.utility.RenderEngine;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class UserPlayer extends Player {


    @Override
    public void takeTurn() {
        System.out.println("User Player taking turn.");
        // Show User their hand
        // Ask user to if they want to draw or take from discard
        // take the card they choose
        // ask them to discard a card from their hand (or the one they drew)
        // discard the card
        // createBestHand()
        // end turn.
    }

    @Override
    public void takeCard() {

    }

    @Override
    public void discard() {

    }

    public List<String> getUserInput(String prompt) {
        List<String> out = getCardInput();
        return out;
    }

    /**
     * Validates and saves user input in the form of (1-13)(h,d,s,c).
     * <p>
     * Ex: 12h, 2d, 10s, etc.
     * @return [number, letter] (rank, suit).
     */
    public static List<String> getCardInput() {
        Scanner scanner = new Scanner(System.in);
        List<String> result = new ArrayList<>();

        System.out.println("Enter a card value in the format 'number (1-13) followed by a single character (h, d, s, or c)':");

        // Define the regex pattern to match the input format
        Pattern pattern = Pattern.compile("^(1[0-3]|[1-9])([hdsc])$");

        while (true) {
            String input = scanner.nextLine().trim();
            Matcher matcher = pattern.matcher(input);

            // Check if input matches the required format
            if (matcher.matches()) {
                // Add number part and suit character to the result list
                result.add(matcher.group(1)); // The number (1-13)
                result.add(matcher.group(2)); // The suit character (h, d, s, c)
                break; // Valid input received, exit the loop
            } else {
                System.out.println("Invalid input format. Please enter a number (1-13) followed by a single character (h, d, s, or c).");
            }
        }

        scanner.close();
        return result;
    }

    public static void main(String[] args) {
        // userTakeTurn prototype
        UserPlayer userPlayer = new UserPlayer();
        CardDeck deck = new CardDeck();
        userPlayer.shuffleCards(deck.cards);
        List<Player> players = new ArrayList<>();
        players.add(userPlayer);
        userPlayer.deal(deck.cards, players, 3);
        userPlayer.getHand().createBestHand();
        RenderEngine.renderHand(userPlayer.getHand());
        List<String> userChoice = userPlayer.getUserInput("");
        String theChoice = "";

        switch (userChoice.get(1)) {
            case "h":
                theChoice = "HEARTS";
                break;
            case "d":
                theChoice = "DIAMONDS";
                break;
            case "s":
                theChoice = "SPADES";
                break;
            case "c":
                theChoice = "CLUBS";
                break;

        }
        Card cardToRemove = null;
        for (Card card : userPlayer.getHand().getDeadwood()) {
            if (card.getSuit().name().equals(theChoice)) {
                if (card.getCardRank().getRank() == Integer.parseInt(userChoice.get(0))) {
                    System.out.println("Found chosen card");
                    cardToRemove = card;
                }
            }
        }
        if (cardToRemove != null) {
            userPlayer.getHand().getDeadwood().remove(cardToRemove);
            System.out.println("Removed card " + cardToRemove.prettyPrint(true));
        }
        RenderEngine.renderHand(userPlayer.getHand());
    }
}
