package com.corith.lgchicken.models.player;

public class ComputerPlayer extends Player {
    @Override
    public void takeTurn() {
        System.out.println("Take computer turn");
    }

    @Override
    public void takeCard() {
        System.out.println("Pick up card computer");
    }

    @Override
    public void discard() {
        System.out.println("Discard card computer");
    }

}
