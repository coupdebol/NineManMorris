import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Debol
 *
 */
public class BoardGraph {
	private List<BoardNode> node = new ArrayList<>();
	
	/*
	 * Board coordinates 
	 * 
	 * 
	 * 
1,1---------------1,2---------------1,3
 |                 |                 | 
 |    2,1---------2,2---------2,3    | 
 |     |           |           |     | 
 |     |    3,1---3,2---3,3    |     | 
 |     |     |           |     |     | 
4,1---4,2---4,3         4,4---4,5---4,6
 |     |     |           |     |     | 
 |     |    5,1---5,2---5,3    |     | 
 |     |           |           |     | 
 |    6,1---------6,2---------6,3    | 
 |                 |                 | 
7,1---------------7,2---------------7,3
	*
	* Object Numbers
&01---------------&02---------------&03
 |                 |                 | 
 |    &04---------&05---------&06    | 
 |     |           |           |     | 
 |     |    &07---&08---X09    |     | 
 |     |     |           |     |     | 
&10---&11---&12         &13---&14---&15
 |     |     |           |     |     | 
 |     |    &16---&17---&18    |     | 
 |     |           |           |     | 
 |    &19---------&20---------&21    | 
 |                 |                 | 
&22---------------&23---------------&24
	*
	*Tokens are made of 3 characters 
	*Empty is  [ ] 
	*Black is  [X]
	*White is  [O]
	*
	*This is an example board
	*
[O]---------------[ ]---------------[ ]
 |                 |                 | 
 |    [X]---------[X]---------[O]    | 
 |     |           |           |     | 
 |     |    [ ]---[O]---[ ]    |     | 
 |     |     |           |     |     | 
[ ]---[ ]---[X]         [ ]---[O]---[ ]
 |     |     |           |     |     | 
 |     |    [ ]---[ ]---[O]    |     | 
 |     |           |           |     | 
 |    [O]---------[ ]---------[O]    | 
 |                 |                 | 
[ ]---------------[ ]---------------[ ]
	*
	*/
	
	
	public BoardGraph()
	{
		BoardNode b1  =  new BoardNode(1,1);
		BoardNode b2  =  new BoardNode(1,2);
		BoardNode b3  =  new BoardNode(1,3);
		BoardNode b4  =  new BoardNode(2,1);
		BoardNode b5  =  new BoardNode(2,2);
		BoardNode b6  =  new BoardNode(2,3);
		BoardNode b7  =  new BoardNode(3,1);
		BoardNode b8  =  new BoardNode(3,2);
		BoardNode b9  =  new BoardNode(3,3);
		BoardNode b10 =  new BoardNode(4,1);
		BoardNode b11 =  new BoardNode(4,2);
		BoardNode b12  = new BoardNode(4,3);
		BoardNode b13  = new BoardNode(4,4);
		BoardNode b14  = new BoardNode(4,5);
		BoardNode b15  = new BoardNode(4,6);
		BoardNode b16  = new BoardNode(5,1);
		BoardNode b17  = new BoardNode(5,2);
		BoardNode b18  = new BoardNode(5,3);
		BoardNode b19  = new BoardNode(6,1);
		BoardNode b20  = new BoardNode(6,2);
		BoardNode b21  = new BoardNode(6,3);
		BoardNode b22  = new BoardNode(7,1);
		BoardNode b23  = new BoardNode(7,2);
		BoardNode b24  = new BoardNode(7,3);
		
		
	}
}
