/*
	Provided code by Dan Shervheim
	UMN 1103 TA Fall 2018

	Adapted from:
	https://gist.github.com/dainkaplan/4651352

	This file does not require any modification by the students.

	...

	Ansi escape sequences are strings which, when printed to the terminal,
	will control the formatting of all text printed after them.

	e.g. System.out.println(Ansi.RED + "Warning!" + Ansi.RESET);

	will print out "Warning!" in a red font. Note that because the
	sequences affect all text printed out after them, you must explicitly
	print out Ansi.RESET to reset the styles back to normal.

	...

	Also note that these member variables are static. That means you do not
	have to create instances of Ansi to access them.

	e.g.

	Ansi myAnsiVar = new Ansi();
	System.out.println(myAnsiVar.ITALIC + "italic message" + myAnsiVar.RESET);

	is not necessary. Simply

	System.out.println(Ansi.ITALIC + "italic message" + Ansi.RESET);

	is fine.

	...

	Also, note that these escape sequences only work on Unix machines (Linux, MacOS)
	So they may/may not have any affect on windows command lines.

	...

	Finally, you may define your own styles as combinations of sequences.
	We have provided 2 additions below (printing out YES and NO in green
	and red fonts). You may find these useful in the Existential/RelationalQuestion
	classes.
*/
public class Ansi
{
	public static final String	RESET				= "\u001B[0m";

	public static final String	HIGH_INTENSITY		= "\u001B[1m";
	public static final String	LOW_INTENSITY		= "\u001B[2m";

	public static final String	ITALIC				= "\u001B[3m";
	public static final String	UNDERLINE			= "\u001B[4m";
	public static final String	BLINK				= "\u001B[5m";
	public static final String	RAPID_BLINK			= "\u001B[6m";
	public static final String	REVERSE_VIDEO		= "\u001B[7m";
	public static final String	INVISIBLE_TEXT		= "\u001B[8m";

	public static final String	BLACK				= "\u001B[30m";
	public static final String	RED					= "\u001B[31m";
	public static final String	GREEN				= "\u001B[32m";
	public static final String	YELLOW				= "\u001B[33m";
	public static final String	BLUE				= "\u001B[34m";
	public static final String	MAGENTA				= "\u001B[35m";
	public static final String	CYAN				= "\u001B[36m";
	public static final String	WHITE				= "\u001B[37m";

	public static final String	BACKGROUND_BLACK	= "\u001B[40m";
	public static final String	BACKGROUND_RED		= "\u001B[41m";
	public static final String	BACKGROUND_GREEN	= "\u001B[42m";
	public static final String	BACKGROUND_YELLOW	= "\u001B[43m";
	public static final String	BACKGROUND_BLUE		= "\u001B[44m";
	public static final String	BACKGROUND_MAGENTA	= "\u001B[45m";
	public static final String	BACKGROUND_CYAN		= "\u001B[46m";
	public static final String	BACKGROUND_WHITE	= "\u001B[47m";

	// define your own strings and/or styles below
	public static final String YES = Ansi.HIGH_INTENSITY + Ansi.BACKGROUND_GREEN + Ansi.WHITE + "YES!" + Ansi.RESET;
	public static final String NO = Ansi.HIGH_INTENSITY + Ansi.BACKGROUND_RED + Ansi.WHITE + "NO!" + Ansi.RESET;
}
