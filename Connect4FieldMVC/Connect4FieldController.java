import java.util.Scanner;

/* 
 * Connect4FieldController.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

/**
 * This program implements controller component of MVC pattern
 *
 * @author Imran Bohari
 * @author Deep Kataria
 */

public class Connect4FieldController {

	Connect4FieldModel model = new Connect4FieldModel();
	Connect4FieldView view = new Connect4FieldView();
	static Connect4FieldController controller = new Connect4FieldController();

	static Player aPlayer;
	static Player bPlayer;

	/**
	 * this method is used to play game between two humans
	 * 
	 */
	
	public void playerNames(){
		Scanner input = new Scanner(System.in);
		for(int i =0; i < 2; i++){
			System.out.println("Enter Player "+(i+1)+" name:");
			String name = input.next();
			if ( i==0){
				aPlayer = new Player(model, name, '@');
			} else if(i==1){
				bPlayer = new Player(model, name, '$');
			}
		}
		input.close();
	}
	
	public void playTheGame() {
		model.init(aPlayer, bPlayer);
		int column;
//		System.out.println(this);
		boolean gameIsOver = false;
		do {
			for (int index = 0; index < 2; index++) {
				view.printBoard(model.toString());
				if (model.isItaDraw()) {
					view.printDraw();
					gameIsOver = true;
				} else {
					column = model.thePlayers[index].nextMove();
					if (model.checkIfPiecedCanBeDroppedIn(column))
						model.dropPieces(column, model.thePlayers[index].getGamePiece());
					else
						index = index - 1;
					if (model.didLastMoveWin()) {
						gameIsOver = true;
						view.printBoard(model.toString());
						view.printWinner(model.thePlayers[index].getName());
						break;
					}
				}
			}
		} while (!gameIsOver);
	}

	public static void main(String args[]) {
		 controller.playerNames();
		 controller.playTheGame();
	}
	
} // Connect4FieldController
