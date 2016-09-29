
/* 
 * Connect4FieldView.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

import java.util.Scanner;

/**
 * This program implements view component of MVC pattern
 *
 * @author Imran Bohari
 * @author Deep Kataria
 */

public class Connect4FieldView {
	String str;

	/**
	 * This method is used to display the current state of board
	 *
	 * @param	str		board
	 *
	 */

	public void printBoard(String str) {
		System.out.println(str);
	}

	/**
	 * This method is used to warn user to enter a correct column number
	 *
	 */
	
	public void validColum() {
		System.out.println("Please input a valid column no.");
	}
	
	/**
	 * This method is used to take column number from player
	 *
	 */
	
	public void enterColumn(String name) {
		System.out.println(name + " Enter a column number: ");
	}
	
	/**
	 * This method is used to inform that the game has drawn
	 *
	 */
	
	public void printDraw() {
		System.out.println("Its a Draw");
	}

	/**
	 * This method is used to display winner
	 *
	 */

	public void printWinner(String playername) {
		System.out.println("The Winner is " + playername);
	}


	/**
	 * This method is used to take column no. as input from the user
	 *
	 * @return user input/ column no.
	 */

	public Object inputColumn() {
		Scanner input = new Scanner(System.in);
		return input;
	}

	/**
	 * This method is used to inform that the column is full
	 *
	 */

	public void columnFull() {
		System.out.println("This column is full, select a different "
				+ "" + "column.");
	}

} // Connect4FieldView