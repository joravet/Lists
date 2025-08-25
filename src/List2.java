/* List2.java
 * Jackson Oravetz
 * 11/8/23
 * 
 * Sorted
 * List2 implements the list alphabetically and increase the count variable 
 * if a duplicate word is found. The list stops doing comparisons if a 
 * duplicate is found or if the word is being compared to a word later in 
 * alphabet than it. This list is still quite inefficient, but more efficient
 * than List1. A previous variable is provided in order to maintain the node
 * that is before the one currently being observed. All returns simply cease function of method.
 * */

public class List2 extends BaseList implements ListInterface
{
	public List2() //constructor for list 2, calls super class
	{
		super();
	}
	
	public void add(String word)
	{
		LLNode location = list;
		LLNode previous = null;
		while (location != null)	//loops while location isn't null
		{
			if (location.getInfo().equals(word)) //if words are equivalent
			{
				location.incCount();			//increment count and move on
				keyCompare++;
				return;
			}
			else if (word.compareTo(location.getInfo()) < 0) //checks if word is earlier
			{												 //in alphabet then location
				LLNode newNode = new LLNode(word);	//creates new node
				if (previous == null)				//if location is first node
				{									
					newNode.setNext(list);			//do two link changes to put 
					list = newNode;					//new node in the front of list
					refChanges += 2;
					keyCompare++;
					return;
				}
				else								//otherwise put new node in
				{									//between previous and location
					newNode.setNext(location);
					previous.setNext(newNode);
					refChanges += 2;
					keyCompare++;
					return;
				}
			}
			else if(location.getNext() == null)		//if location is last node in list
			{										//insert at end of list
				LLNode newNode = new LLNode(word);
				newNode.setNext(location.getNext());
				location.setNext(newNode);
				refChanges += 2;
				keyCompare++;
				return;
			}
			else						//if nothing is true, move
			{							//previous and location up 1
				previous = location;
				location = location.getNext();
				keyCompare++;
			}
			
		}
		
		//only hit if first input into list
		LLNode newNode = new LLNode(word);	//creates new node
		newNode.setNext(list);				//sets new node to be
		list = newNode;						//linked to front
		refChanges += 2;					//two reference changes
	}
}
