package com.corith.LG313.models.player;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class UserPlayer extends Player {


    @Override
    public void takeTurn() {

    }

    @Override
    public void takeCard() {

    }

    @Override
    public void discard() {

    }

    public String getUserInput(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        String scannerText = scanner.nextLine();
        System.out.println("scanned:  " + scannerText);
        return scannerText;
    }
}
