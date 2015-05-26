package models;
import java.util.Objects;

import enums.Side;

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
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Token))
			return false;
		Token t = (Token) o;
		if( t.getCol()!=this.getCol() )
			return false;
		if( t.getRow()!=this.getRow() )
			return false;
		return true;
			
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(this.getRow(),this.getCol());
	}
	
	@Override
	public String toString()
	{
		
		return this.getSide()+" row: " + this.getRow() + " col: " + this.getCol();
	}

}
