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
	
	public boolean placeTokenAt(Player player, int row, int col)
	{
		return false;
	}
	
	public boolean moveTokenTo(Player player, int rowFrom, int colFrom, int rowTo, int colTo)
	{
		return false;
	}
	
	public static Player getWhite() {
		return white;
	}

	public static void setWhite(Player white) {
		Game.white = white;
	}

	public static Player getBlack() {
		return black;
	}

	public static void setBlack(Player black) {
		Game.black = black;
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		Game.board = board;
	}

	
	
	

	
}
