/* List3.java
 * Jackson Oravetz
 * 11/8/23
 * 
 * Self-Adj (Front)
 * List3 implements the list by inserting new words in the front. However 
 * when a duplicate word is found, its count is incremented by one and the
 * corresponding node is moved to the front of the list. This is the most 
 * efficient out of Lists1, 2, and 4. A previous variable is provided in order to maintain the node
 * that is before the one currently being observed. All returns simply cease function of method.
 * */
public class List3 extends BaseList implements ListInterface
{
	public List3()	//constructor for list 3, calls super class
	{
		super();
	}
	
	public void add(String word)
	{
		LLNode location = list;
		LLNode previous = null;
		while (location != null)	//loops while location isn't null
		{
			if (location.getInfo().equals(word)) //checks for equivalence
			{
				if (previous == null)		//if looking at first node
				{
					location.incCount();	//increment count
					keyCompare++;			//one key comparison
					return;
				}
				else
				{
					//increments count and changes 3 links accordingly
					location.incCount();				
					previous.setNext(location.getNext());
					location.setNext(list);
					list = location;
					refChanges += 3;		//3 reference changes
					keyCompare++;			//one key comparison
					return;
				}
			}
			else
			{
				previous = location;			//if no match is found
				location = location.getNext();	//move previous and location
				keyCompare++;					//forward
			}
		}
		
		//only hit if new word
		LLNode newNode = new LLNode(word); //creates node
		newNode.setNext(list);			   //sets node to 	
		list = newNode;					   //be linked to front
		refChanges += 2;				   //two reference changes
	}
}
