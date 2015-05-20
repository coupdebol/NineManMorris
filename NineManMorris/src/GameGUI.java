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
		Player white = game.getWhite();
		Player black = game.getBlack();
		Board board = game.getBoard();
		System.out.println(board.toString());
		String s = "";
		
		while(true)
		{
			
			System.out.println("Player 1 starts");
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(s.equalsIgnoreCase("a"))
			{
				System.out.println("aborting the game");
				break;
			}
			
				Token whiteToken = white.getToken();
				
			
		}
		
	}

}
