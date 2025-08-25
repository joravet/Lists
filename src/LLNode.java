/*LLNode.java
 * Jackson Oravetz
 * 
 * The LLNode class's functionality is to create nodes, set he link of
 * nodes, set the information of nodes, set the count of nodes, and return 
 * the values of those variables. The info variable is the word in each node.
 * The Info methods have to do with linking nodes. Count is the number of 
 * times a word appears in the text file.
 */
public class LLNode
{
	protected String info;			//data node stores
	protected LLNode link;	//link to NEXT node
	protected int count;
	
	public LLNode(String info)		//Constructor for node class
	{
		this.info = info;		//info = input
		link = null;			//link is null
		count = 1;
	}
	
	public void setInfo(String info)	//set info based on parameter input
	{
		this.info = info;
	}
	
	public String getInfo()			//returns info when called
	{
		return info;
	}
	
	public void setNext(LLNode link) //set link based on parameter input
	{
		this.link = link;
	}
	
	public LLNode getNext() 	//returns link when called
	{
		return link;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void incCount()
	{
		count++;
	}
}
