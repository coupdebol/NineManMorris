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
 * @author Debol
 *
 */
public class Game
{

	private static Player playerO;
	private static Player playerX;
	private static Board board;
	private static Side lastSidePlayed;

	public Game()
	{
		playerO = new HumanPlayer(Side.O);
		playerX = new HumanPlayer(Side.X);
		board = new Board();
		lastSidePlayed = Side.X;
	}

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

	public void slideTokenTo(Player player, int rowFrom, int colFrom,
			int rowTo, int colTo) throws InvalidCoordinatesException,
			IllegalMoveException
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
				board.changeSide(rowFrom, colFrom,Side.NONE);
				board.changeSide(rowTo,colTo,player.getSide());
				lastSidePlayed = player.getSide();
			}
			else
			{
				throw new IllegalMoveException();
			}
	
		} else
		{
			throw new IllegalMoveException();
		}
	
	}

	public void moveTokenTo(Player player, int rowFrom, int colFrom, int rowTo,
			int colTo) throws InvalidCoordinatesException, IllegalMoveException, DestinationNotEmptyException, WrongSideTokenProvenanceException
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
			if(!board.hasMill(node))
			{
				board.changeSide(row, col,Side.NONE);
			}else
			{
				throw new TokenBelongsToAMillException();
			}
		} else
		{
			throw new InvalidCoordinatesException();
		}
	
	}

	public static boolean hasMill(int row, int col) throws InvalidCoordinatesException
	{
		return board.hasMill(board.findNode(row,col));
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

	@Override
	public String toString()
	{
		return board.toString() + "\n----------------\n" +getGameState()+ "\n----------------\n";
	}

	public static Side getWinner()
	{
		if(!(playerO.hasToken())){
			if (board.howManyMen(Side.O) == 2)
			{
				return Side.X;
			}
		}
		if(!(playerO.hasToken())){
			if (board.howManyMen(Side.X) == 2)
			{
				return Side.O;
			}
		}
		return Side.NONE;
	}
	
	public String getGameState()
	{
		StringBuilder s = new StringBuilder();
		s.append(lastSidePlayed + " ");
		s.append(playerO.getSide()+ " ");
		s.append(playerO.getTokenBagSize()+ " ");
		s.append(playerX.getSide()+ " ");
		s.append(playerX.getTokenBagSize()+ " ");
		for(BoardNode node : board.getBoardGraph().getNode())
		{
			if(!node.getSide().equals(Side.NONE))
			{
				s.append(node.getSide() + " ");
				s.append(node.getRow() + " " + node.getCol()+ " ");
			}
		}
		return s.toString();
	}

}
