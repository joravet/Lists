/* SkipListNode.java
 * Jackson Oravetz
 * 11/18/23
 * 
 * This is the node class for the skip list. Most of
 * what is present in this class is what would be expected
 * from a normal linked list node class. Each skip list node
 * has 4 pointers, one for each direction. The other unique
 * thing is the existence of sentinel nodes. Skip lists 
 * contain sentinel nodes on the far left and far right so all
 * real nodes have a predecessor and successor and so that the
 * list has distinct starting and stopping points. Other than this,
 * the SkipListNode functions as any other node class.
 * 
 */
public class SkipListNode
{
	public String info;		//comparable we use to determine position
	public int count;		//number of times info appears
	public SkipListNode up, down, left, right;	//all for links
	public boolean isSentinel;	//flag nodes as sentinel
	
	//constructor for SLNode. Sets all links to null, saves the word, and
	//sets count to 1 since a new node means there is one instance of word
	public SkipListNode(String info)
	{
		this.info = info;
		up = null;
		down = null;
		left = null;
		right = null;
		count = 1;
		isSentinel = false;
	}
	
	//set the word
	public void setInfo(String info)
	{
		this.info = info;
	}
	
	//get the word
	public String getInfo()
	{
		return info;
	}
	
	//set the up link
	public void setUp(SkipListNode up)
	{
		this.up = up;
	}
	
	//get the uplink
	public SkipListNode getUp()
	{
		return up;
	}
	
	//set down link
	public void setDown(SkipListNode down)
	{
		this.down = down;
	}
	
	//get down link
	public SkipListNode getDown()
	{
		return down;
	}
	
	//etc
	public void setLeft(SkipListNode left)
	{
		this.left = left;
	}
	
	public SkipListNode getLeft()
	{
		return left;
	}
	
	public void setRight(SkipListNode right)
	{
		this.right = right;
	}
	
	public SkipListNode getRight()
	{
		return right;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public int getCount()
	{
		return count;
	}
	
	//increments count variable by 1
	public void incCount()
	{
		count++;
	}
	
	public void setIsSentinel(boolean isSentinel)
	{
		this.isSentinel = isSentinel;
	}
	
	public boolean getIsSentinel()
	{
		return isSentinel;
	}
}
