import java.util.Scanner;

public class Greeting {

  public static Scanner lou = new Scanner(System.in);

  public static boolean userGreeting() {
    System.out.println("Hello! welcome to G Lous Stupid Game!");
    System.out.println("If you don't know the rules....");
    System.out.println(Ansi.RED + "\nGOOGLE " + Ansi.RESET + "them. Just search \"3-13 card game!\"");
    System.out.println("If you are ready to begin enter \"yes\" or enter \"no\"!");

    String commence;
    lou = new Scanner(System.in);

    while (lou.hasNextLine())
    {
      commence = lou.nextLine();
      if (commence.equalsIgnoreCase("yes") || commence.equalsIgnoreCase("y") )
        return true;
      else if (commence.equalsIgnoreCase("no"))
        return false;
      else
        System.out.println("Enter \"yes\" to play and \"no\" to quit");
    }
    return false;
  } // end user userGreeting
}
