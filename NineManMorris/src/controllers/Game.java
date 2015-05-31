package controllers;

import models.BoardNode;
import models.HumanPlayer;
import models.Player;
import enums.Side;
import exceptions.DestinationNotEmptyException;
import exceptions.EmptyBagException;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinatesException;
import exceptions.TokenAlredyPlacedException;
import exceptions.TokenBelongsToAMillException;
import exceptions.WrongSideTokenProvenanceException;

/**
 * 
 */

/**
 * This class represent the game with its two players and the board. The side of
 * the last player that has played is stored to ease the game retrieval function
 * 
 * @author Debol
 *
 */
public class Game
{

	private static Player playerO;
	private static Player playerX;
	private static Board board;
	private static Side lastSidePlayed;

	/**
	 * Default contructor creates two human player and initialise a default
	 * board with O playing first.
	 */
	public Game()
	{
		playerO = new HumanPlayer(Side.O);
		playerX = new HumanPlayer(Side.X);
		board = new Board();
		lastSidePlayed = Side.X;
	}

	/**
	 * Take a token form the token bad of the player and place it on the board
	 * at the given location
	 * 
	 * @param player
	 *            to take a token from
	 * @param row
	 *            of the location to add the token to
	 * @param col
	 *            of the location to add the token to
	 * @throws TokenAlredyPlacedException
	 *             a token is already on the location
	 * @throws InvalidCoordinatesException
	 *             the location does not exist on the board
	 * @throws EmptyBagException
	 *             the player has no token in his bag to be removed
	 */
	public void placeTokenAt(Player player, int row, int col)
			throws TokenAlredyPlacedException, InvalidCoordinatesException,
			EmptyBagException
	{
		if (!board.isValidCoordinate(row, col))
		{
			throw new InvalidCoordinatesException();
		}
		if (board.findNode(row, col).getSide().equals(Side.NONE))
		{
			player.takeToken();
			board.changeSide(row, col, player.getSide());
			lastSidePlayed = player.getSide();
		} else
		{
			throw new TokenAlredyPlacedException();
		}
	}

	/**
	 * Selects a token on the board and move the token to the new location. The
	 * new location must be connected by a line.
	 * 
	 * @param player
	 *            to select a token from
	 * @param rowFrom
	 *            row of token to move
	 * @param colFrom
	 *            col of token to move
	 * @param rowTo
	 *            row of destination of the token
	 * @param colTo
	 *            col of destination of the token
	 * @throws InvalidCoordinatesException
	 *             if any of the two coordinate are invalid
	 * @throws IllegalMoveException
	 *             if the move is not legal ( i.e. the locations are not
	 *             connected...)
	 * @throws WrongSideTokenProvenanceException
	 *             if the token selected is not of the correct side
	 */
	public void slideTokenTo(Player player, int rowFrom, int colFrom,
			int rowTo, int colTo) throws InvalidCoordinatesException,
			IllegalMoveException, WrongSideTokenProvenanceException
	{
		// take the token from the board
		BoardNode node = board.findNode(rowFrom, colFrom);
		BoardNode nodeTo = board.findNode(rowTo, colTo);
		if (node.getSide().equals(player.getSide()))
		{
			boolean validMove = node.nodes.contains(nodeTo);
			if (board.findNode(rowTo, colTo).getSide().equals(Side.NONE)
					&& validMove)
			{
				board.changeSide(rowFrom, colFrom, Side.NONE);
				board.changeSide(rowTo, colTo, player.getSide());
				lastSidePlayed = player.getSide();
			} else
			{
				throw new IllegalMoveException();
			}

		} else
		{
			throw new WrongSideTokenProvenanceException();
		}

	}

	/**
	 * Select a token and place it to the new location, the new location can be
	 * any location on the board
	 * 
	 * @param player
	 *            to "hop" the token
	 * @param rowFrom
	 *            row of token to move
	 * @param colFrom
	 *            col of token to move
	 * @param rowTo
	 *            row of destination of the token
	 * @param colTo
	 *            col of destination of the token
	 * @throws InvalidCoordinatesException
	 *             if any coodinate is not valid
	 * @throws IllegalMoveException
	 *             if the move is illegal
	 * @throws DestinationNotEmptyException
	 *             if the destination location has a token
	 * @throws WrongSideTokenProvenanceException
	 *             if the selected token is of the wrong side
	 */
	public void moveTokenTo(Player player, int rowFrom, int colFrom, int rowTo,
			int colTo) throws InvalidCoordinatesException,
			IllegalMoveException, DestinationNotEmptyException,
			WrongSideTokenProvenanceException
	{
		BoardNode nodeFrom = board.findNode(rowFrom, colFrom);
		BoardNode nodeTo = board.findNode(rowTo, colTo);
		if (nodeFrom.getSide().equals(player.getSide()))
		{
			if (nodeTo.getSide().equals(Side.NONE))
			{
				board.changeSide(rowFrom, colFrom, Side.NONE);
				board.changeSide(rowTo, colTo, player.getSide());
				lastSidePlayed = player.getSide();
			} else
			{
				throw new DestinationNotEmptyException();
			}

		} else
		{
			throw new WrongSideTokenProvenanceException();
		}

	}

	/**
	 * Selects a token and remove it from the board, checks the the token does
	 * not belong to a mill
	 * 
	 * @param row
	 *            row of token to remove
	 * @param col
	 *            col of token to remove
	 * @param side
	 *            side of the token to remove
	 * @throws InvalidCoordinatesException
	 * @throws TokenBelongsToAMillException
	 */
	public void removeToken(int row, int col, Side side)
			throws InvalidCoordinatesException, TokenBelongsToAMillException
	{
		if (!board.isValidCoordinate(row, col))
		{
			throw new InvalidCoordinatesException();
		}
		BoardNode node = board.findNode(row, col);
		Side tokenSide = node.getSide();
		if (tokenSide.equals(side))
		{
			if (!board.hasMill(node))
			{
				board.changeSide(row, col, Side.NONE);
			} else
			{
				throw new TokenBelongsToAMillException();
			}
		} else
		{
			throw new InvalidCoordinatesException();
		}

	}

	/**
	 * Checks wether the location belong to a mill on the board
	 * 
	 * @param row
	 *            row of location to check
	 * @param col
	 *            col of location to check
	 * @return true if the location belongs to a mill
	 * @throws InvalidCoordinatesException
	 *             if the coordinates don't exist on the board
	 */
	public static boolean hasMill(int row, int col)
			throws InvalidCoordinatesException
	{
		return board.hasMill(board.findNode(row, col));
	}

	public static Player getPlayerO()
	{
		return playerO;
	}

	public static void setWhite(Player white)
	{
		Game.playerO = white;
	}

	public static Player getPlayerX()
	{
		return playerX;
	}

	public static void setBlack(Player black)
	{
		Game.playerX = black;
	}

	public static Board getBoard()
	{
		return board;
	}

	public static void setBoard(Board board)
	{
		Game.board = board;
	}

	/**
	 * returns a graphical representation of the board followed by a code that
	 * represent the game state to be used to resume a game
	 */
	@Override
	public String toString()
	{
		return board.toString() + "\n----------------\n" + getGameState()
				+ "\n----------------\n";
	}

	/**
	 * returns the Side of the game that has won the game, if no winner exists
	 * yet, None is returned
	 * 
	 * @return the side of the game that has won the game, None if no winner yet
	 */
	public static Side getWinner()
	{
		if (!(playerO.hasToken()))
		{
			if (board.howManyMen(Side.O) == 2)
			{
				return Side.X;
			}
		}
		if (!(playerO.hasToken()))
		{
			if (board.howManyMen(Side.X) == 2)
			{
				return Side.O;
			}
		}
		return Side.NONE;
	}

	/**
	 * Create a string that represent the state of the game. it contains in
	 * order and separated by spaces the lastplayed side, the side of the token
	 * bag followed by its size, for both players, a set of side and row and
	 * columns. An example such as : X O 1 X 2 X 1 1 O 1 2 means that Last
	 * player was X X has 1 token in his bag, O has 2 tokens in his bag, the
	 * board contains a X token at location 1 1 and a O token at location 1 2
	 * 
	 * @return the string that represent the game state
	 */
	public String getGameState()
	{
		StringBuilder s = new StringBuilder();
		s.append(lastSidePlayed + " ");
		s.append(playerO.getSide() + " ");
		s.append(playerO.getTokenBagSize() + " ");
		s.append(playerX.getSide() + " ");
		s.append(playerX.getTokenBagSize() + " ");
		for (BoardNode node : board.getBoardGraph().getNode())
		{
			if (!node.getSide().equals(Side.NONE))
			{
				s.append(node.getSide() + " ");
				s.append(node.getRow() + " " + node.getCol() + " ");
			}
		}
		return s.toString();
	}

}
