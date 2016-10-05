
/* 
 * HangmanController.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * This program implements controller component.
 *
 * @author Imran Bohari
 * @author Deep Kataria
 */

public class HangmanController implements Runnable {

	static HangmanModel hmodel = new HangmanModel();
	static HangmanView hview = new HangmanView();
	String player;
	String lock = "";

	public HangmanController() {

	}

	/**
	 * this method contains entire game logic
	 * 
	 */

	public void playHangman() {

		synchronized (lock) {
			int count = 0;
			boolean p1 = true;
			boolean p2 = true;
			do {
				lock.notify();

				if (Thread.currentThread().getName().equals("1")) {
					int index = 0;
					String play = hmodel.PlayerName(index);
					hview.yourTurn(play);

					int num_of_lives = 8;
					String randomword = hmodel.RandomWord();

					hmodel.randomworda = new char[randomword.length()];
					hmodel.correctletter = new String[randomword.length()];

					// Putting characters from String randomword into array
					// randomworda

					for (int i = 0; i < randomword.length(); i++) {
						hmodel.randomworda[i] = randomword.charAt(i);
						hmodel.correctletter[i] = "_ ";
					}

					// displaying secret word in the form of blanks

					for (int i = 0; i < hmodel.correctletter.length; i++) {
						System.out.print(" " + hmodel.correctletter[i]);

					}

					// while loop will work until user finishes all of his
					// guesses
					// and the secret word has been guessed

					while (num_of_lives != 0 && Arrays.asList
							(hmodel.correctletter).contains("_ ")) {
						hview.displayNumberOfLives(num_of_lives);
						String letter = hview.enterLetter();
						hmodel.match(letter);

						// displaying the correctly guessed characters with
						// blank

						for (int i = 0; i < hmodel.correctletter.length; i++) {
							String correctletter = hmodel.correctletter[i];
							hview.displayCorrectLetter(correctletter);
						}

						// for an incorrect guess the no. of lives is
						// decremented

						if (hmodel.isfound == false) {
							num_of_lives = num_of_lives - 1;
						}

					}

					hmodel.score[index] = num_of_lives;
					// displaying user name and score

					hview.displayScore(hmodel.PlayerName[index], 
							hmodel.score[index]);
					if (num_of_lives == 0){
						hview.displayCorrectWordEnd(randomword);
					}
					
					p1 = false;
					try {

						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else if (Thread.currentThread().getName().equals("2")) {
					int index = 1;
					String play = hmodel.PlayerName(index);
					hview.yourTurn(play);

					int num_of_lives = 8;
					String randomword = hmodel.RandomWord();

					hmodel.randomworda = new char[randomword.length()];
					hmodel.correctletter = new String[randomword.length()];

					// Putting characters from String randomword into array
					// randomworda

					for (int i = 0; i < randomword.length(); i++) {
						hmodel.randomworda[i] = randomword.charAt(i);
						hmodel.correctletter[i] = "_ ";
					}

					// displaying secret word in the form of blanks

					for (int i = 0; i < hmodel.correctletter.length; i++) {
						hview.displayCorrectLetter(hmodel.correctletter[i]);
						// System.out.print(" " + hmodel.correctletter[i]);
					}

					// while loop will work until user finishes all of his
					// guesses
					// and the secret word has been guessed

					while (num_of_lives != 0 && Arrays.asList
							(hmodel.correctletter).contains("_ ")) {
						hview.displayNumberOfLives(num_of_lives);
						String letter = hview.enterLetter();
						hmodel.match(letter);

						// displaying the correctly guessed characters with
						// blank

						for (int i = 0; i < hmodel.correctletter.length; i++) {
							String correctletter = hmodel.correctletter[i];
							hview.displayCorrectLetter(correctletter);
						}

						// for an incorrect guess the no. of lives is
						// decremented

						if (hmodel.isfound == false) {
							num_of_lives = num_of_lives - 1;
						}

					}

					hmodel.score[index] = num_of_lives;
					// displaying user name and score

					hview.displayScore(hmodel.PlayerName[index],
							hmodel.score[index]);
					p2 = false;

					try {

						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			} while (p1 && p2);
		}

	}

	public static void main(String[] args) throws IOException {
		HangmanController hcontroller = new HangmanController();
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);

		BufferedReader reader = new BufferedReader(
				new FileReader("C:/Users/" + 
		"Deep Kataria/workspace/Hang/src/words.txt"));
		String read;
		read = reader.readLine();

		// while loop reads words from word.txt and adds them to array wordlist

		while (read != null) {
			hmodel.wordlist.add(read);
			read = reader.readLine();
		}

		// asking for player name
		for (int i = 0; i < hmodel.PlayerName.length; i++) {
			hmodel.PlayerName[i] = hview.playerNames(i + 1);

		}

		Thread thread1 = new Thread(hcontroller);
		thread1.setName("1");
		Thread thread2 = new Thread(hcontroller);
		thread2.setName("2");
		thread1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start();
		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// display the winner and terminate the game 
		String winnerMessage = hmodel.winner();
		hview.gameOver(winnerMessage);
		System.exit(0);

	}
    
	@Override
	public void run() {
		playHangman();
	}

} // HangmanController 
