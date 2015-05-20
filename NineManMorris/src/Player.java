
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
	private String name;
	
	
	/**
	 * A player is created with a full bag of token ready to be placed on the board
	 * @param side
	 */
	
	public Player(Side side){
		for(int i = 0 ; i < 9 ; i++){
			Token token = new Token(side);
			tokenBag.add(token);
		}
		if(side.equals(Side.WHITE))
		{
			this.setName("O");
		}
		if(side.equals(Side.BLACK))
		{
			this.setName("X");
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
	
	public boolean hasToken()
	{
		return tokenBag.size() > 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
