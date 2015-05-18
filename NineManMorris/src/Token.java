/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Token {
	
	private int[] coodinates;
	private Side side;
	private Move[] availableMoves;
	
	public Token(int[] coordinates) {
		this.coodinates = coordinates;
	}

	public Token(Side side) {
		this.side = side;
	}
	
	
}
