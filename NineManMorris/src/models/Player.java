package models;

import java.util.ArrayList;
import java.util.List;

import enums.Side;
import exceptions.EmptyBagException;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public abstract class Player
{

	private List<Token> tokenBag = new ArrayList<>();
	private String name;
	private Side side;

	/**
	 * A player is created with a full bag of token ready to be placed on the
	 * board
	 * 
	 * @param side
	 *            side of the player
	 */

	public Player(Side side)
	{
		this.side = side;
		for (int i = 0; i < 9; i++)
		{
			Token token = new Token(side);
			tokenBag.add(token);
		}
		if (side.equals(Side.O))
		{
			this.setName("O");
		}
		if (side.equals(Side.X))
		{
			this.setName("X");
		}
	}

	/**
	 * Take a token from the bag of token of the player. If the token bag is
	 * empty, throws an EmptyBagException
	 * 
	 * @return the Token that has been taken from the player
	 * @throws EmptyBagException
	 *             if the token bag of the player is empty
	 */
	public Token takeToken() throws EmptyBagException
	{
		if (tokenBag.isEmpty())
		{
			throw new EmptyBagException();
		} else
		{
			return tokenBag.remove(0);
		}
	}

	/**
	 * return true if the player has some tokens in his bag
	 * 
	 * @return true if the player has some tokens in his bag
	 */
	public boolean hasToken()
	{
		return tokenBag.size() > 0;
	}

	/**
	 * returns the name of the player
	 * 
	 * @return the name of the player
	 */
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Side getSide()
	{
		return side;
	}

	public int getTokenBagSize()
	{
		return tokenBag.size();
	}

	/**
	 * Set the size of the bad of the player by removing the nine token that
	 * were created at construction
	 * 
	 * @param num
	 *            the number of token the player has to have
	 */
	public void setTokenBagSize(int num)
	{
		while (getTokenBagSize() != num)
		{
			tokenBag.remove(0);
		}
	}

}
