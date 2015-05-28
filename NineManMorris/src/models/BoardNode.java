package models;
import java.util.ArrayList;
import java.util.List;

import enums.Side;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class BoardNode {

	private Token token;
	public List<BoardNode> nodes = new ArrayList<>();
	public List<List<BoardNode>> mills = new ArrayList<>();	
	
	public BoardNode(int row, int col) {
		token = new Token(row,col,Side.NONE);
	}
		
	public int getRow(){
		return token.getRow();
	}
	
	public int getCol(){
		return token.getCol();
	}
	
	public Side getSide()
	{
		return token.getSide();
	}
	
	public void attachTo(BoardNode... nodes)
	{
		for(BoardNode node : nodes ){
			this.nodes.add(node);
		}	
	}
	public void millWith(BoardNode... nodes)
	{
		List<BoardNode> mill = new ArrayList<>();
		for(BoardNode node : nodes ){
			mill.add(node);
		}
		this.mills.add(mill);
	}

	public void setSide(Side side)
	{
		token.setSide(side);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		if(!(o instanceof BoardNode))
			return false;
		BoardNode node = (BoardNode) o;
		if(node.token == null)
			return false;
		if(node.token.getRow()!=this.token.getRow())
			return false;
		if(node.token.getCol()!=this.token.getCol())
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return token.getRow() + " " + token.getCol() + "->" + nodes.size() + "nodes";
	}

}
