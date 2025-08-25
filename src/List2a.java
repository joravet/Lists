/* List2a.java
 * Jackson Oravetz
 * 11/25/23
 * 
 * Modified Sorted List
 * Refer to List2.java for an explanation of how a sorted list functions.
 * This code offers a modification to List2's code. All it does is save
 * the word that was inserted previously and compares the new word to 
 * it first. This allows for the opportunity to skip a large amount
 * of the list instantly, making it faster than the original List2
 * 
 */
public class List2a extends BaseList implements ListInterface
{
	LLNode startHere = null;	//previous word
	
	public List2a()
	{
		super();
	}
	
	public void add(String word)
	{
		LLNode location = list;	
		LLNode previous = null;	
		
		//checks if the new word is to the right of the last word added
		//if it is, start traversing the list at the last words node
		keyCompare++;
		if (startHere != null && word.compareTo(startHere.getInfo()) > 0)
		{
			location = startHere;
		}
		
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
				startHere = location;
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
				startHere = location;
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
		refChanges += 2;					//two reference change
	}
}
