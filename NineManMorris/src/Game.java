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
	
	public void placeTokenAt(Player player, int row, int col) throws TokenAlredyPlacedException,InvalidCoordinatesException
	{
		if(!board.isValidCoordinate(row, col)){
			throw new InvalidCoordinatesException();
		}
		if(board.getToken(row, col) == null)
		{
			Token t = player.getToken();
			t.setCol(col);
			t.setRow(row);
			board.addToken(t);
			//a mill may have been created, find mill and update full is necessary
			board.updateMill(t);
		}else{
			throw new TokenAlredyPlacedException();
		}
	}
	
	public static boolean hasMill(int row, int col)
	{
		Token t = board.getToken(row, col);
		return board.hasMill(t);
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

	@Override
	public String toString()
	{
		return board.toString();
	}
	
	
	

	
}
