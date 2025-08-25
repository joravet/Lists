/* List9.java
 * Jackson Oravetz
 * 11/28/23
 * 
 * Hash 3
 * Once again refer to List7 for a description
 * of a hash table. Hash 3 uses instructor supplied 
 * code for the hash function. This code still 
 * evaluates based on ASCII, however in a much
 * more complex manner.
 * 
 */
public class List9 extends BaseList implements ListInterface
{

	List3[] list1 = new List3[256];		//create an array of 256 List3's
	
	public List9()	//constructor
	{
		super();
	}
	
	//add a word to the hash table
	public void add(String word)
	{
		int location;
		location = hash(word);	//call hash method
		
		if (list1[location] == null) 		//if the location being 
		{									//added to is null
			list1[location] = new List3();	//create a list3 there
		}
		
		list1[location].add(word);	//use the list3 add method to deal with collisions
		
	}
	
	//get index of array
	public int hash(String word) //instructor supplied code to hash
	{
		 long hash = 0;
		 for (int i = 0; i < word.length(); i++)
		 hash = (hash * 15) + word.charAt(i);
		 return (int) (hash & 255);
	}
	
	//get vocab for Hash
	@Override
	public int getDistinctWords()
	{
		int words = 0;
		for (int i = 0; i < list1.length; i++)	//call list 3 distinctWords
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
		for (int i = 0; i < list1.length; i++)	//call list 3 totalWords
		{										//method for each index of
			if (list1[i] != null)				//array and sum them
			{									//(if index isn't null)
				words += list1[i].getTotalWords();
			}
		}
		return words;
	}
	
	//get reference changes for hash
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
	
	//get key comparisons for hash
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
	
	//find the size of the largest list in the array
	public int maxList()
	{
		int count = 0;
		for (int i = 0; i < list1.length; i++)	//if a list has more distinct
		{										//words (nodes) then the count
			if (list1[i] != null)				//set count = to its distinct
			{									//word method
				if (list1[i].getDistinctWords() > count)
					count = list1[i].getDistinctWords();
			}
		}
		return count;
	}
	
	//find size of the smallest list in the array
	public int minList()
	{
		int count = 0;
		for (int i = 0; i < list1.length; i++)
		{
			if (list1[i] == null)				//if a null list is present
			{									//0 is the min
				return 0;
			}
			else if (count == 0)				//if a list that isn't null is found
			{									//and count is still 0, make count it's size
				count = list1[i].getDistinctWords();
			}
			else 								//otherwise compare count to each list size
			{									//and save the smallest
				if (list1[i].getDistinctWords() < count)
					count = list1[i].getDistinctWords();
			}
		}
		return count;
	}
	
	//find the average length of list by diving the total words by 256
	public double averageLength()
	{
		return getDistinctWords() / 256.0; 
	}
	
	//find the standard deviation of the hash, will represent the distribution
	//of the words throughout the array
	public double standardDev()
	{
		double averageLength = averageLength();	//call averageLength method
		double num = 0;
												//for each index of array
		for (int i = 0; i < list1.length; i++)	//do the math function below
		{										//unless list1[i] is null
			if (list1[i] != null)				//in which you square average length
				num += Math.pow(averageLength - list1[i].getDistinctWords(), 2);
			else 
				num += Math.pow(averageLength, 2);
		}
		num /= 256;				//divide by 256
		return Math.sqrt(num);	//and take square root
	}
	
	//find the length of a specific list in the array
	public int listLength(int index)
	{
		int count = 0;
		count = list1[index].getDistinctWords(); 
		return count;
	}
}
