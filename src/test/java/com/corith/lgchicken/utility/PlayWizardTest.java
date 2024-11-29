package com.corith.lgchicken.utility;

import org.junit.Assert;
import org.junit.Test;

public class PlayWizardTest {

    @Test
    public void testPlayloopThreeCard() {
        RenderEngine.disable();
        final int targetRuns = 1000;
        int run = 0;
        while (run < targetRuns) {
            PlayWizard.cardLimit = 3;
            String output = PlayWizard.playLoop();
            Assert.assertEquals("No game over after " + run + " cycles.", "Game Over.", output);
            run++;
        }
        System.out.println("Ran " + run + " times.");
    }

    @Test
    public void testPlayloopSevenCard() {
        RenderEngine.disable();
        final int targetRuns = 1000;
        int run = 0;
        while (run < targetRuns) {
            PlayWizard.cardLimit = 7;
            String output = PlayWizard.playLoop();
            Assert.assertEquals("No game over after " + run + " cycles.", "Game Over.", output);
            run++;
        }
        System.out.println("Ran " + run + " times.");
    }

    @Test
    public void testPlayloopThirteenCard() {
        RenderEngine.disable();
        final int targetRuns = 1000;
        int run = 0;
        while (run < targetRuns) {
            PlayWizard.cardLimit = 13;
            String output = PlayWizard.playLoop();
            Assert.assertEquals("No game over after " + run + " cycles.", "Game Over.", output);
            run++;
        }
        System.out.println("Ran " + run + " times.");
    }

}
