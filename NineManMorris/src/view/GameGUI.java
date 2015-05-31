package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import controllers.Board;
import controllers.Game;
import models.HumanPlayer;
import models.Player;
import enums.MoveType;
import enums.Side;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinatesException;
import exceptions.InvalidInputException;
import exceptions.TokenAlredyPlacedException;
import exceptions.TokenBelongsToAMillException;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class GameGUI
{

	private static Game game = new Game();
	private static BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));
	private static Side lastSidePlayed = Side.X;

	/**
	 * This object is used to parse the argument when starting a game from a
	 * previous state
	 *
	 */
	public static class GameState
	{
		public int bagX;
		public int bagO;
		public List<Intersection> tokenX = new ArrayList<>();
		public List<Intersection> tokenO = new ArrayList<>();;

	}

	/**
	 * Defines intersection on the board
	 * 
	 * @author Debol
	 *
	 */
	public static class Intersection
	{
		public int row;
		public int col;
	}

	/**
	 * Read the arguments and attempt to parse into a game state, the game state
	 * tells what was the last player side, the number of token in each side,
	 * and the side of all token on the board.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args)
	{
		GameState state = new GameState();
		try
		{
			if (args.length > 5)
			{
				state.bagO = Integer.parseInt(args[2]);
				state.bagX = Integer.parseInt(args[4]);
				for (int i = 5; i < args.length - 2; i += 3)
				{
					Intersection intersection = new Intersection();
					intersection.row = Integer.parseInt(args[i + 1]);
					intersection.col = Integer.parseInt(args[i + 2]);
					if (args[i].equals("O"))
					{
						state.tokenO.add(intersection);
					}
					if (args[i].equals("X"))
					{
						state.tokenX.add(intersection);
					}
				}

				lastSidePlayed = Side.valueOf(args[0]);
				Player playerO = new HumanPlayer(Side.O);
				playerO.setTokenBagSize(state.bagO);
				Player playerX = new HumanPlayer(Side.X);
				playerX.setTokenBagSize(state.bagX);
				Game.setWhite(playerO);
				Game.setBlack(playerX);
				Board board = new Board();
				for (Intersection inter : state.tokenO)
				{
					board.changeSide(inter.row, inter.col, Side.O);
				}
				for (Intersection inter : state.tokenX)
				{
					board.changeSide(inter.row, inter.col, Side.X);
				}
				Game.setBoard(board);
				init2PlayerGame();
			} else
			{
				// no arguments, start with empty board
				init2PlayerGame();
			}

			// can't parse the arugment, no need to run the game
		} catch (Exception e)
		{
			System.out
					.println("Something went wrong when starting the game, please restart the game with no arguments");
		}

		// game is over, time to exit
		System.exit(0);

	}

	/**
	 * Starts a new game out of the current game state, the game may have been
	 * initialised
	 */
	private static void init2PlayerGame()
	{
		Player playerO = Game.getPlayerO();
		Player playerX = Game.getPlayerX();

		System.out.println(game);

		boolean gameOver = false;
		while (!gameOver)
		{
			if (lastSidePlayed.equals(Side.X))
			{
				while (!playTurn(playerO))
				{
				}
				gameOver = Game.getWinner().equals(Side.NONE) ? false : true;
				lastSidePlayed = Side.O;
			}
			if (!gameOver)
			{
				if (lastSidePlayed.equals(Side.O))
				{
					while (!playTurn(playerX))
					{
					}
					gameOver = Game.getWinner().equals(Side.NONE) ? false
							: true;
					lastSidePlayed = Side.X;
				}
			}
		}
		System.out.println("Game is over, winner is " + Game.getWinner());

	}

	private static boolean playTurn(Player player)
	{
		if (player.hasToken())
		{
			return moveToken(player, MoveType.PLACE);
		} else
		{
			int men = Game.getBoard().howManyMen(player.getSide());
			if (men > 3)
			{
				return moveToken(player, MoveType.SLIDE);
			}
			if (men == 3)
			{
				return moveToken(player, MoveType.JUMP);
			}
			if (men <= 2)
			{
				return true;
			}
		}
		return false;
	}

	private static boolean moveToken(Player player, MoveType moveType)
	{
		int row, col, toRow, toCol;
		int rowCheck = 0, colCheck = 0;
		System.out.println("Player " + player.getName());
		int[] coordinates;

		try
		{
			switch (moveType)
			{
			case PLACE:
				System.out.print("Place - Enter Token coodinates as Row Col:");
				coordinates = obtainUserInput(2);
				row = coordinates[0];
				col = coordinates[1];
				rowCheck = row;
				colCheck = col;
				game.placeTokenAt(player, row, col);
				break;
			case SLIDE:
				System.out
						.print("Slide - Enter Token coodinates as fromRow fromCol toRow toCol:");
				coordinates = obtainUserInput(4);
				row = coordinates[0];
				col = coordinates[1];
				toRow = coordinates[2];
				toCol = coordinates[3];
				rowCheck = toRow;
				colCheck = toCol;
				game.slideTokenTo(player, row, col, toRow, toCol);
				break;
			case JUMP:
				System.out
						.print("Jump - Enter Token coodinates as fromRow fromCol toRow toCol:");
				coordinates = obtainUserInput(4);
				row = coordinates[0];
				col = coordinates[1];
				toRow = coordinates[2];
				toCol = coordinates[3];
				rowCheck = toRow;
				colCheck = toCol;
				game.moveTokenTo(player, row, col, toRow, toCol);
				break;
			case REMOVE:
				System.out.print("Remove - Enter Token coodinates as Row Col:");
				coordinates = obtainUserInput(2);
				row = coordinates[0];
				col = coordinates[1];
				Side side = player.getSide();
				Side sideToRemove = null;
				if (side.equals(Side.X))
				{
					sideToRemove = Side.O;
				}
				if (side.equals(Side.O))
				{
					sideToRemove = Side.X;
				}
				game.removeToken(row, col, sideToRemove);
				break;
			default:
				break;
			}
			System.out.println(game);
		} catch (TokenAlredyPlacedException e)
		{
			System.out.println("There is already a token at that location");
			return false;
		} catch (InvalidCoordinatesException e)
		{
			System.out.println("Invalid cell coordinate");
			return false;
		} catch (InvalidInputException e)
		{
			System.out.println("Invalid input");
			return false;
		} catch (IllegalMoveException e)
		{
			System.out.println("Illegal Move");
			return false;
		} catch (TokenBelongsToAMillException e)
		{
			System.out.println("token belongs to a mill, cannot remove");
			return false;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

		if (!(rowCheck == 0 && colCheck == 0))
		{
			try
			{
				if (millCreated(rowCheck, colCheck))
				{
					while (!moveToken(player, MoveType.REMOVE))
					{
					}
					System.out.println(game);
				}
			} catch (InvalidCoordinatesException e)
			{
				System.out.println("Invalid cell coordinate");
				return false;
			}
		}
		return true;
	}

	private static int[] obtainUserInput(int numOfCoordinatesSet)
			throws InvalidInputException, Exception
	{
		if ((numOfCoordinatesSet != 2) && (numOfCoordinatesSet != 4))
		{
			throw new Exception("invalid call to obtainUserInput");
		}
		int[] coordinates = new int[2 * numOfCoordinatesSet];
		try
		{
			String input = br.readLine();
			String[] inputs = input.split(" ");
			if (inputs.length < 2)
			{
				throw new NumberFormatException();
			}
			coordinates[0] = Integer.parseInt(inputs[0]);
			coordinates[1] = Integer.parseInt(inputs[1]);
			if (numOfCoordinatesSet == 4)
			{
				coordinates[2] = Integer.parseInt(inputs[2]);
				coordinates[3] = Integer.parseInt(inputs[3]);
			}

		} catch (IOException e)
		{
			System.out.println("IOException");
			e.printStackTrace();
			throw new InvalidInputException();
		} catch (NumberFormatException e)
		{
			System.out.println("Enter two numbers separated by a space");
			throw new InvalidInputException();
		} catch (ArrayIndexOutOfBoundsException e)
		{
			System.out
					.println("Enter two sets of coordinates separated by a space");
			throw new InvalidInputException();
		}
		return coordinates;
	}

	private static boolean millCreated(int row, int col)
			throws InvalidCoordinatesException
	{
		if (Game.hasMill(row, col))
		{
			System.out.println("There is a mill at " + row + " " + col);
			return true;
		}
		return false;
	}

}
