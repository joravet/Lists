/* List4.java
 * Jackson Oravetz
 * ll/8/23
 * 
 * Self-Adj (By one)
 * List4 implements the list by inputting new words in the front of the list.
 * However, duplicate words get their count's incremented and the swap place
 * with the node to the left. This is quite inefficient as well. A previous variable is provided in order to maintain the node
 * that is before the one currently being observed. A previous2 variable is 
 * provided to keep track of the node to spaces before the node being observed.
 * All returns simply cease function of method. 
 */
public class List4 extends BaseList implements ListInterface
{
	public List4()	//constructor for list4, calls superclass
	{
		super();
	}
	
	public void add(String word)
	{
		LLNode location = list;
		LLNode previous = null;
		LLNode previous2 = null;
		while (location != null)	//loops unless location = null
		{
			if (location.getInfo().equals(word)) //checks for equivalence
			{
				if (previous == null)	//if location is first node in list,
				{						//inc count and move on
					location.incCount();
					keyCompare++;		//one comparison takes place
					return;
				}
				else if (previous2 == null)	//if location is second node in list,
				{							//inc count, change 3 links accordingly
					location.incCount();	//similar to List3 move
					previous.setNext(location.getNext());
					location.setNext(list);
					list = location;
					keyCompare++;			//one comparison
					refChanges += 3;		//3 reference changes
					return;
				}
				else 					//if no nulls, change 3 links accordingly
				{
					location.incCount();
					previous2.setNext(previous.getNext());
					previous.setNext(location.getNext());
					location.setNext(previous);
					refChanges += 3;	//3 reference changes
					keyCompare++;		//one comparison
					return;
				}
			}
			else						//if nothing is equivalent, move all nodes
			{							//forward once
				previous2 = previous;
				previous = location;
				location = location.getNext();
				keyCompare++;			//a comparison took place if code got to this
										//point
			}
		}
		
		//only hit if new word
		LLNode newNode = new LLNode(word);	//creates node
		newNode.setNext(list);				//sets node to be
		list = newNode;						//linked to the front
		refChanges += 2;					//to reference changes when creating node
	}
}
