import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Board {
	private List<Token> tokens = new ArrayList<>();
	private BoardGraph boardGraph;
	private boolean allTokenPlaced = false;
	
	public Board()
	{
		Token t = new Token(7,1, Side.WHITE);
		Token t2 = new Token(7,2, Side.BLACK);
		Token t3 = new Token(7,3, Side.BLACK);
		Token t4 = new Token(6,1, Side.BLACK);
		Token t5 = new Token(6,2, Side.WHITE);
		Token t6 = new Token(6,3, Side.BLACK);
		tokens.add(t);
		tokens.add(t2);
		tokens.add(t3);
		tokens.add(t4);
		tokens.add(t5);
		tokens.add(t6);
	}
	
	public Token getToken(int row, int col)
	{
		for(Token t:tokens)
		{
			if(t.getRow()== row)
			{
				if(t.getCol()== col)
				{
					return t;
				}
			}
		}
		return null;
	}
	
	public void addToken(Token token)
	{
		tokens.add(token);
	}
	
	public boolean moveToken(Token token, Move move)
	{
		return true;
	}
	
	public boolean hasFullLine()
	{
		return false;
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
		for(Token t : tokens)
		{
			int idx = coordinatesToCharNumber(t.getRow(),t.getCol());
			if(t.getSide().equals(Side.WHITE))
				builder.replace(idx, idx+1, "O");
			if(t.getSide().equals(Side.BLACK))
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
}
