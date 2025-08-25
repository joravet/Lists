/* List5.Java
 * Jackson Oravetz
 * 11/23/23
 * 
 * Skip List
 * List5 is code for the skip list. A skip list can be described
 * as a semi-linear data structure. The way it functions is fairly 
 * simple. A bottom slow lane contains all words added to the list
 * in sorted order. However, each time a word is inserted, a coin is
 * flipped to determine if a copy of that word should be inserted directly 
 * above its original in the slow lane. The coin continues to be flipped
 * until it lands on "tails", meaning that a word could theoretically
 * get several lanes high. The search method provided will then search
 * the entire list from top to bottom, allowing for large gaps in words
 * to be continuously skipped. The skip list is inherently random, meaning
 * that one's luck on the coin toss's can alter the efficiency results. All
 * other methods in class gather extraneous data when called.
 * 
 * 
 */
import java.util.Random;

public class List5 extends BaseList implements ListInterface
{
	private  SkipListNode head, tail;	//head and tail pointers
	public int h;						//height (# of lanes)
	private int n;						//# of items in list (slow lane)
	private Random r;					//used to flip coin/add to new row
	
	//constructor sets head, tail to sentinel nodes and link them to
	//one another. Creates a random object and sets height to 1
	public List5()	
	{
		head = new SkipListNode("negInf");
		tail = new SkipListNode("posInf");
		head.setIsSentinel(true);
		tail.setIsSentinel(true);
		head.right = tail;
		tail.left = head;
		r = new Random();
		n = 0;
		h = 1;
	}
	
	//add word list
	public void add(String word)
	{
		SkipListNode p = search(word);	//search for word in list
		
		if (p.getInfo().equals(word))	//if the word is found, increment count
		{								//and move on
			p.incCount();
			keyCompare++;
			return;
		}
		
		//word is not found
		else 
		{
			SkipListNode newNode = new SkipListNode(word);	//create new node
			SkipListNode topNode = newNode;	//saves the highest node for new word
			SkipListNode location = newNode; //temp variable for newNode
			int headsFlips = 1; //coin will flip at least once, track it
			int lane = 1; //track which lane we are in
			newNode.setLeft(p);					//
			newNode.setRight(p.getRight());		//Insert newNode where it should
			p.getRight().setLeft(newNode);		//be in slow lane
			p.setRight(newNode);				//
			refChanges += 4;
			
			while (true)	//loops until hitting return statement
			{
				int randNum = r.nextInt(0, 2); //flip the coin
				if (randNum == 1)	//if heads
				{
					if (h < headsFlips + 1)	//check if a lane for this flip exists
					{
						//creates new sentinel nodes, links them, and increments h
						SkipListNode newHead = new SkipListNode("negInf");
						newHead.setIsSentinel(true);
						SkipListNode newTail = new SkipListNode("posInf");
						newTail.setIsSentinel(true);
						newHead.down = head;     head.up = newHead;
						newTail.down = tail;     tail.up = newTail;
						newHead.right = newTail; newTail.left = newHead;
						head = newHead;          tail = newTail;
						h++;
						refChanges += 8;
					}
					
					if (h >= headsFlips + 1)	//if height is highest enough for 
					{							//this flip
						while (lane != headsFlips + 1)	//as long as we are not in
						{								//a lane to high up
							if (location.getUp() != null)
							{
								location = location.getUp(); //move up a lane
								lane++;						//if theres an up pointer
								keyCompare++;
							}
							else
							{	
								outer: while (location.getLeft() != null)		//go left until a node has an up link
								{												//
									if (location.getLeft().getUp() != null)		//
									{											//
										location = location.getLeft().getUp();	//
										lane++;									//
										keyCompare++;							//
										break outer;							//break out of nearest while loop
									}
									
									else										//
									{											//
										location = location.getLeft();			//
										keyCompare++;							//
									}		
									
								}
							}
						}
						
						p = location; //temp variable for location
						SkipListNode newNodeFlip = new SkipListNode(word);	//create a new node to be place above
						newNodeFlip.setLeft(p);								//the previous newNode		
						newNodeFlip.setRight(p.getRight());		//
						p.getRight().setLeft(newNodeFlip);		//
						p.setRight(newNodeFlip);				//set its links
						newNodeFlip.setDown(topNode);			//
						topNode.setUp(newNodeFlip);				//
						headsFlips++;							//increment the number of headFlipls
						topNode = newNodeFlip;					//save the highest node for this word
						refChanges += 6;
					}
				}
				
				else	//return when a tails is flipped
				{
					return;
				}
			}
		}
	}
	
	//searches for word in list
	public SkipListNode search(String word)
	{
		//returns a slow lane pointer to either node containing key k
		//or the slow lane node preceding where k WOULD be if the list
		SkipListNode p = head;
		while (true)
		{
			while (!p.right.isSentinel && p.right.info.compareTo(word) <= 0)
			{
				p = p.right;
				keyCompare++;
			}
			if (p.down == null) return p;
			p = p.down;
			keyCompare++;
		}
	}
	
	//get vocab for skip list
	@Override
	public int getDistinctWords()
	{
		int count = 0; 
		SkipListNode temp = head;
		while (temp.down != null)	//go down until you cant anymore
		{
			temp = temp.getDown();
		}
		while (temp != null)		//go right until you cant anymore
		{							//incrementing each time
			count++;
			temp = temp.getRight();
		}
		return count - 2;	//subtract 2 for the sentinel nodes
	}
	
	//get total words for skip list
	@Override
	public int getTotalWords()
	{
		int count = 0;
		SkipListNode temp = head;
		while (temp.down != null)	//go down until you cant anymore
		{
			temp = temp.getDown();
		}
		while (temp != null)		//go right until you cant anymore
		{							//summing the counts each time
			count += temp.getCount();
			temp = temp.getRight();
		}
		return count - 2;	//subtract 2 for sentinel nodes
	}
	
	//return the height
	public int getHeight()
	{
		return h;
	}
	
	//get the total number of nodes in entire skiplist
	public int getTotalNodes()
	{
		int count = 0;
		SkipListNode temp = head;
		SkipListNode tempPre = head;
		for (int i = 0; i < getHeight(); i++) //loop at each level of the height
		{
			while (temp != null)	//go right till you can't anymore 
			{						//incrementing count each time
				count++;
				temp = temp.getRight();
			}
			temp = tempPre.getDown();	 //go down one from the row you're currently in
			tempPre = tempPre.getDown(); //move temp variable down to save row you're in
		}
		return count - (2 * h);
	}
	
	//get min value of skip list
	public SkipListNode min()
	{
		SkipListNode temp = head;
		while (temp.down != null)	//go down from head until you cant anymore
		{
			temp = temp.getDown();
		}
		
		temp = temp.getRight();	//get the node directly to the right
		return temp;
	}
	
	//get max value of skip list
	public SkipListNode max()
	{
		SkipListNode temp = head;
		while (temp.down != null)	//go down until you cant anymore
		{
			temp = temp.getDown();
		}
		while (temp.getRight() != null)	//go right till you can't anymore
		{
			temp = temp.getRight();
		}
		
		temp = temp.getLeft();	//get the directly to your left
		return temp;
	}
}
