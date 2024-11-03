package com.corith.lgchicken.enums;

import com.corith.lgchicken.utility.Ansi;
import lombok.Getter;

@Getter
public enum Suit {
    HEARTS(Ansi.RED+"<3"+Ansi.RESET), DIAMONDS(Ansi.RED+"<*"+Ansi.RESET),
    CLUBS(Ansi.BLACK+Ansi.BACKGROUND_WHITE+"#"+Ansi.RESET), SPADES(Ansi.BACKGROUND_WHITE+Ansi.BLACK+"<"+Ansi.RESET),
    JOKER(Ansi.BACKGROUND_MAGENTA+ "JKR"+Ansi.RESET);

//    HEARTS(Ansi.RED+"❤️"+Ansi.RESET), DIAMONDS(Ansi.RED+"💎"+Ansi.RESET),
//    CLUBS(Ansi.BLACK+Ansi.BACKGROUND_WHITE+"♣️"+Ansi.RESET), SPADES(Ansi.BACKGROUND_WHITE+Ansi.BLACK+"♠️"+Ansi.RESET),
//    JOKER(Ansi.BACKGROUND_MAGENTA+ "JKR"+Ansi.RESET);

    private final String style;

    Suit(String style) {
        this.style = style;
    }


}
