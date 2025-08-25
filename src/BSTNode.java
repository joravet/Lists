/* BSTNode.java
 * Jackson Oravetz
 * 12/5/23 
 * 
 * This is the node class for the Binary search tree.
 * BSTNode's in this case will have a info variable
 * that will hold the word in the node, a count for the
 * number of instances of the word, and three pointers. A
 * left child pointer, a right child pointer, and a parent 
 * pointer are present in each node. The functionality of
 * the class is very similar to a normal linked list node,
 * BST's simply require more pointers.
 * 
 * 
 */
public class BSTNode
{
	public String info;		//stores word
	public long count;		//stores count
	public BSTNode LChild, RChild, parent; //links
	
	//constructor setting all links to null and count to 1
	public BSTNode(String info) 
	{
		this.info = info;
		LChild = null;
		RChild = null;
		parent = null;
		count = 1;
	}
	
	public void setInfo(String info)
	{
		this.info = info;
	}
	
	public String getInfo()
	{
		return info;
	}
	
	public void setLChild(BSTNode LChild)
	{
		this.LChild = LChild;
	}
	
	public BSTNode getLChild()
	{
		return LChild;
	}
	
	public void setRChild(BSTNode RChild)
	{
		this.RChild = RChild;
	}
	
	public BSTNode getRChild()
	{
		return RChild;
	}
	
	public void setParent(BSTNode parent)
	{
		this.parent = parent;
	}
	
	public BSTNode getParent()
	{
		return parent;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public long getCount()
	{
		return count;
	}
	
	public void incCount()
	{
		count++;
	}
}
