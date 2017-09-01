package TicTacToe;

import java.util.Scanner;

/**
 * 
 * @author Raphael
 * In order to play the game use
 * Spiel.TicTacToe().game();
 * in another class
 * 
 * Have fun!
 *
 *(The actual game is now in the TTTGUI class!)
 */


public class Spiel {
	protected final int spalten = 3;
	protected final int zeilen = 3;
	protected String[][] spielfeld;
	Scanner scanner = new Scanner(System.in);
	private String spieler1 = "";
	private String spieler2 = "";
	protected int turns = 0;

	
	
	
	/**
	 * creates a new 3x3 array filled with " - "
	 */
	public Spiel() {
		this.spielfeld = new String[spalten][zeilen];
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				spielfeld[i][j] = "-";
			}
		}

	}
	
	
	
	//getter methods
	public String getSpieler1(){
		return spieler1;
	}
	
	public String getSpieler2(){
		return spieler2;
	}
	
	public String[][] getSpielfeld(){
		return this.spielfeld;
	}
	
	//setter methods
	public void setSpieler1(String s){
		this.spieler1 = s;
	}
	public void setSpieler2(String s){
		this.spieler2 = s;
	}
	
	/**
	 * 
	 * @return returns a new Spielfeld on which, one can play a new game.
	 * Use this method in order to use the private constructor in another class.
	 *  This method's only purpose is a fitting name.
	 */
	public static Spiel TicTacToe(){
		return new Spiel();
	}

	/**
	 * The actual game. Uses the different private methods and determines a winner.
	 * You have to use this method in order to play the game.
	 */
	public void game() {
		setName();
		print();
		boolean draw = false;
		while (!winO() && !winX() && !draw) {
			turns++;
			inputPlayer1();
			print();
			if (!winX() && !winO()) {
				turns++;
				if (turns >= 9 && !winO() && !winX()) {
					draw = true;
				}

				if (!draw && !winO() && !winX()) {
					inputPlayer2();
				}
				print();
			}
		}
		if (winX()) {
			System.out.println("Congratulations " + spieler1 + ", you won!");
		}
		if (winO()) {
			System.out.println("Congratulations " + spieler2 + ", you won!");
		}
		if (winX() != true && winO() != true) {
			System.out.println("Draw!! You both lost! Sad!");
		}
	}

	/**
	 * Uses a scanner and gives the Strings spieler1 and spieler 2 a corresponding value.
	 */
	private void setName() {
		System.out.print("Chose a name player 1: ");
		spieler1 = scanner.nextLine();
		System.out.print("Hello " + spieler1 + ", your sign is X!\n");
		System.out.println("");
		System.out.print("Chose a name player 2: ");
		spieler2 = scanner.nextLine();
		System.out.print("Hello " + spieler2 + ", your sign is O!\n");
		System.out.println("");

	}

	/**
	 * prints out the two dimensional 3x3 array
	 */

	private void print() {
		System.out.println("");
		String grid = "S1  S2  S3\n\n";
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				grid += spielfeld[i][j];

				if (j != spalten - 1) {
					grid += " | ";
				} else {
					grid += "      Zeile: " + (i + 1) + "\n";
				}
			}
		}
		System.out.println(grid);

	}

	/**
	 * Uses a scanner and asks for a row and a column and fills in a X at the corresponding
	 * place in the array[][] spielfeld
	 */
	private void inputPlayer1() {
		String sign = "X";
		int signError = 1;
		;
		int zeile = 0;
		int spalte = 0;
		//exc becomes true when an exception is thrown and is used for exception handling.
		//It's start value is true in order to enter the while loop but becomes false after having entered it
		boolean exc=true;

		System.out.print(spieler1 + ", it's your turn, set a X!\n");

		
		System.out.println("");
		System.out.println("Type in the row(Zeile) and column(Spalte) where you want to set your X :");

		while (exc) {
			try {
				System.out.print("Zeile: ");
				zeile = scanner.nextInt();
				exc = false;
				if (zeile < 1 || zeile > zeilen) {
					throw new IllegalArgumentException();
				}
			} catch (Exception e) {
				exc = true;
				System.out.println("");
				System.out.println("--Invalid argument, try again!-- \n");
				scanner.nextLine();
				
				
			}
		}

		exc = true;
		while (exc) {
			try {
				System.out.print("Spalte: ");
				spalte = scanner.nextInt();
				exc = false;
				if (spalte < 1 || spalte > spalten) {
					throw new IllegalArgumentException();
				}
			} catch (Exception e) {
				exc = true;
				System.out.println("");
				System.out.println("--Invalid argument, try again!-- \n");
				scanner.nextLine();
				
			}
		}

		if (spielfeld[zeile - 1][spalte - 1].equals("-")) {
			spielfeld[zeile - 1][spalte - 1] = sign;
		}

		else {
			System.out.println("There's already a sign!");
			if (turns < 9) {
				inputPlayer1();
			}
		}

	}

	
	/**
	 * Uses a scanner and asks for a row and a column and fills in a O at the corresponding
	 * place in the array[][] spielfeld
	 */
	private void inputPlayer2() {
		String sign = "O";
		int signError = 1;
		;
		int zeile = 0;
		int spalte = 0;
		//exc becomes true when an exception is thrown and is used for exception handling.
		//It's start value is true in order to enter the while loop but becomes false after having entered it
		boolean exc = true;

		System.out.print(spieler2 + ", it's your turn, set a O!\n");

		System.out.println("");
		System.out.println("Type in the row(Zeile) and column(Spalte) where you want to set your O :");

		while (exc) {
			try {
				System.out.print("Zeile: ");
				zeile = scanner.nextInt();
				exc = false;
				if (zeile < 1 || zeile > zeilen) {
					throw new IllegalArgumentException();
				}
			} catch (Exception e) {
				exc = true;
				System.out.println("");
				System.out.println("--Invalid argument, try again!-- \n");
				scanner.nextLine();

			}
		}

		exc = true;
		while (exc) {
			try {
				System.out.print("Spalte: ");
				spalte = scanner.nextInt();
				exc = false;
				if (spalte < 1 || spalte > spalten) {
					throw new IllegalArgumentException();
				}
			} catch (Exception e) {
				exc = true;
				System.out.println("");
				System.out.println("--Invalid argument, try again!-- \n");
				scanner.nextLine();

			}
		}

		if (spielfeld[zeile - 1][spalte - 1].equals("-")) {
			spielfeld[zeile - 1][spalte - 1] = sign;
		}

		else {
			System.out.println("There's already a sign!");
			if (turns < 9) {
				inputPlayer2();
			}
		}

	}

	/**
	 * 
	 * @return return true if a player set three X in a row, a column or on the diagonal.
	 *         Otherwise false.
	 */
	public boolean winX() {

		for (int i = 0; i < zeilen; i++) {
			if (spielfeld[i][0].equals("X") && spielfeld[i][1].equals("X") && spielfeld[i][2].equals("X")) {
				return true;
			}
		}

		for (int i = 0; i < spalten; i++) {
			if (spielfeld[0][i].equals("X") && spielfeld[1][i].equals("X") && spielfeld[2][i].equals("X")) {
				return true;
			}
		}

		if ((spielfeld[0][0].equals("X") && spielfeld[1][1].equals("X") && spielfeld[2][2].equals("X"))
				|| (spielfeld[0][2].equals("X") && spielfeld[1][1].equals("X") && spielfeld[2][0].equals("X"))) {
			return true;
		}

		return false;

	}

	/**
	 * 
	 * @return return true if a player set three O in a row, a column or on the diagonal.
	 *         Otherwise false.
	 */
	public boolean winO() {

		for (int i = 0; i < zeilen; i++) {
			if (spielfeld[i][0].equals("O") && spielfeld[i][1].equals("O") && spielfeld[i][2].equals("O")) {
				return true;
			}
		}

		for (int i = 0; i < spalten; i++) {
			if (spielfeld[0][i].equals("O") && spielfeld[1][i].equals("O") && spielfeld[2][i].equals("O")) {
				return true;
			}
		}

		if ((spielfeld[0][0].equals("O") && spielfeld[1][1].equals("O") && spielfeld[2][2].equals("O"))
				|| (spielfeld[0][2].equals("O") && spielfeld[1][1].equals("O") && spielfeld[2][0].equals("O"))) {
			return true;
		}

		return false;

	}
}