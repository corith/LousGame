package com.corith.lgchicken.utility;

public class LousLogger {

    private LousLogger() {

    }

    public static void printRed(String msg) {
        if (RenderEngine.shouldRender()) {
            System.out.println(Ansi.RED+msg+Ansi.RESET);
        }
    }

    public static void printYellow(String msg) {
        if (RenderEngine.shouldRender()) {
            System.out.println(Ansi.YELLOW+msg+Ansi.RESET);
        }
    }

    public static void printGreen(String msg) {
        if (RenderEngine.shouldRender()) {
            System.out.println(Ansi.GREEN+msg+Ansi.RESET);
        }
    }

}