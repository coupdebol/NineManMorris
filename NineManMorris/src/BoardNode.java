import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class BoardNode {

	private Token token;
	public List<BoardNode> nodes = new ArrayList<>();	
	
	public BoardNode(int row, int col) {
		token = new Token(row,col,Side.NONE);
	}
		
	public int getRow(){
		return token.getRow();
	}
	
	public int getCol(){
		return token.getCol();
	}
	
	public Side getSide()
	{
		return token.getSide();
	}
	
	public void attachTo(BoardNode... nodes)
	{
		for(BoardNode node : nodes ){
			this.nodes.add(node);
		}	
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		if(!(o instanceof BoardNode))
			return false;
		BoardNode node = (BoardNode) o;
		if(node.token == null)
			return false;
		if(node.token.getRow()!=this.token.getRow())
			return false;
		if(node.token.getRow()!=this.token.getRow())
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return token.getRow() + " " + token.getCol() + "->" + nodes.size() + "nodes";
	}
}
