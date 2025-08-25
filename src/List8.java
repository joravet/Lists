/* List8.java
 * Jackson Oravetz
 * 11/28/23
 * 
 * Hash 2
 * Refer to List7 for a description of a hash table.
 * List8 functions identically, other than its hash 
 * method. Instead of evaluating the entire words
 * ASCII value, it just evaluates the first letters ASCII
 * value. 
 * 
 */
public class List8 extends BaseList implements ListInterface
{
	List3[] list1 = new List3[256];	//creates array of 256 List3's
	
	public List8()	//constructor
	{
		super();
	}
	
	//add a word to the hash table
	public void add(String word)
	{
		int location;
		location = hash(word);	//call hash method
		
		if (list1[location] == null)		//if location is being added to
		{									//is null, create list 3
			list1[location] = new List3();
		}
		
		list1[location].add(word);	//list3 add method deals with collisions
		
	}
	
	//get index of array based on ascii of first letter
	public int hash(String word)
	{
		int index = word.charAt(0);	//get ascii value of first letter
		return index;
	}
	
	//get has vocab
	@Override
	public int getDistinctWords()
	{
		int words = 0;
		for (int i = 0; i < list1.length; i++)	//call list3 distinctWords
		{										//method for each index of
			if (list1[i] != null)				//array and sum them
			{									//(if index isn't null)
				words += list1[i].getDistinctWords();
			}
		}
		return words;
	}
	
	//get total words for hash
	@Override 
	public int getTotalWords()
	{
		int words = 0;
		for (int i = 0; i < list1.length; i++)	//call list3 totalWords
		{										//method for each index of
			if (list1[i] != null)				//array and sum them
			{									//(if index isn't null)
				words += list1[i].getTotalWords();
			}
		}
		return words;
	}
	
	//get reference change for hash
	@Override
	public long getRefChanges()
	{
		long count = 0;
		for (int i = 0; i < list1.length; i++)	//call list3 refChanges
		{										//method for each index of
			if (list1[i] != null)				//array and sum them
			{									//(if index isn't null)
				count += list1[i].getRefChanges();
			}
		}
		return count;
	}
	
	//get key Comparisons for hash
	@Override
	public long getKeyCompare()
	{
		long count = 0;	
		for (int i = 0; i < list1.length; i++)	//call list3 keyCompare
		{										//method for each index of
			if (list1[i] != null)				//array and sum them
			{									//(if index isn't null)
				count += list1[i].getKeyCompare();
			}
		}
		return count;
	}
	
	//find the largest list in array
	public int maxList()
	{
		int count = 0;
		for (int i = 0; i < list1.length; i++)	//if a list has more distinct
		{										//words (nodes) then set
			if (list1[i] != null)				//count = to its distinctWords
			{									//method
				if (list1[i].getDistinctWords() > count)
					count = list1[i].getDistinctWords();
			}
		}
		return count;
	}
	
	//find smallest list in array
	public int minList()
	{
		int count = 0;
		for (int i = 0; i < list1.length; i++)
		{
			if (list1[i] == null)	//if null list is found, return 0
			{
				return 0;
			}
			else if (count == 0)	//if count 0 and list[i] isn't null, make
			{						//count equal to list[i] number of nodes
				count = list1[i].getDistinctWords();
			}
			else 					//otherwise set count equal to smallest
			{						//list found
				if (list1[i].getDistinctWords() < count)
					count = list1[i].getDistinctWords();
			}
		}
		return count;
	}
	
	//get average length of list for entire array
	public double averageLength()
	{
		return getDistinctWords() / 256.0; 
	}
	
	//find standard deviation to see distribution of words
	public double standardDev()
	{
		double averageLength = averageLength(); //get average length
		double num = 0;
												//for each index of array
		for (int i = 0; i < list1.length; i++)	//do the math function below
		{										//unless list[i] is null
			if (list1[i] != null)				//then square avg length
				num += Math.pow(averageLength - list1[i].getDistinctWords(), 2);
			else 
				num += Math.pow(averageLength, 2);
		}
		num /= 256;				//divide 256
		return Math.sqrt(num);	//take square root
	}
	
	//find the length of a specific list in the array
	public int listLength(int index)
	{
		int count = 0;
		if (list1[index] == null)	//if list is null return zero
		{
			return 0;
		}
		else	//otherwise return number of nodes in list
			count = list1[index].getDistinctWords();  
		return count;
	}
}
