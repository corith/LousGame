package com.corith.lgchicken;
import com.corith.lgchicken.utility.Ansi;
import com.corith.lgchicken.utility.PlayWizard;

public class App {
    public static void main( String[] args ) {
        String output = PlayWizard.playLoop();

        String format = Ansi.HIGH_INTENSITY + Ansi.RED + Ansi.BLINK;
        System.out.println(format+output+Ansi.RESET);
    }
}
