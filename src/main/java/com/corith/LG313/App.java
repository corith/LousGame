package com.corith.LG313;
import com.corith.LG313.utility.Ansi;
import com.corith.LG313.utility.PlayWizard;

public class App {
    public static void main( String[] args ) {
        String output = PlayWizard.playLoop();

        String format = Ansi.HIGH_INTENSITY + Ansi.RED + Ansi.BLINK;
        System.out.println(format+output+Ansi.RESET);
    }
}
