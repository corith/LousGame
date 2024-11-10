package com.corith.lgchicken.models.player;

import com.corith.lgchicken.models.Card;
import com.corith.lgchicken.models.PlayPlate;
import com.corith.lgchicken.utility.Ansi;
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
    private static Scanner scanner = new Scanner(System.in);
    private static final String drawChar = "d";
    private static final String discardChar = "p";

    @Override
    public void takeTurn(PlayPlate playPlate) {
        // Show User their hand
        // Ask user to if they want to draw or take from discard
        System.out.println(Ansi.HIGH_INTENSITY+Ansi.MAGENTA+"Draw card ("+drawChar+") or pick ("+discardChar+") up from discard pile?"+Ansi.RESET);
        String choice;
        do {
            choice = scanner.nextLine();
        } while (!choice.trim().equalsIgnoreCase(drawChar) && !choice.trim().equalsIgnoreCase(discardChar));

        if (choice.equalsIgnoreCase(drawChar)) {
            // draw card from deck.
            Card drawCard = playPlate.drawFromDeck();
            System.out.println("Draw Card: " + drawCard.prettyPrint(true));
            getHand().getDeadwood().add(drawCard);
        } else if (choice.equalsIgnoreCase(discardChar)) {
            // take from discard pile.
            getHand().getDeadwood().add(playPlate.getDiscardCards().pop());
        }

        // discard
        Card discardedCard = discard();
        playPlate.getDiscardCards().push(discardedCard);
        System.out.println(discardedCard.prettyPrint(true));
    }

    @Override
    public void takeCard() {

    }

    @Override
    public Card discard() {
        Card discardCard;
        do {
            List<String> userInput = getUserInput("Which card do you want to discard?");
            discardCard = getDiscardCardFromUserInput(userInput);
        } while(discardCard == null);
        getHand().getDeadwood().remove(discardCard);
        return discardCard;
    }

    public List<String> getUserInput(String prompt) {
        return getCardInput();
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

        System.out.println(Ansi.HIGH_INTENSITY+Ansi.MAGENTA+"Choose Discard."+Ansi.CYAN+"\nFormat:"+Ansi.RESET+" number (1-13) followed by a suit char (h, d, s, or c).\n"+Ansi.CYAN+"Ex: 7h, 12d, 1c, 3s"+Ansi.RESET);

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
        return result;
    }

    private Card getDiscardCardFromUserInput(List<String> input) {
        String theChoice = "";

        switch (input.get(1)) {
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
        for (Card card : getHand().getDeadwood()) {
            if (card.getSuit().name().equals(theChoice)) {
                if (card.getCardRank().getRank() == Integer.parseInt(input.get(0))) {
                    System.out.println("Found chosen card");
                    cardToRemove = card;
                }
            }
        }
        return cardToRemove;
    }
}
