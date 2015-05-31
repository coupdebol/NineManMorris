package models;

import java.util.ArrayList;
import java.util.List;

import enums.Side;

/**
 * 
 */

/**
 * This class represent a intersection of the board, it holds a token for of
 * either a player side or none to represent a empty location the Token of the
 * board contains a location and each board node contains a list of node for the
 * "connected" node (i.e. the location that a token can move to from the
 * location of the node) and a list of node that represent the node that are on
 * the same mill
 * 
 * @author Debol
 *
 */
public class BoardNode
{

	private Token token;
	public List<BoardNode> nodes = new ArrayList<>();
	public List<List<BoardNode>> mills = new ArrayList<>();

	public BoardNode(int row, int col)
	{
		token = new Token(row, col, Side.NONE);
	}

	public int getRow()
	{
		return token.getRow();
	}

	public int getCol()
	{
		return token.getCol();
	}

	public Side getSide()
	{
		return token.getSide();
	}

	/**
	 * Add the nodes to the current node to represent the possible node a token
	 * can move ("slide") to
	 * 
	 * @param nodes
	 *            the Boardnode the current node can have its token "moved" to
	 */
	public void attachTo(BoardNode... nodes)
	{
		for (BoardNode node : nodes)
		{
			this.nodes.add(node);
		}
	}

	/**
	 * For each calls of the method a new mill is created, this represent the
	 * nodes to check when verifiyng that a mill exists The nodes are the other
	 * node that are to be checked on the mills that a location belongs to
	 * 
	 * @param nodes
	 *            the nodes to be checked for mill checking
	 */
	public void millWith(BoardNode... nodes)
	{
		List<BoardNode> mill = new ArrayList<>();
		for (BoardNode node : nodes)
		{
			mill.add(node);
		}
		this.mills.add(mill);
	}

	public void setSide(Side side)
	{
		token.setSide(side);
	}

	/**
	 * Two nodes are the equal when their token have the same location, the side
	 * of the token is not used for equality
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		if (!(o instanceof BoardNode))
			return false;
		BoardNode node = (BoardNode) o;
		if (node.token == null)
			return false;
		if (node.token.getRow() != this.token.getRow())
			return false;
		if (node.token.getCol() != this.token.getCol())
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return token.getRow() + " " + token.getCol() + "->" + nodes.size()
				+ "nodes";
	}

}
