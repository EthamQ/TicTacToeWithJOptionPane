package TicTacToe;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TTTGUI extends Spiel {

	public static void main(String[] args) {
		TTTGUI t = new TTTGUI();
		t.game();	
	}

	
	//Constructor
	public TTTGUI() {
		super();
		

	}
	/**
	 * 
	 * @return returns a new 3x3 array[][] filled with "--"
	 */
	public String[][] createNewArray() {
		String[][] newSpielfeld = new String[spalten][zeilen];
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				newSpielfeld[i][j] = "--";
			}
		}
		return newSpielfeld;

	}

	/**
	 * The actual game using the different methods
	 */
	public void game() {
		this.spielfeld = createNewArray();
		turns = 0;
		this.setName();
		String name1 = this.getSpieler1();
		String name2 = this.getSpieler2();

		boolean draw = false;
		while (!winO() && !winX() && !draw) {
			turns++;
			this.inputPlayer("X", name1);
			if (!winX() && !winO()) {
				turns++;
				if (turns >= 9 && !winO() && !winX()) {
					draw = true;
				}

				if (!draw && !winO() && !winX()) {
					this.inputPlayer("O", name2);
				}

			}
		}
		if (winX() && !draw) {
			System.out.println("Congratulations " + name1 + ", you won!");
			int option = JOptionPane.showConfirmDialog(null,
					"Congratulations " + name1 + ", you won!\n" + " Do you want to play again?", "YAAYY", 1);
			if (option == 0) {
				game();
			} else {
				System.exit(0);
			}
		}
		if (winO() && !draw) {
			int option = JOptionPane.showConfirmDialog(null,
					"Congratulations " + name2 + ", you won!\n" + " Do you want to play again?", "YAAYY", 1);
			if (option == 0) {
				game();
			} else {
				System.exit(0);
			}
		}
		if (!winX() && !winO()) {
			int option = JOptionPane.showConfirmDialog(null, "Draw!! You both lost! Sad! Try again?",
					"BOOOOHHH\n" + " Do you want to play again?", 1);
			if (option == 0) {
				game();
			} else {
				System.exit(0);
			}

		}
	}

	/**
	 * asks the two players for their name and gives the input to the variables
	 * spieler1 and spieler2
	 */
	public void setName() {
		// Create the message for player 1, will ask him to set his name
		JTextField name1 = new JTextField();
		Object[] message1 = { "Chose a name player 1: ", name1 };
		int option = JOptionPane.showConfirmDialog(null, message1, "Before it starts...", 2);
		
		if (option == 0) {
			
			if(name1.getText().equals("")){
				int optionEmpty = JOptionPane.showConfirmDialog(null, "This isn't even a name. Try again?", "What are you even doing!?", 2);
				if(optionEmpty!=0){
					System.exit(0);
				}
				setName();
			}
			else{
			setSpieler1(name1.getText());
			JOptionPane.showMessageDialog(null, "Hello " + getSpieler1() + ", your sign is X!", "Before it starts...",
					JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			System.exit(0);
		}
		
		
		

		// Create the message for player 2, will ask him to set his name
		boolean exc = true;
		while (exc){
			exc=true;
		JTextField name2 = new JTextField();
		Object[] message2 = { "Chose a name player 2: ", name2 };
		int option2 = JOptionPane.showConfirmDialog(null, message2, "Before it starts...", 2);
		if (option2 == 0) {
			if(name2.getText().equals("")){
				int optionEmpty = JOptionPane.showConfirmDialog(null, "This isn't even a name. Try again?", "What are you even doing!?", 2);
				if(optionEmpty!=0){
					System.exit(0);
				}
				exc = true;
			}
			else{
			setSpieler2(name2.getText());
			JOptionPane.showMessageDialog(null, "Hello " + getSpieler2() + ", your sign is O!", "Before it starts...",
					JOptionPane.PLAIN_MESSAGE);
			exc = false;
			}
		} else {
			System.exit(0);
		}
		}

		// if the names are identical, gives the option to continue or to try it
		// again
		if (this.getSpieler1().equals(this.getSpieler2())) {
			int option3 = JOptionPane.showConfirmDialog(null, "Your names are kind of similar...wanna try again?",
					"Hmmm", 1);
			if (option3 == 0) {
				this.setName();
			}
			if (option3 == 1) {
			} else {
				System.exit(0);
			}
		}

	}

	/**
	 * 
	 * @return returns a String that gives a graphical representation of the 3x3
	 *         array spielfeld[][]
	 */
	public String print() {
		System.out.println("");
		String grid = "Spalte:\n  1          2          3 \n";
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				grid += getSpielfeld()[i][j];

				if (j != spalten - 1) {
					grid += "      |      ";
				} else {
					grid += "      Zeile: " + (i + 1) + "\n";
				}
			}
		}
		return grid;

	}

	/**
	 * 
	 * @param z
	 * @return returns true if z is smaller than 1 or bigger than the number of rows
	 */
	public boolean outOfBounds(int z) {
		return (z < 1 || z > zeilen);
	}

	
	
	/**
	 * 
	 * @param input
	 *            the chosen input will be put into the array later
	 * @param name
	 *            insert the name of the player, use the classvariables spieler1 and spieler2
	 */
	private void inputPlayer(String input, String name) {
		// X or O
		String sign = input;

		// gets a value between 1 and 3 depending on the player's input
		int zeile = 0;
		int spalte = 0;

		// exc becomes true when an exception is thrown and is used for
		// exception handling.
		// It's start value is true in order to enter the while loop but becomes
		// false after having entered it. 
		boolean exc = true;

		// content of the window
		JTextField zeileInp = new JTextField();
		JTextField spalteInp = new JTextField();
		Object[] message = { print(), "Where dou you want to set your "+sign+" ?",
				"Spalte: ", spalteInp, "Zeile: ", zeileInp };

		// catch if input isn't a number or not a valid number and ask once
		// again
		// loop only continues if the catch block isn't entered
		// TODO: optional: add custom exception for letters and invalid numbers
		while (exc) {
			// Input from the player
			int option = JOptionPane.showConfirmDialog(null, message, name + ", it's your turn, set a " + sign + "!",
					2);
			String zeileString = zeileInp.getText();
			String spalteString = spalteInp.getText();

			// Exit if the player presses cancel
			if (option == 0) {
				try {
					exc = false;
					zeile = Integer.parseInt(zeileString);
					spalte = Integer.parseInt(spalteString);
					if (outOfBounds(zeile) || outOfBounds(spalte)) {
						throw new IllegalArgumentException();
					}

				} catch (Exception e) {
					exc = true;
					int optionExc = JOptionPane.showConfirmDialog(null, "--Invalid argument, try again!--", "What are you even doing??", 2);
//							"BOOOOHHH\n" + " Do you want to play again?", 1);
					if (optionExc != 0) {
						System.exit(0);
					}

				}

			} else {
				System.exit(0);
			}
		}

		//puts sign into the array if there hasn't already been put a sign
		if (getSpielfeld()[zeile - 1][spalte - 1].equals("--")) {
			getSpielfeld()[zeile - 1][spalte - 1] = sign;
		}

		else {
			int option = JOptionPane.showConfirmDialog(null, "There's already a sign! Try again!", "What are you even doing??", 2);
			if (option != 0) {
				System.exit(0);
			}
			if (turns < 9) {
				inputPlayer(input, name);
			}
		}

	}
}
