import java.util.Scanner;

/**
 * A class used to handle greeting a real world Player.
 */
public class Greeting {

    public static Scanner lou = new Scanner(System.in);

    /**
     * Prompts user with a welcome message and asks if they are ready to play the game.
     * This is called by handleRealUser() below.
     * @return true if answer is yes and false if answer is no.
     */
    private static boolean userGreeting() {
        System.out.println("Hello! welcome to G Lous Stupid Game!");
        System.out.println("If you don't know the rules....");
        System.out.println(Ansi.RED + "\nGOOGLE " + Ansi.RESET + "them. Just search \"3-13 card game!\"");
        System.out.println("If you are ready to begin enter \"yes\" or enter \"no\"!");

        String commence;
        lou = new Scanner(System.in);

        while (lou.hasNextLine()) {
            commence = lou.nextLine();
            if (commence.equalsIgnoreCase("yes") || commence.equalsIgnoreCase("y"))
                return true;
            else if (commence.equalsIgnoreCase("no"))
                return false;
            else
                System.out.println("Enter \"yes\" to play and \"no\" to quit");
        }
        return false;
    }

    /**
     * This is called at the beginning of LousReady and calls
     * @return true if user want's to play and false if they do not
     */
    public static boolean handleRealUser() {
        if (!Greeting.userGreeting()) {
            System.out.println("Okay.....goodbye.");
            return false;
        } else {
            System.out.println("Okay sweet! Let's begin! Here is your hand!");
            return true;
        }
    }
}
