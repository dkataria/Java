
/* 
 * Connect4FieldModel.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

import java.util.Scanner;
import java.util.Random;

/**
 * This program implements model component of MVC pattern
 *
 * @author Imran Bohari
 * @author Deep Kataria
 */

interface Connect4FieldInterface {
	public boolean checkIfPiecedCanBeDroppedIn(int column);

	public void dropPieces(int column, char gamePiece);

	boolean didLastMoveWin();

	public boolean isItaDraw();

	public void init(PlayerInterface playerA, PlayerInterface playerB);

	public String toString();
}

interface PlayerInterface {

	public char getGamePiece();

	public String getName();

	public int nextMove();
}

/**
 * This class provides implementation for PlayerInterface
 *
 * @author Imran Bohari
 * @author Deep Kataria
 */

class Player implements PlayerInterface { 
	
	char gamePiece;
	String playerName;

	public Player(Connect4FieldModel aConnect4Field, String str, char c) {
		gamePiece = c;
		playerName = str;
	}

	/**
	 * this method is used to return game piece
	 * 
	 * @return		returns gamepiece
	 * 
	 */
	
	public char getGamePiece() {
		return gamePiece;
	}

	/**
	 * this method is used to get player name
	 * 
	 * @return		returns player name
	 * 
	 */
	
	public String getName() {
		return playerName;
	}

	/**
	 * this method takes column no. as input from user
	 * 
	 * @return		returns player name
	 * 
	 */
	
	Connect4FieldView view = new Connect4FieldView();
	
	/**
	 * this method returns column which is used for next move
	 * 
	 * @return		column no.
	 * 
	 */
	
	public int nextMove() {
		view.enterColumn(playerName);
//		System.out.print(playerName + " Enter a column no.");
		Object input = view.inputColumn();
		return ((Scanner) input).nextInt();
	}
}

/**
 * This class provides implementation for Connect4Fieldinterface
 *
 * @author Imran Bohari
 * @author Deep Kataria
 */

public class Connect4FieldModel implements Connect4FieldInterface {
	
	char[][] board = new char[9][25];
//	char gamePieceA = '+';
//	char gamePieceB = '*';
	String str = "";
	PlayerInterface[] thePlayers = new PlayerInterface[3];
	Connect4FieldView view = new Connect4FieldView();

	Connect4FieldModel() {
		// initializing 2D array with o
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 25; j++) {
				board[i][j] = 'o';
			}
		}

		// initializing left triangle with spaces
		for (int row = 1; row < 9; row++) {
			for (int col = 0; col < row; col++) {
				board[row][col] = ' ';
			}
		}

		// initializing right triangle with spaces
		for (int row = 1; row < 9; row++) {
			for (int col = 24; col >= 25 - row; col--) {
				board[row][col] = ' ';
			}
		}
	}

	/**
	 * the below method replaces the default toString() and is used to display
	 * current state of the game board.
	 *
	 */

	public String toString() {
		str = "";
		// displaying board
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 25; j++) {
				str = str + board[i][j];
			}
			str = str + '\n';
		}
		return str;
	}

	/**
	 * The main program.
	 *
	 * @param args		command line arguments  (ignored)
	 *           
	 */

	public static void main(String[] args) {

	}

	/**
	 * this method is used to check whether the game piece can be dropped
	 * in the specified column or not.
	 * 
	 * @param	column	column number of board 
	 * 
	 * return			true if piece can be dropped
	 *
	 */
	
	public boolean checkIfPiecedCanBeDroppedIn(int column) {
		
		// if users inputs invalid column no. warn him. If the column no.
		// entered is valid, but the column is full, notify the player.
		// If the piece can be dropped return true.
		if (column < 0 || column > 24) {
			view.validColum();
			return false;
		} else if (board[0][column] != 'o') {
			view.columnFull();
			return false;
		}
		return true;
	}
	
	/**
	 * this method is used to drop game piece in the specified column
	 * 
	 * @param	column		column number of board
	 * @param	gamePiece	player game piece
	 * 
	 */

	public void dropPieces(int column, char gamePiece) {
		// check elements of the column and drop game piece when we find
		// first empty element.
		if (checkIfPiecedCanBeDroppedIn(column)) {
			for (int row = 8; row >= 0; row--) {
				if (board[row][column] == 'o') {
					board[row][column] = gamePiece;
					break;
				}
			}
		}
	}
	
	/**
	 * this method is used to check if the last move resulted in a victory
	 * 
	 * @return		returns true if the last move resulted in a victory
	 * 
	 */

	public boolean didLastMoveWin() {
		// check if there are consecutive 4 gamePiece in the same row
		for (int row = 0; row < 9; row++) {
			int count = 1;
			for (int col = 1; col < 25; col++) {
				if ((board[row][col] != 'o') && (board[row][col] != ' ') 
						&& board[row][col] == board[row][col - 1])
					count++;

				if (count == 4)
					return true;
			}
		}

		// check if there are 4 consecutive gamePiece in the same column
		for (int col = 0; col < 25; col++) {
			int count = 1;
			for (int row = 0; row < 8; row++) {
				if ((board[row][col] != 'o') && (board[row][col] != ' ') 
						&& board[row][col] == board[row + 1][col])
					count++;

				if (count == 4)
					return true;
			}
		}

		// check if there are 4 consecutive gamePiece in a diagonal
		// there are two types of diagonal i.e. from top-left to bottom right
		// and from top right to bottom left.
		// check for top left to bottom right
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 22; col++) {
				if ((board[row][col] != 'o') && (board[row][col] != ' ') 
						&& (board[row + 1][col + 1] != 'o')
						&& (board[row + 1][col + 1] != ' ') 
						&& (board[row + 2][col + 2] != 'o')
						&& (board[row + 2][col + 2] != ' ') 
						&& (board[row + 3][col + 3] != 'o')
						&& (board[row + 3][col + 3] != ' ') 
						&& (board[row][col]) == (board[row + 1][col + 1])
						&& (board[row + 1][col + 1])==(board[row + 2][col + 2])
						&& (board[row +2][col + 2])==(board[row + 3][col + 3]))
					return true;
			}
		}

		// check for top right to bottom left
		for (int row = 0; row < 6; row++) {
			for (int col = 24; col > 2; col--) {
				if ((board[row][col] != 'o') && (board[row][col] != ' ') 
						&& (board[row + 1][col - 1] != 'o')
						&& (board[row + 1][col - 1] != ' ') 
						&& (board[row + 2][col - 2] != 'o')
						&& (board[row + 2][col - 2] != ' ') 
						&& (board[row + 3][col - 3] != 'o')
						&& (board[row + 3][col - 3] != ' ') 
						&& (board[row][col]) == (board[row + 1][col - 1])
						&& (board[row + 1][col - 1])==(board[row + 2][col - 2])
						&& (board[row +2][col - 2])==(board[row + 3][col - 3]))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * this method is used to check if the ended as a draw
	 * 
	 * @return		returns true if the game ended as a draw
	 * 
	 */

	public boolean isItaDraw() {

		if (didLastMoveWin()) {
			return false;
		}

		int drawcount = 0;
		for (int col = 0; col < 25; col++) {
			if (board[0][col] == 'o')
				drawcount++;
		}
		if (drawcount == 0)
			return true;
		else
			return false;
	}

	/**
	 * this method is used to store PlayerInterface objects in an array
	 * 
	 * @param	playerA		object of PlayerInterface
	 * @param	playerB		object of PlayerInterface
	 * 
	 */
	
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		String player1 = playerA.getName();
		String player2 = playerB.getName();

		thePlayers[0] = playerA;
		thePlayers[1] = playerB;
	}
} // Connect4FieldModel
