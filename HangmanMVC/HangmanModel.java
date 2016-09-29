/* 
 * HangmanModel.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * This program implements model component.
 *
 * @author Imran Bohari
 * @author Deep Kataria
 */

public class HangmanModel {

	static ArrayList<String> wordlist = new ArrayList<String>();
	char randomworda[];
	int index;
	boolean isfound;
	String correctletter[];
	String PlayerName[] = new String[2];
	int score[] = new int[2];

	/**
	 * this method is used to return the player name 
	 * at the index provided
	 * 
	 * @param       index to search
	 * 
	 * @return		returns playername
	 * 
	 */
	
	public String PlayerName(int i) {

		return PlayerName[i];
	}
     
	/**
	 * this method is used check and update the hidden word
	 * with dashes based on the user input provided. 
	 * 
	 * @param        letter to be checked 
	 * 
	 */
	
	public void match(String letter) {

		for (int i = 0; i < randomworda.length; i++) {
			if (letter.charAt(0) == randomworda[i]) {
				index = i;
				correctletter[i] = letter;
				isfound = true;
				break;
			} else {
				isfound = false;
			}
		}
	}
    
	/**
	 * this method returns a randomword selected from the wordlist
	 * 
	 * 
	 * @return		randomword
	 * 
	 */
	
	static String RandomWord() {
		Random rand = new Random();
		String randomword = wordlist.get(rand.nextInt(wordlist.size()));
		return randomword;
	}

	/**
	 * this method is used to compute and return the winner
	 * 
	 * 
	 * @return	winner name
	 * 
	 */
	
	public String winner() {

		if (score[0] > score[1]) {
			return PlayerName[0];

		} else if (score[0] < score[1]) {
			return PlayerName[1];

		} else if (score[0] == score[1]) {
			return "Draw";
		} else
			return null;

	}

	
} // HangmanModel