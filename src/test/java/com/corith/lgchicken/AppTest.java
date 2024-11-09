package com.corith.lgchicken;

import com.corith.lgchicken.utility.Ansi;
import com.corith.lgchicken.utility.PlayWizard;
import com.corith.lgchicken.utility.RenderEngine;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void appTest() {
        RenderEngine.disable();
        int run = 0;
        int targetRuns = 1;
        while (run < targetRuns) {
            String message = "Cycled " + run + " times.";
            Assert.assertEquals(message,"Game Over.", PlayWizard.playLoop());
            Assert.assertEquals(14, PlayWizard.cardLimit);
            run++;
            PlayWizard.cardLimit = 3;
        }
        System.out.println(Ansi.HIGH_INTENSITY+Ansi.MAGENTA+"Cycled " + run + " times."+Ansi.RESET);
    }

}
