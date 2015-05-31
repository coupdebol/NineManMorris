package controllers;

import java.util.List;

import models.BoardGraph;
import models.BoardNode;
import enums.Side;
import exceptions.InvalidCoordinatesException;

/**
 * The Board represent the board of the game and uses boardGraph to store the
 * values, the board class is used to perfom overall check and operations on the
 * board.
 * 
 * @author Debol
 *
 */
public class Board
{

	private BoardGraph boardGraph;

	public Board()
	{
		boardGraph = new BoardGraph();

	}

	/**
	 * Checks that the given coordinates exist on the board
	 * 
	 * @param row
	 *            row of location to check
	 * @param col
	 *            col of location to check
	 * @return true if the location exists on the board
	 */
	public boolean isValidCoordinate(int row, int col)
	{
		for (BoardNode node : boardGraph.getNode())
		{
			int r = node.getRow();
			int c = node.getCol();
			if (c == col && r == row)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Changes the side of the token on the board at the given location. Allows
	 * to remove or place of token on the board. An empty location is
	 * represented by a token of side None,
	 * 
	 * @param row
	 *            row of location to change
	 * @param col
	 *            col of location to change
	 * @param side
	 *            the new side of the location
	 * @throws InvalidCoordinatesException
	 *             if the location does not exists on the board
	 */
	public void changeSide(int row, int col, Side side)
			throws InvalidCoordinatesException
	{
		BoardNode node = findNode(row, col);
		node.setSide(side);
	}

	/**
	 * return the BoardNode stored at the given location
	 * 
	 * @param row
	 *            row of the location
	 * @param col
	 *            col of the location
	 * @return the boardNode object stored at the location
	 * @throws InvalidCoordinatesException
	 *             if the location does not exist on the board
	 */
	public BoardNode findNode(int row, int col)
			throws InvalidCoordinatesException
	{
		List<BoardNode> nodes = boardGraph.getNode();
		for (BoardNode node : nodes)
		{
			if (node.getRow() == row)
			{
				if (node.getCol() == col)
				{
					return node;
				}
			}
		}
		throw new InvalidCoordinatesException();
	}

	/**
	 * verifies that if the node is part of a mill
	 * 
	 * @param node
	 *            the node on the board to check
	 * @return true if the node belongs to a mill
	 */
	public boolean hasMill(BoardNode node)
	{
		Side s = node.getSide();
		boolean result = true;
		for (List<BoardNode> mill : node.mills)
		{
			for (BoardNode childNode : mill)
			{
				if (!childNode.getSide().equals(s))
				{
					result = false;
				}
			}
			if (result)
				return true;
		}
		return false;
	}

	/**
	 * determines the number of token of the given side on the board
	 * 
	 * @param side
	 *            side of the token to check
	 * @return the number of token on the board of the given side
	 */
	public int howManyMen(Side side)
	{
		int num = 0;
		for (BoardNode node : boardGraph.getNode())
		{
			if (node.getSide().equals(side))
			{
				num++;
			}
		}
		return num;
	}

	/**
	 * return a string the represent geraphcally the board and its tokens
	 */
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
		for (BoardNode node : boardGraph.getNode())
		{
			int idx = coordinatesToCharNumber(node.getRow(), node.getCol());
			if (node.getSide().equals(Side.O))
				builder.replace(idx, idx + 1, "O");
			if (node.getSide().equals(Side.X))
				builder.replace(idx, idx + 1, "X");
		}
		return builder.toString();

	}

	/**
	 * used by the toString method to determine the location of the string to
	 * add the token
	 * 
	 * @param row
	 *            row of the token on the board
	 * @param col
	 *            col of the token of the board
	 * @return index of the string for the given location
	 */
	private int coordinatesToCharNumber(int row, int col)
	{
		if (row == 1 && col == 1)
			return 1;
		if (row == 1 && col == 2)
			return 19;
		if (row == 1 && col == 3)
			return 37;
		if (row == 2 && col == 1)
			return 87;
		if (row == 2 && col == 2)
			return 99;
		if (row == 2 && col == 3)
			return 111;
		if (row == 3 && col == 1)
			return 173;
		if (row == 3 && col == 2)
			return 179;
		if (row == 3 && col == 3)
			return 185;
		if (row == 4 && col == 1)
			return 241;
		if (row == 4 && col == 2)
			return 247;
		if (row == 4 && col == 3)
			return 253;
		if (row == 4 && col == 4)
			return 265;
		if (row == 4 && col == 5)
			return 271;
		if (row == 4 && col == 6)
			return 277;
		if (row == 5 && col == 1)
			return 333;
		if (row == 5 && col == 2)
			return 339;
		if (row == 5 && col == 3)
			return 345;
		if (row == 6 && col == 1)
			return 407;
		if (row == 6 && col == 2)
			return 419;
		if (row == 6 && col == 3)
			return 431;
		if (row == 7 && col == 1)
			return 481;
		if (row == 7 && col == 2)
			return 499;
		if (row == 7 && col == 3)
			return 517;

		return -1;

	}

	public BoardGraph getBoardGraph()
	{
		return boardGraph;
	}

}
