import java.util.HashSet;
import java.util.Set;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Mill {
	private Set<Token> tokens = new HashSet<>();
	private boolean full = false;
	
	public Mill(Token... token)
	{
		for(Token t: token)
		{
			tokens.add(t);
		}
	}
	
	public Set<Token> getTokens() {
		return tokens;
	}
	public void setTokens(Set<Token> tokens) {
		this.tokens = tokens;
	}
	public boolean isFull() {
		return full;
	}
	public void setFull(boolean full) {
		this.full = full;
	}
	

}
