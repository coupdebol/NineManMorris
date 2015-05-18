/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Game {
	
	private static Player white;
	private static Player black;
	private static Board board;
	
	public Game(){
		white = new HumanPlayer(Side.WHITE);
		black = new HumanPlayer(Side.BLACK);
		board = new Board();
	}

	
}
