package jantony.ninemanmorris.models;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Mill {
	private List<Token> tokens = new ArrayList<>();
	
	public Mill(Token... token)
	{
		for(Token t: token)
		{
			tokens.add(t);
		}
	}
	
	public void placeToken(Token token)
	{
		List<Token> tokenSet = new ArrayList<>();
		for(Token t:tokens)
		{
			if(t.equals(token))
			{
				
				tokenSet.add(token);
			}
			else
			{
				tokenSet.add(t);
			}
		}
		tokens = tokenSet;
	}
	
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	public boolean isFull() {
		Side s = tokens.get(0).getSide();
		for(int i = 1 ; i < tokens.size() ; i++){
			if(!tokens.get(i).getSide().equals(s))
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		String result = "Mill at: ";
		for(Token t: tokens)
		{
			result += t.getRow() + " " + t.getCol() + ",";
		}
		return result;
	}

}
