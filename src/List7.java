/* List7.java
 * Jackson Oravetz
 * 11/28/23
 * 
 * Hash 1
 * The code below is the first of three hash tables
 * in the project. A hash table uses a key of some kind
 * in order to go directly to a piece of data in an array.
 * For this class specifically, the key is found in the hash 
 * method. A ASCII value is given for each unique word and then
 * gives a certain index of the array accordingly. One may realize
 * that there are only 256 slots of the array, but many files are much
 * larger than this. To account, each index of the array is a list3.
 * What this ultimately means is: A word hashed to a certain index,
 * then it is inserted in a front sorting list in that specific index.
 * All other methods are for extraneous data.
 * 
 */
public class List7 extends BaseList implements ListInterface
{
	List3[] list1 = new List3[256];		//create an array of 256 List3's
	
	public List7()	//constructor
	{
		super();
	}
	
	//add a word to hash table
	public void add(String word)
	{
		int location;
		location = hash(word);	//call hash method
		
		if (list1[location] == null)		//if location being added to
		{									//is null, create list3 there
			list1[location] = new List3();
		}
		
		list1[location].add(word);	//list3 add method deals with collisions
		
	}
	
	//gets index of array based on ascii of whole word
	public int hash(String word)
	{
		int charSum = 0;
		for (int i = 0; i < word.length(); i++)	//sum the ASCII value of 
		{										//each character of the word
			charSum += word.charAt(i);
		}
		int index = charSum % 256;	//divide by 256 and get remainder
		return index;
	}
	
	//get hash vocab
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
	
	//total reference changes of hash
	@Override
	public long getRefChanges()
	{
		long count = 0;
		for (int i = 0; i < list1.length; i++)	//call list3 refChanges method
		{										//for each index of array
			if (list1[i] != null)				//and sum them
			{									//(unless index isn't null)
				count += list1[i].getRefChanges();
			}
		}
		return count;
	}
	
	//total key comparisons of hash
	@Override
	public long getKeyCompare()
	{
		long count = 0;
		for (int i = 0; i < list1.length; i++)	//call list3 keyCompare method
		{										//for each index of array
			if (list1[i] != null)				//and sum them
			{									//(unless index isn't null)
				count += list1[i].getKeyCompare();
			}
		}
		return count;
	}
	
	//find size of largest list in the array
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
	
	//find size of smallest list in array
	public int minList()
	{
		int count = 0;
		for (int i = 0; i < list1.length; i++)
		{
			if (list1[i] == null)	//if null list is found, return 0
			{
				return 0;
			}
			else if (count == 0)	//if count is 0 and list[i] isn't null, make
			{						//count equal list[i] number of nodes
				count = list1[i].getDistinctWords();
			}
			else 					//otherwise set count equal to the smallest
			{						//list found	
				if (list1[i].getDistinctWords() < count)
					count = list1[i].getDistinctWords();
			}
		}
		return count;
	}
	
	//find average length of list by dividing total nodes by 256
	public double averageLength()
	{
		return getDistinctWords() / 256.0; 
	}
	
	//find standard deviation to see distribution throughout array
	public double standardDev()
	{
		double averageLength = averageLength(); //get average length
		double num = 0;
												//for each index of the array
		for (int i = 0; i < list1.length; i++)	//do the math function below
		{										//unless list[i] is null
			if (list1[i] != null)				//then square avgLength
				num += Math.pow(averageLength - list1[i].getDistinctWords(), 2);
			else 
				num += Math.pow(averageLength, 2);
		}
		num /= 256;				//divide by 256
		return Math.sqrt(num);	//take square root
	}
	
	//find the length of a specific list in the array
	public int listLength(int index)
	{
		int count = 0;
		count = list1[index].getDistinctWords(); 
		return count;
	}
}

