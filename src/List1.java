/* List1.java
 * Jackson Oravetz
 * ll/8/23
 * 
 * Unsorted
 * List1 implements the list by simply putting a new word to the front of the
 * list and incrementing a words count if it already exists. This code runs
 * quite inefficiently. All returns simply cease function of method.
 * */

public class List1 extends BaseList implements ListInterface
{
	public List1()	//constructor for list 1, calls super class
	{
		super();
	}
	
	public void add(String word)
	{
		LLNode location = list;
		while (location != null)	//loops if location isn't null
		{
			if (location.getInfo().equals(word))	//if words are equivalent
			{
				location.incCount();	//increment count and move on
				keyCompare++;
				return;
			}
			else						//otherwise move on to next node
			{
				location = location.getNext();
				keyCompare++;
			}
		}
		
		//only hit if word doesn't already exist
		LLNode newNode = new LLNode(word);	//creates node
		newNode.setNext(list);				//sets new node to 
		list = newNode;						//be linked to list
		refChanges += 2;					//two reference changes
	}
}
