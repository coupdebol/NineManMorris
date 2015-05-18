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

	private int row;
	private int col;
	public List<BoardNode> nodes = new ArrayList<>();	
	
	public BoardNode(int row, int col) {
		this.row = row;
		this.col = col;
	}
		
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public void attachTo(BoardNode... nodes)
	{
		for(BoardNode node : nodes ){
			this.nodes.add(node);
		}	
	}
}
