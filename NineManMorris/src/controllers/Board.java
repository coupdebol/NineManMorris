package controllers;
import java.util.List;

import models.BoardGraph;
import models.BoardNode;
import enums.Side;
import exceptions.InvalidCoordinatesException;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Board {
	
	private BoardGraph boardGraph;
	//private List<Mill> mills;
	
	
	public Board()
	{
		boardGraph = new BoardGraph();
		
	}
	
	public boolean isValidCoordinate(int row, int col)
	{
		for(BoardNode node:boardGraph.getNode())
		{
			int r = node.getRow();
			int c = node.getCol();
			if(c==col && r==row){
				return true;
			}
		}
		return false;
	}
	

	public void changeSide(int row, int col, Side side) throws InvalidCoordinatesException
	{
		BoardNode node = findNode(row,col);
		node.setSide(side);
	}
	
	public BoardNode findNode(int row, int col) throws InvalidCoordinatesException
	{
		List<BoardNode> nodes = boardGraph.getNode();
		for(BoardNode node:nodes)
		{
			if(node.getRow()== row)
			{
				if(node.getCol()== col)
				{
					return node;
				}
			}
		}
		throw new InvalidCoordinatesException();
	}
	
	public boolean hasMill(BoardNode node) {
		List<BoardNode> graphNodes = boardGraph.getNode();
		int idx = graphNodes.indexOf(node);
		BoardNode thisNode = graphNodes.get(idx);
		Side s = node.getSide();
		for(BoardNode childNode: thisNode.nodes)
		{
			if(!childNode.getSide().equals(s))
			{
				return false;
			}
		}
		return true;
	}
	
	public int howManyMen(Side side)
	{
		int num = 0;
		for(BoardNode node: boardGraph.getNode())
		{
			if(node.getSide().equals(side))
			{
				num++;
			}
		}
		return num;
	}
	

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("[ ]---------------[ ]---------------[ ]\n");
		builder.append(" |                 |                 | \n");
		builder.append(" |    [ ]---------[ ]---------[ ]    | \n");
		builder.append(" |     |           |           |     | \n");
		builder.append(" |     |    [ ]---[ ]---[ ]    |     | \n");
		builder.append(" |     |     |           |     |     | \n");
		builder.append("[ ]---[ ]---[ ]         [ ]---[ ]---[ ]\n");
		builder.append(" |     |     |           |     |     | \n");
		builder.append(" |     |    [ ]---[ ]---[ ]    |     | \n");
		builder.append(" |     |           |           |     | \n");
		builder.append(" |    [ ]---------[ ]---------[ ]    | \n");
		builder.append(" |                 |                 | \n");
		builder.append("[ ]---------------[ ]---------------[ ]\n");
		for(BoardNode node: boardGraph.getNode() )
		{
			int idx = coordinatesToCharNumber(node.getRow(),node.getCol());
			if(node.getSide().equals(Side.O))
				builder.replace(idx, idx+1, "O");
			if(node.getSide().equals(Side.X))
				builder.replace(idx, idx+1, "X");
		}
		return builder.toString();
		
	}
	
	private int coordinatesToCharNumber(int row,int col)
	{
		if(row == 1 && col == 1)
			return 1;
		if(row == 1 && col == 2)
			return 19;
		if(row == 1 && col == 3)
			return 37;
		if(row == 2 && col == 1)
			return 87;
		if(row == 2 && col == 2)
			return 99;
		if(row == 2 && col == 3)
			return 111;
		if(row == 3 && col == 1)
			return 173;
		if(row == 3 && col == 2)
			return 179;
		if(row == 3 && col == 3)
			return 185;
		if(row == 4 && col == 1)
			return 241;
		if(row == 4 && col == 2)
			return 247;
		if(row == 4 && col == 3)
			return 253;
		if(row == 4 && col == 4)
			return 265;
		if(row == 4 && col == 5)
			return 271;
		if(row == 4 && col == 6)
			return 277;
		if(row == 5 && col == 1)
			return 333;
		if(row == 5 && col == 2)
			return 339;
		if(row == 5 && col == 3)
			return 345;
		if(row == 6 && col == 1)
			return 407;
		if(row == 6 && col == 2)
			return 419;
		if(row == 6 && col == 3)
			return 431;
		if(row == 7 && col == 1)
			return 481;
		if(row == 7 && col == 2)
			return 499;
		if(row == 7 && col == 3)
			return 517;
		
		return -1;
		
	}
	
	public BoardGraph getBoardGraph()
	{
		return boardGraph;
	}

}
