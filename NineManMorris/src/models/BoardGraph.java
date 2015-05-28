package models;
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
		
		b1.attachTo(b1,b10);
		b2.attachTo(b1,b3,b5);
		b3.attachTo(b2,b15);
		b4.attachTo(b5,b11);
		b5.attachTo(b2,b6,b8,b4);
		b6.attachTo(b5,b14);
		b7.attachTo(b8,b12);
		b8.attachTo(b5,b9,b7);
		b9.attachTo(b8,b13);
		b10.attachTo(b1,b22,b11);
		b11.attachTo(b4,b12,b19,b10);
		b12.attachTo(b7,b16);
		b13.attachTo(b9,b18,b14);
		b14.attachTo(b6,b21,b13,b15);
		b15.attachTo(b14,b3,b24);
		b16.attachTo(b12,b17);
		b17.attachTo(b16,b18,b20);
		b18.attachTo(b13,b17);
		b19.attachTo(b11,b20);
		b20.attachTo(b17,b19,b21,b23);
		b21.attachTo(b14,b20);
		b22.attachTo(b10,b23);
		b23.attachTo(b20,b22,b24);
		b24.attachTo(b23,b15);
		
		b1.millWith(b2,b3);
		b1.millWith(b10,b22);
		b2.millWith(b1,b3);
		b2.millWith(b5,b8);
		b3.millWith(b1,b2);
		b3.millWith(b15,b24);
		b4.millWith(b5,b6);
		b4.millWith(b11,b19);
		b5.millWith(b4,b6);
		b5.millWith(b2,b8);
		b6.millWith(b4,b5);
		b6.millWith(b14,b21);
		b7.millWith(b8,b9);
		b7.millWith(b12,b16);
		b8.millWith(b2,b5);
		b8.millWith(b7,b9);
		b9.millWith(b7,b8);
		b9.millWith(b13,b18);
		b10.millWith(b1,b22);
		b10.millWith(b11,b12);
		b11.millWith(b4,b19);
		b11.millWith(b10,b12);
		b12.millWith(b7,b16);
		b12.millWith(b10,b11);
		b13.millWith(b9,b18);
		b13.millWith(b14,b15);
		b14.millWith(b6,b21);
		b14.millWith(b13,b15);
		b15.millWith(b3,b24);
		b15.millWith(b13,b14);
		b16.millWith(b7,b12);
		b16.millWith(b17,b18);
		b17.millWith(b16,b18);
		b17.millWith(b20,b23);
		b18.millWith(b9,b13);
		b18.millWith(b16,b17);
		b19.millWith(b4,b11);
		b19.millWith(b20,b21);
		b20.millWith(b17,b23);
		b20.millWith(b19,b21);
		b21.millWith(b6,b14);
		b21.millWith(b19,b20);
		b22.millWith(b1,b10);
		b22.millWith(b23,b24);
		b23.millWith(b17,b20);
		b23.millWith(b22,b24);
		b24.millWith(b3,b15);
		b24.millWith(b22,b23);
		
		node.add(b1);
		node.add(b2);
		node.add(b3);
		node.add(b4);
		node.add(b5);
		node.add(b6);
		node.add(b7);
		node.add(b8);
		node.add(b9);
		node.add(b10);
		node.add(b11);
		node.add(b12);
		node.add(b13);
		node.add(b14);
		node.add(b15);
		node.add(b16);
		node.add(b17);
		node.add(b18);
		node.add(b19);
		node.add(b20);
		node.add(b21);
		node.add(b22);
		node.add(b23);
		node.add(b24);
		
		
		
	}

	public List<BoardNode> getNode() {
		return node;
	}

	public void setNode(List<BoardNode> node) {
		this.node = node;
	}

}
