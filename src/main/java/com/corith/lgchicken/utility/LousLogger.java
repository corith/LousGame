package com.corith.lgchicken.utility;

public class LousLogger {

    private LousLogger() {

    }

    public static void printRed(String msg) {
        System.out.println(Ansi.RED+msg+Ansi.RESET);
    }

    public static void printYellow(String msg) {
        System.out.println(Ansi.YELLOW+msg+Ansi.RESET);
    }

    public static void printGreen(String msg) {
        System.out.println(Ansi.GREEN+msg+Ansi.RESET);
    }

}
