/* List10.java
 * Jackson Oravetz
 * 12/1/23
 * 
 * Binary Search Tree
 * This is the only non-linear data structure in the entire
 * project. The root of the tree is the node at the very top that
 * everything branches from. Leaf nodes are nodes with two null child
 * pointers, meaning you reached the bottom of a branch. The binary 
 * search tree works fairly simply. The tree is traversed by going 
 * left if the comparison to the current node is small and right if it is large. 
 * This is done till a null child is found. Once one is found, you will be
 * able to insert the new word to the pointer that pointed to that null child. 
 * Other methods are supplied to gather extra data on a BST and to find things 
 * like a certain nodes successor.  
 * 
 */
public class List10 extends BaseList implements ListInterface
{
	BSTNode root;		//Node variable for the root node
	public int height = -1;		//height variable
	int maxHeight = 0;
	
	public List10() 	//constructor
	{
		super();
	}
	
	//method to add to the tree
	public void add(String word)
	{
		BSTNode x = root;	//x is the starts at the root and will traverse
		BSTNode y = null;	//y will lag behind x
		
		while (x != null)	//loop until we hit null at a leaf
		{
			if (x.getInfo().equals(word))
			{				
				keyCompare++;		//if the word we are looking at
				x.incCount();		//is the same as the word being added
				return;				//incCount and move on
			}
			
			y = x;					//save current location to y
			
			if (word.compareTo(x.getInfo()) < 0)	//should newNode be 
			{										//left of x?
				x = x.getLChild();
				keyCompare++;
			}
			else					//or should it be right of x?
			{
				x = x.getRChild();
				keyCompare++;
			}
		}
			//x is now null, and y points at the leaf we fell off of
			BSTNode newNode = new BSTNode(word);	//make a new node
			if (y == null)		//if y is still null, the tree is empty
			{					//make a one node tree
				root = newNode;
				refChanges++;
			}
			else if (newNode.getInfo().compareTo(y.getInfo()) < 0)
			{						//is the new word left of y?
				y.LChild = newNode;	//link it y's left child
				newNode.parent = y;
				keyCompare++;
				refChanges += 2;
			}
			else 	
			{						//or right of y?
				y.RChild = newNode;	//link it to y's right child
				newNode.parent = y;	//parent pointer for successor method (points to nodes parent)
				keyCompare++;
				refChanges += 2;
			}
	
	}
	
	//find the minimum value node by going left till it can't anymore
	public BSTNode min(BSTNode node)
	{
		while (node.LChild != null)
			node = node.LChild;
		return node;
	}
	
	//finds a specific node's successor
	public BSTNode successor(BSTNode x)
	{
		if (x.RChild != null)		//if x has a right child
			return min(x.RChild);	//return the min of that right child
		BSTNode y = x.parent;		//x has no right child; go up
		//loops until y is null or x doesn't equal y right child
		while (y != null && x == y.RChild)
		{
			x = y;
			y = y.parent;
		}
		return y;					//return y as the successor
	}
	
	//increment count for each traversal, returning number of nodes
	private int TraverseDistinct(BSTNode node, int count)
	{
		if (node == null) return count;
		count = TraverseDistinct(node.LChild, count);
		count++;
		count = TraverseDistinct(node.RChild, count);
		return count;
	}
	
	//sum the nodes counts at each traversal
	private int TraverseTotal(BSTNode node, int count)
	{
		if (node == null) return count;
		count = TraverseTotal(node.LChild, count);
		count += node.getCount();
		count = TraverseTotal(node.RChild, count);
		return count;
	}
	
	
	private void TraverseHeight(BSTNode node)
	{
		if (node == null) return;
		height++;
		if (height > maxHeight)
			maxHeight = height;
		TraverseHeight(node.LChild);
		TraverseHeight(node.RChild);
		height--;
	}
	
	//gets distinct words of tree
	@Override
	public int getDistinctWords()
	{
		int count = 0;
		count = TraverseDistinct(root, count);
		return count;
	}
	
	//gets total words of tree
	@Override
	public int getTotalWords()
	{
		int count = 0;
		count = TraverseTotal(root, count);
		return count;
	}
	
	//gets height of tree
	public int getHeight()
	{
		TraverseHeight(root);
		return maxHeight;
	}
	
	public BSTNode getMin()
	{
		BSTNode temp = min(root);
		return temp;
	}
	
}
