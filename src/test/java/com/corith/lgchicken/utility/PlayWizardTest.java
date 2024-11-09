package com.corith.lgchicken.utility;

import org.junit.Assert;
import org.junit.Test;

public class PlayWizardTest {


    @Test
    public void testPlayloopDefault() {
        final int targetRuns = 1000;
        int run = 0;
        while (run < targetRuns) {
            RenderEngine.disable();
            String output = PlayWizard.playLoop();
            Assert.assertEquals("No game over after " + run + " cycles.", "Game Over.", output);
            run++;
        }
        System.out.println("Ran " + run + " times.");
    }

    @Test
    public void testPlayloopThreeCard() {
        final int targetRuns = 1000;
        int run = 0;
        while (run < targetRuns) {
            RenderEngine.disable();
            PlayWizard.cardLimit = 3;
            String output = PlayWizard.playLoop();
            Assert.assertEquals("No game over after " + run + " cycles.", "Game Over.", output);
//            System.out.println(Ansi.YELLOW+Ansi.HIGH_INTENSITY+"Game Over!"+Ansi.RESET);
            run++;
        }
        System.out.println("Ran " + run + " times.");
    }

    @Test
    public void testPlayloopSevenCard() {
        final int targetRuns = 1000;
        int run = 0;
        while (run < targetRuns) {
            RenderEngine.disable();
            PlayWizard.cardLimit = 7;
            String output = PlayWizard.playLoop();
            Assert.assertEquals("No game over after " + run + " cycles.", "Game Over.", output);
//            System.out.println(Ansi.YELLOW+Ansi.HIGH_INTENSITY+"Game Over!"+Ansi.RESET);
            run++;
        }
        System.out.println("Ran " + run + " times.");
    }

    @Test
    public void testPlayloopThirteenCard() {
        final int targetRuns = 1000;
        int run = 0;
        while (run < targetRuns) {
            RenderEngine.disable();
            PlayWizard.cardLimit = 13;
            String output = PlayWizard.playLoop();
            Assert.assertEquals("No game over after " + run + " cycles.", "Game Over.", output);
//            System.out.println(Ansi.YELLOW+Ansi.HIGH_INTENSITY+"Game Over!"+Ansi.RESET);
            run++;
        }
        System.out.println("Ran " + run + " times.");
    }



}
