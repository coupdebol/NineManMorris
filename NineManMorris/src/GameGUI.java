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
				System.out.println(game);
				while(!placeToken(black)){}
				System.out.println(game);
			}
			System.out.println("all tokens have now been placed on the board");
			
		}
		
	}
	
	private static boolean placeToken(Player player)
	{
		System.out.println("Player " + player.getName());
		System.out.print("Enter Token coodinates as row col:");
		
		try{
			String input = br.readLine();
			String[] inputs = input.split(" ");
			if(inputs.length < 2){
				throw new NumberFormatException();
			}
			int row = Integer.parseInt(inputs[0]);
			int col = Integer.parseInt(inputs[1]);
			game.placeTokenAt(player, row, col);			
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
			return false;
		} catch (NumberFormatException e) {
			System.out.println("Enter two numbers separated by a space");
			return false;
		} catch (TokenAlredyPlacedException e){
			System.out.println("There is already a token at that location");
			return false;
		} catch (InvalidCoordinatesException e) {
			System.out.println("Invalid cell coordinate");
			return false;
		}		
		return true;
	}

}
