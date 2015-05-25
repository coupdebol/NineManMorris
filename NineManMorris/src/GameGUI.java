import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
	private static Side lastSidePlayed = Side.X;
	
	public static class GameState
	{
		public int bagX;
		public int bagO;
		public List<Intersection> tokenX = new ArrayList<>();
		public List<Intersection> tokenO = new ArrayList<>();;
		
		
	}
	public static class Intersection
	{
		public int row;
		public int col;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)
	{
		GameState state = new GameState();
		try
		{
			if(args.length > 5)
			{
				state.bagO = Integer.parseInt(args[2]);
				state.bagX = Integer.parseInt(args[4]);
				for(int i = 5 ; i < args.length - 2 ; i+=3)
				{
					Intersection intersection = new Intersection();
					intersection.row = Integer.parseInt(args[i+1]);
					intersection.col = Integer.parseInt(args[i+2]);
					if(args[i].equals("O"))
					{
						state.tokenO.add(intersection);
					}
					if(args[i].equals("X"))
					{
						state.tokenX.add(intersection);
					}
				}
			
				lastSidePlayed = Side.valueOf(args[0]);
				Player playerO = new HumanPlayer(Side.O);
				playerO.setTokenBagSize(state.bagO);
				Player playerX = new HumanPlayer(Side.X);
				playerX.setTokenBagSize(state.bagX);
				Game.setWhite(playerO);
				Game.setBlack(playerX);
				Board board = new Board();
				for(Intersection inter : state.tokenO)
				{
					board.addToken(new Token(inter.row,inter.col,Side.O));
				}
				for(Intersection inter : state.tokenX)
				{
					board.addToken(new Token(inter.row,inter.col,Side.X));
				}
				Game.setBoard(board);
				init2PlayerGame();
			}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		init2PlayerGame();
		
	}


	private static void init2PlayerGame() {
		Player playerO = Game.getPlayerO();
		Player playerX = Game.getPlayerX();
		
		System.out.println(game);
		
		while(true)
		{			
			while(playerO.hasToken() || playerX.hasToken())
			{
				if(lastSidePlayed.equals(Side.X) && playerO.hasToken())
				{
					while(!placeToken(playerO)){}
					lastSidePlayed = Side.O;
				}
				if(lastSidePlayed.equals(Side.O) && playerX.hasToken())
				{
					while(!placeToken(playerX)){}
					lastSidePlayed = Side.X;
				}
			}
			System.out.println("all tokens have now been placed on the board");
			
			boolean gameOver = false;
			while(!gameOver)
			{
				if(lastSidePlayed.equals(Side.X))
				{
					while(!moveToken(playerO)){}
					gameOver = Game.getWinner().equals(Side.NONE) ? false : true ;
					lastSidePlayed = Side.O;
				}
				if(lastSidePlayed.equals(Side.O))
				{
					while(!moveToken(playerX)){}
					gameOver = Game.getWinner().equals(Side.NONE) ? false : true ;
					lastSidePlayed = Side.X;
				}
			}
			System.out.println("Game is over, winner is " + Game.getWinner());
			
		}
		
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
		
		try
		{
			if(millCreated(row,col))
			{			
				while(!removeOpponentToken(player)){}
				System.out.println(game);
			}
		} catch (InvalidCoordinatesException e)
		{
			System.out.println("Invalid cell coordinate");
			return false;
		}
		return true;
	}


	private static boolean moveToken(Player player) {
		
		System.out.println("Player " + player.getName());
		System.out.print("Enter Token coodinates as fromRow fromCol toRow toCol:");
		int row,col,toRow,toCol;
		
		try {
			int[] coordinates = obtainUserInput(4);
			row = coordinates[0];
			col = coordinates[1];
			toRow = coordinates[2];
			toCol = coordinates[3];
			game.moveTokenTo(player, row, col, toRow, toCol);
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
		
		
		try
		{
			if(millCreated(row,col))
			{			
				while(!removeOpponentToken(player)){}
				System.out.println(game);
			}
		} catch (InvalidCoordinatesException e)
		{
			System.out.println("Invalid cell coordinate");
			return false;
		}
		return true;
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

	private static boolean millCreated(int row, int col) throws InvalidCoordinatesException {
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
			if(side.equals(Side.X)){
				sideToRemove = Side.O;
			}
			if(side.equals(Side.O)){
				sideToRemove = Side.X;
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
