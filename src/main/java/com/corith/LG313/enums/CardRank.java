package com.corith.LG313.enums;

import lombok.Getter;

@Getter
public enum CardRank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(11), QUEEN(12), KING(13), JOKER(69);

    private final int rank;

    CardRank(int value) {
        this.rank = value;
    }


}
