

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
		if (board.getToken(row, col).getSide().equals(Side.NONE))
		{
			Token t = player.getToken();
			t.setCol(col);
			t.setRow(row);
			board.addToken(t);
			lastSidePlayed = player.getSide();
		} else
		{
			throw new TokenAlredyPlacedException();
		}
	}

	public static boolean hasMill(int row, int col) throws InvalidCoordinatesException
	{
		Token t = board.getToken(row, col);
		return board.hasMill(t);
	}

	public void moveTokenTo(Player player, int rowFrom, int colFrom, int rowTo,
			int colTo) throws InvalidCoordinatesException, IllegalMoveException
	{
		// take the token from the board
		Token fromToken = board.getToken(rowFrom, colFrom);
		if (fromToken.getSide().equals(player.getSide()))
		{
			if (board.getToken(rowTo, colTo).getSide().equals(Side.NONE))
			{
				board.removeToken(rowFrom, colFrom);
				fromToken.setRow(rowTo);
				fromToken.setCol(colTo);
				board.addToken(fromToken);
				lastSidePlayed = player.getSide();
			}

		} else
		{
			throw new IllegalMoveException();
		}

	}
	
	public void slideTokenTo(Player player, int rowFrom, int colFrom,
			int rowTo, int colTo) throws InvalidCoordinatesException,
			IllegalMoveException
	{
		// take the token from the board
		Token fromToken = board.getToken(rowFrom, colFrom);
		if (fromToken.getSide().equals(player.getSide()))
		{
			boolean validMove = board.findNode(rowFrom, colFrom).nodes
					.contains(board.findNode(rowTo, colTo));
			if (board.getToken(rowTo, colTo).getSide().equals(Side.NONE)
					&& validMove)
			{
				board.removeToken(rowFrom, colFrom);
				fromToken.setRow(rowTo);
				fromToken.setCol(colTo);
				fromToken.setSide(player.getSide());
				board.addToken(fromToken);
				lastSidePlayed = player.getSide();
			}

		} else
		{
			throw new IllegalMoveException();
		}

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

	public void removeToken(int row, int col, Side side)
			throws InvalidCoordinatesException
	{
		if (!board.isValidCoordinate(row, col))
		{
			throw new InvalidCoordinatesException();
		}
		Side tokenSide = board.getToken(row, col).getSide();
		if (tokenSide.equals(side))
		{
			board.removeToken(row, col);
		} else
		{
			throw new InvalidCoordinatesException();
		}

	}

	public static Side getWinner()
	{
		if(!(playerO.hasToken())){
			if (board.howManyMen(Side.O) == 2)
			{
				return Side.O;
			}
		}
		if(!(playerO.hasToken())){
			if (board.howManyMen(Side.X) == 2)
			{
				return Side.X;
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
				Token t = node.getToken();
				s.append(t.getSide() + " ");
				s.append(t.getRow() + " " + t.getCol()+ " ");
			}
		}
		return s.toString();
	}

}
