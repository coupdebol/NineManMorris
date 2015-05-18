import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Board {
	private List<Token> tokens = new ArrayList<>();
	private BoardGraph boardGraph;
	private boolean allTokenPlaced = false;
	
	public boolean placeToken(int[] coordinates, Token token)
	{
		//if tokens reach 18 => allTokenPlaced = true
		return true;
	}
	
	public Token getToken(int[] coordinates)
	{
		return new Token(coordinates);
	}
	
	public boolean moveToke(Token token, Move move)
	{
		return true;
	}
	
	public boolean hasFullLine()
	{
		return false;
	}
}
