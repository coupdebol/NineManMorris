import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class Board {
	
	private BoardGraph boardGraph;
	private List<Mill> mills;
	
	
	public Board()
	{
		boardGraph = new BoardGraph();
		
		Mill h1 = new Mill(getToken(1,1),getToken(1,2),getToken(1,3));
		Mill h2 = new Mill(getToken(2,1),getToken(2,2),getToken(2,3));
		Mill h3 = new Mill(getToken(3,1),getToken(3,2),getToken(3,3));
		Mill h4 = new Mill(getToken(4,1),getToken(4,2),getToken(4,3));
		Mill h5 = new Mill(getToken(4,4),getToken(4,5),getToken(4,6));
		Mill h6 = new Mill(getToken(5,1),getToken(5,2),getToken(5,3));
		Mill h7 = new Mill(getToken(6,1),getToken(6,2),getToken(6,3));
		Mill h8 = new Mill(getToken(7,1),getToken(7,2),getToken(7,3));
		
		Mill v1 = new Mill(getToken(1,1),getToken(4,1),getToken(7,1));
		Mill v2 = new Mill(getToken(2,1),getToken(4,2),getToken(6,1));
		Mill v3 = new Mill(getToken(3,1),getToken(4,3),getToken(5,1));
		Mill v4 = new Mill(getToken(1,2),getToken(2,2),getToken(3,2));
		Mill v5 = new Mill(getToken(5,2),getToken(6,2),getToken(7,2));
		Mill v6 = new Mill(getToken(3,3),getToken(4,4),getToken(5,3));
		Mill v7 = new Mill(getToken(2,3),getToken(4,5),getToken(6,3));
		Mill v8 = new Mill(getToken(1,3),getToken(4,6),getToken(7,3));
		
		Mill[] millArray = {h1,h2,h3,h4,h5,h6,h7,h8,v1,v2,v3,v4,v5,v6,v7,v8};
		mills = new ArrayList<>();
		for(Mill m: millArray){
			mills.add(m);
		}
		
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
	
	public Token getToken(int row, int col)
	{
		List<BoardNode> nodes = boardGraph.getNode();
		for(BoardNode node:nodes)
		{
			Token t = node.getToken();
			if(t.getRow()== row)
			{
				if(t.getCol()== col)
				{
					return t;
				}
			}
		}
		return new Token(0,0,Side.NONE);
	}
	
	public void addToken(Token token) throws InvalidCoordinatesException
	{
		//update graph
		BoardNode node = findNode(token.getRow(),token.getCol());
		node.setToken(token);
		//update mills
		List<Mill> millList = findMills(token);
		for(Mill m:millList)
		{
			m.placeToken(token);
		}
	}
	
	public BoardNode findNode(int row, int col) throws InvalidCoordinatesException
	{
		List<BoardNode> nodes = boardGraph.getNode();
		for(BoardNode node:nodes)
		{
			Token t = node.getToken();
			if(t.getRow()== row)
			{
				if(t.getCol()== col)
				{
					return node;
				}
			}
		}
		throw new InvalidCoordinatesException();
	}
	
	public boolean moveToken(Token token)
	{
		return true;
	}
	
	public boolean hasMill(Token t) {
		boolean result = false;
		List<Mill> theMills = findMills(t);
		for(Mill m: theMills)
		{
			if(m.isFull())
			{
				return true;
			}
		}
		return result;
	}
	
	private List<Mill> findMills(Token t)
	{
		List<Mill> results = new ArrayList<>();
		for(Mill m: mills)
		{
			if(m.getTokens().contains(t))
			{
				results.add(m);
			}
		}
		return results;
	}
	
	public void updateMill(Token t) {
		Side mySide = t.getSide();
		List<Mill> millList = findMills(t);
		for(Mill m : millList)
		{
			boolean sameSide = true;
			for(Token tok: m.getTokens())
			{
				if(tok.getSide() != mySide)
				{
					sameSide = false;
				}
			}
			if(sameSide)
			{
				m.setFull(true);
			}
		}
		
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
			Token t = node.getToken();
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
