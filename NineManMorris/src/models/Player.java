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
public abstract class Player {
	
	private List<Token> tokenBag = new ArrayList<>();
	private String name;
	private Side side;
	
	
	/**
	 * A player is created with a full bag of token ready to be placed on the board
	 * @param side
	 */
	
	public Player(Side side){
		this.side = side;
		for(int i = 0 ; i < 9 ; i++){
			Token token = new Token(side);
			tokenBag.add(token);
		}
		if(side.equals(Side.O))
		{
			this.setName("O");
		}
		if(side.equals(Side.X))
		{
			this.setName("X");
		}
	}
	
	public Token takeToken() throws EmptyBagException
	{
		if (tokenBag.isEmpty())
		{	
			throw new EmptyBagException();
		}else
		{
			return tokenBag.remove(0);
		}
	}
	
	public boolean hasToken()
	{
		return tokenBag.size() > 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Side getSide() {
		return side;
	}
	
	public int getTokenBagSize()
	{
		return tokenBag.size();
	}
	
	public void setTokenBagSize(int num)
	{
		while(getTokenBagSize()!=num)
		{
			tokenBag.remove(0);
		}
	}

}
