/* 
 * HangmanView.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
import java.util.Scanner;

/**
 * This program implements the View component.
 *
 * @author Imran Bohari
 * @author Deep Kataria
 */

public class HangmanView {
	Scanner in = new Scanner(System.in);
	
	/**
	 * this method is used to ask user input and return it to the 
	 * controller for processing
	 * 
	 * @return		 letter
	 * 
	 */
	
	public String enterLetter() {
		System.out.println("Enter letter " );
		String letter;
		letter = in.nextLine();
		return letter;
	}
	
	/**
	 * this method is used to ask for player name and return it to 
	 * the controller for storing
	 * 
	 * @return		returns gamepiece
	 * 
	 */
	
	public String playerNames(int i){
		
		System.out.println("Enter Player " + i + " name");
		String player = in.nextLine();
		return player;
		
	}
	
	/**
	 * this method is used to display the score to the user
	 * 
	 * 
	 */
	
	public void displayScore(String name ,int score){
		System.out.println(" \n\n" +name + " : " + score);
		
	}
	
	/**
	 * this method is used to display the number of lives left
	 * 
	 * 
	 */
	
	public void displayNumberOfLives(int lives){
		System.out.println("        Lives left" + lives);
		System.out.println("============================");
		
	}
	
	/**
	 * this method is used to display the hidden word with dashes
	 * 
	 * 
	 */
	
	public void displayCorrectLetter(String correctletter){
		
		System.out.print(" " + correctletter);
	}
	
	/**
	 * this method is used to notify the specific about his/her turn
	 * 
	 * 
	 */
	
	public void yourTurn(String play){
		System.out.println("\nYour turn " + play);
	}
	
	/**
	 * this method is used to display the winner .
	 * 
	 * 
	 */
	
	/**
	 * this method is used to display the correct word in the end of player's
	 * turn if he/she was not able to guess the word correctly.
	 * 
	 */
	
	public void displayCorrectWordEnd(String randomword){
		
		System.out.print("Your word was :" + randomword);
	}
	
	public void gameOver(String message){
		String winner = message;
		if (winner.equals("Draw")){
			System.out.println("Game Draw");
		}else {
			System.out.println("The winner is " +winner);
		}
		System.out.println("Game Over");
	}

} // HangmanView
