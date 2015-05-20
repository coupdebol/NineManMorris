/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Token {
	
	private int row;
	private int col;
	private Side side;
	
	public Token(int row, int col, Side side) {
		this.col = col;
		this.row = row;
		this.side = side;
	}

	public Token(Side side) {
		this.side = side;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

}
