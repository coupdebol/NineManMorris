import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class GameGUI {
	
	private static Game game = new Game();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)
	{
		System.out.println("This is the Nine Man Morris Game");
		while(true)
		{
			System.out.println("Type one of the following:");
			System.out.println("n - New Game");
			System.out.println("x - Exit Game");
			
			
			String s = "";
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(s.trim().equalsIgnoreCase("x"))
			{
				System.out.println("Bye bye!");
				System.exit(0);
			}
			if(s.trim().equalsIgnoreCase("n"))
			{
				System.out.println("Starting a new game with two human players!");
				init2PlayerGame();
			}
		}
	}


	private static void init2PlayerGame() {
		Player white = Game.getWhite();
		Player black = Game.getBlack();
		Board board = Game.getBoard();
		
		System.out.println(board.toString());
		
		while(true)
		{
			
//			try {
//				s = br.readLine();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			if(s.equalsIgnoreCase("a"))
//			{
//				System.out.println("aborting the game");
//				break;
//			}
			
			while(white.hasToken() || black.hasToken())
			{
				while(!placeToken(white)){}
				
				while(!placeToken(black)){}
				
			}
			System.out.println("all tokens have now been placed on the board");
			boolean gameOver = false;
			while(!gameOver)
			{
				while(!moveToken(white)){}
				gameOver = isGameOver();
				while(!moveToken(black)){}
				gameOver = isGameOver();
			}
			
		}
		
	}
	
	private static boolean moveToken(Player white) {
		// TODO Auto-generated method stub
		return false;
	}


	private static boolean isGameOver() {
		return false;
	}

	private static int[] obtainUserInput(int numOfCoordinatesSet) 
			throws InvalidInputException,Exception
	{
		if( (numOfCoordinatesSet !=2) && (numOfCoordinatesSet !=4))
		{
			throw new Exception("invalid call to obtainUserInput");
		}
		int[] coordinates = new int[2*numOfCoordinatesSet];
		try
		{
			String input = br.readLine();
			String[] inputs = input.split(" ");
			if(inputs.length < 2){
				throw new NumberFormatException();
			}
			coordinates[0] = Integer.parseInt(inputs[0]);
			coordinates[1] = Integer.parseInt(inputs[1]);
			if(numOfCoordinatesSet==4)
			{
				coordinates[2] = Integer.parseInt(inputs[2]);
				coordinates[3] = Integer.parseInt(inputs[3]);
			}
			
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
			throw new InvalidInputException();
		} catch (NumberFormatException e) {
			System.out.println("Enter two numbers separated by a space");
			throw new InvalidInputException();
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Enter two sets of coordinates separated by a space");
			throw new InvalidInputException();
		}
		return coordinates;
	}

	private static boolean placeToken(Player player)
	{
		System.out.println("Player " + player.getName());
		System.out.print("Enter Token coodinates as row col:");
		int row;
		int col;
		try {
			int[] coordinates = obtainUserInput(2);
			row = coordinates[0];
			col = coordinates[1];
			game.placeTokenAt(player, row, col);
			System.out.println(game);
		} catch (TokenAlredyPlacedException e){
			System.out.println("There is already a token at that location");
			return false;
		} catch (InvalidCoordinatesException e) {
			System.out.println("Invalid cell coordinate");
			return false;
		} catch (InvalidInputException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if(millCreated(row,col))
		{			
			while(!removeOpponentToken(player)){}
			System.out.println(game);
		}
		return true;
	}


	private static boolean millCreated(int row, int col) {
		if(Game.hasMill(row,col))
		{
			System.out.println("There is a mill!");
			return true;
		}
		return false;
	}


	private static boolean removeOpponentToken(Player player) {

		System.out.println("Player "+player.getName()+" ,enter coordinate of opponent token you wish to remove:");
		int[] coordinates;
		try {
			coordinates = obtainUserInput(2);
			int row = coordinates[0];
			int col = coordinates[1];
			Side side = player.getSide();
			Side sideToRemove = null;
			if(side.equals(Side.BLACK)){
				sideToRemove = Side.WHITE;
			}
			if(side.equals(Side.WHITE)){
				sideToRemove = Side.BLACK;
			}
			game.removeToken(row,col, sideToRemove);
		} catch (InvalidCoordinatesException e) {
			System.out.println("invalid coordinates");
			return false;
		} catch (InvalidInputException e) {
			System.out.println("invalid input");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
