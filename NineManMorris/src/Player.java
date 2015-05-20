
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public abstract class Player {
	
	private List<Token> tokenBag = new ArrayList<>();
	
	
	/**
	 * A player is created with a full bag of token ready to be placed on the board
	 * @param side
	 */
	
	public Player(Side side){
		for(int i = 0 ; i < 9 ; i++){
			Token token = new Token(side);
			tokenBag.add(token);
		}
	}
	
	public Token getToken()
	{
		if (tokenBag.isEmpty())
		{	
			return null;
		}else
		{
			return tokenBag.remove(0);
		}
	}
}
