/* Lab3.java
 * Jackson Oravetz
 * Dr. Thomas
 * EECS 2500
 * 12/8/2023
 * 
 * This class contains code to print out the data of all
 * 10 lists being implemented, as well as code to print the 
 * first 100 words of the binary tree. The 10 lists being tested
 * are and unsorted list, a sorted list, a modified sorted list,
 * a front adjusting list, a single adjusting list, a skip list,
 * 3 different hash tables, and a binary tree. A punctuation method 
 * supplied to remove all leading and trailing punctuation to any
 * word added to the list and all words will be automatically 
 * lower-cased. An extraneous data code is present, but commented
 * out. To use the code, supply a text file and wait for the results
 * Running the code with not text file will result in no results.
 */

import java.util.Scanner;

public class Lab3
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);	//create a scanner
		java.io.File file = new java.io.File(args[0]);	// **currently gives error** create a file
		ListInterface[] Lists = new ListInterface[10];	//creates array of lists
		Lists[0] = new List1();							//for modularity sake
		Lists[1] = new List2(); 
		Lists[2] = new List3(); 
		Lists[3] = new List4();
		Lists[4] = new List5();
		Lists[5] = new List2a();
		Lists[6] = new List7();
		Lists[7] = new List8();
		Lists[8] = new List9();
		Lists[9] = new List10();
		
		/*while (input.hasNext() == true)	//debug code
		{
			Lists[9].add(input.next().toLowerCase());
		}*/
		
		//test vars for first 100 words
		/*List3 test1 = new List3();
		List4 test2 = new List4();*/
		
		//array of list names for same reason as above
		String[] ListNames = {"Unsorted", "Sorted", "Self-Adj (Front)", "Self-Adj (By one)", "Skip List", "Sorted Modified", "Hash 1", "Hash 2", "Hash 3", "Binary Tree"};
		
		try	//try to run code, if no file, go to catch
		{
			double overheadTime = 0;
			//code for i = 2-5 is identical, with only variation being which List is called
			for (int i = 0; i < 12; i++)	//do all 6 variations
			{
				if (i == 0)	//opens file and reads it one word at a time
				{			//print header if file exists
					Scanner input0 = new Scanner(file);
					while (input0.hasNext() == true)
					{
						String word = input0.next();
					}
					System.out.println(" #    List Name      Run Time  Vocabulary  Total Words   Key Comp     Ref Chgs    h ");
					System.out.println("-- ----------------- --------  ----------  ----------- ------------ ------------ ---");
					input0.close();	//close file
				}
				
				else if (i == 1)	//open file, track time, and parse punctuation
				{
					long time; // What time did we start this test?
					time = System.currentTimeMillis(); // mark the start time
					Scanner input1 = new Scanner(file);
					while (input1.hasNext() == true)
					{
						String word = punctRemover(input1.next().toLowerCase()); //parses punctuation and makes word lowercase
					}
					overheadTime = ((System.currentTimeMillis() - time) / 1000.0); //get time elapsed
					input1.close();
				}
				
				else if (i == 2)	//complete list1
				{
					long time; // What time did we start this test?
					double elapsed; // What was the elapsed time for all repetitions of this test (in seconds)?
					time = System.currentTimeMillis(); // mark the start time
					Scanner input2 = new Scanner(file);
					while (input2.hasNext() == true)
					{
						String word = input2.next().toLowerCase();	//lower cases next word
						word = punctRemover(word);					//removes punctuation
						if (word != "")								//if word remains, add it
							Lists[0].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					//All printf statements will from here on out will out the 5 or more necessary outputs in a formatted manner
					System.out.printf("%2d%9s%18.3f%11d%12d%14d%13d\n", 1, ListNames[0], elapsed - overheadTime, Lists[0].getDistinctWords(), Lists[0].getTotalWords(), Lists[0].getKeyCompare(), Lists[0].getRefChanges());
					input2.close(); //close file
				}
				
				else if(i == 3)		//complete list2
				{
					long time; // What time did we start this test?
					double elapsed; // What was the elapsed time for all repetitions of this test (in seconds)?
					time = System.currentTimeMillis(); // mark the start time
					Scanner input3 = new Scanner(file);
					while (input3.hasNext() == true)
					{
						String word = input3.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[1].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					System.out.printf("%2d%7s%20.3f%11d%12d%14d%13d\n", 2, ListNames[1], elapsed - overheadTime, Lists[1].getDistinctWords(), Lists[1].getTotalWords(), Lists[1].getKeyCompare(), Lists[1].getRefChanges());
					input3.close(); //close file
				}
				
				else if (i == 4)	//complete list2a
				{
					long time; // What time did we start this test?
					double elapsed; // What was the elapsed time for all repetitions of this test (in seconds)?
					time = System.currentTimeMillis(); // mark the start time
					Scanner input7 = new Scanner(file);
					while (input7.hasNext() == true)
					{
						String word = input7.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[5].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					System.out.printf("%2s%16s%11.3f%11d%12d%14d%13d\n", "2a", ListNames[5], elapsed - overheadTime, Lists[5].getDistinctWords(), Lists[5].getTotalWords(), Lists[5].getKeyCompare(), Lists[5].getRefChanges());
					input7.close(); //close file
				}
				
				else if (i == 5)	//complete list3
				{
					long time; // What time did we start this test?
					double elapsed; // What was the elapsed time for all repetitions of this test (in seconds)?
					time = System.currentTimeMillis(); // mark the start time
					Scanner input4 = new Scanner(file);
					while (input4.hasNext() == true)
					{
						String word = input4.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[2].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					System.out.printf("%2d%17s%10.3f%11d%12d%14d%13d\n", 3, ListNames[2], elapsed - overheadTime, Lists[2].getDistinctWords(), Lists[2].getTotalWords(), Lists[2].getKeyCompare(), Lists[2].getRefChanges());
					input4.close(); //close file
				
					//code to get first 100 words
					//Scanner input4 = new Scanner(file);
					//while (test1.getDistinctWords() <= 100)
					//{
					//	String word = input4.next().toLowerCase();
					//	word = punctRemover(word);
					//	if (word != "")
					//		test1.add(word);
					//}
				}
				
				else if (i == 6)	//complete list4
				{
					long time; // What time did we start this test?
					double elapsed; // What was the elapsed time for all repetitions of this test (in seconds)?
					time = System.currentTimeMillis(); // mark the start time
					Scanner input5 = new Scanner(file);
					while (input5.hasNext() == true)
					{
						String word = input5.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[3].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					System.out.printf("%2d%18s%9.3f%11d%12d%14d%13d\n", 4, ListNames[3], elapsed - overheadTime, Lists[3].getDistinctWords(), Lists[3].getTotalWords(), Lists[3].getKeyCompare(), Lists[3].getRefChanges());
					input5.close(); //close
					
					//tests for first 100 words
					//Scanner input5 = new Scanner(file);
					//while (test2.getDistinctWords() <= 100)
					//{
					//	String word = input5.next().toLowerCase();
					//	word = punctRemover(word);
					//	if (word != "")
					//		test2.add(word);
					//}
				}
				
				else if (i == 7)	//complete skip list
				{					//functions identically to previous else if's
					long time;
					double elapsed;
					time = System.currentTimeMillis();
					Scanner input6 = new Scanner(file);
					while (input6.hasNext() == true)
					{
						String word = input6.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[4].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					//prints height as well, casted to lists5 since ListInterface doesn't contain getHeight()
					System.out.printf("%2d%10s%17.3f%11d%12d%14d%13d%5d\n", 5, ListNames[4], elapsed - overheadTime, Lists[4].getDistinctWords(), Lists[4].getTotalWords(), Lists[4].getKeyCompare(), Lists[4].getRefChanges(), ((List5) Lists[4]).getHeight());
					//System.out.println(((List5) Lists[4]).getTotalNodes());	//Gets extra data
					//System.out.println(((List5) Lists[4]).min().getInfo());	//
					//System.out.println(((List5) Lists[4]).max().getInfo());	//
					input6.close();
				}
				
				else if (i == 8)	//complete Hash 1
				{					//nearly identical to previous else if's
					long time;
					double elapsed;
					time = System.currentTimeMillis();
					Scanner input8 = new Scanner(file);
					while (input8.hasNext() == true)
					{
						String word = input8.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[6].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					System.out.printf("%2d%7s%20.3f%11d%12d%14d%13d\n", 6, ListNames[6], elapsed - overheadTime, Lists[6].getDistinctWords(), Lists[6].getTotalWords(), Lists[6].getKeyCompare(), Lists[6].getRefChanges());
					//System.out.println(((List7) Lists[6]).maxList() + " " + ((List7) Lists[6]).minList() + " " + ((List7) Lists[6]).averageLength() + " " + ((List7) Lists[6]).standardDev());	//code for Hash data, casting every instance to List7
					input8.close();
				}
				
				else if (i == 9)	//complete Hash 2
				{					//nearly identical to Hash 1
					long time;
					double elapsed;
					time = System.currentTimeMillis();
					Scanner input9 = new Scanner(file);
					while (input9.hasNext() == true)
					{
						String word = input9.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[7].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					System.out.printf("%2d%7s%20.3f%11d%12d%14d%13d\n", 7, ListNames[7], elapsed - overheadTime, Lists[7].getDistinctWords(), Lists[7].getTotalWords(), Lists[7].getKeyCompare(), Lists[7].getRefChanges());
					//System.out.println(((List8) Lists[7]).maxList() + " " + ((List8) Lists[7]).minList() + " " + ((List8) Lists[7]).averageLength() + " " + ((List8) Lists[7]).standardDev());
					input9.close();
				}
				
				else if (i == 10)	//complete Hash 3
				{					//nearly identical to Hash 1
					long time;
					double elapsed;
					time = System.currentTimeMillis();
					Scanner input10 = new Scanner(file);
					while (input10.hasNext() == true)
					{
						String word = input10.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[8].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					System.out.printf("%2d%7s%20.3f%11d%12d%14d%13d\n", 8, ListNames[8], elapsed - overheadTime, Lists[8].getDistinctWords(), Lists[8].getTotalWords(), Lists[8].getKeyCompare(), Lists[8].getRefChanges());
					//System.out.println(((List9) Lists[8]).maxList() + " " + ((List9) Lists[8]).minList() + " " + ((List9) Lists[8]).averageLength() + " " + ((List9) Lists[8]).standardDev());
					input10.close();
				}
				
				else if (i == 11)	//complete binary tree
				{
					long time;
					double elapsed;
					time = System.currentTimeMillis();
					Scanner input11 = new Scanner(file);
					while (input11.hasNext() == true)
					{
						String word = input11.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							Lists[9].add(word);
					}
					elapsed = ((System.currentTimeMillis() - time) / 1000.0);
					System.out.printf("%2d%12s%15.3f%11d%12d%14d%13d%5d\n", 9, ListNames[9], elapsed - overheadTime, Lists[9].getDistinctWords(), Lists[9].getTotalWords(), Lists[9].getKeyCompare(), Lists[9].getRefChanges(), ((List10) Lists[9]).getHeight());
					input11.close();
				}
				
			}
			
		}
		catch (Exception FileNotFound)	//ends program if there is no file
		{
			System.exit(0);
		}
		
		//code for testing first 100 words of Lists 3 and 4
		//LLNode location = test1.list;
		//while (location != null)
		//{
		//	System.out.println(location.getInfo() + " " + location.getCount());
		//	
		//	location = location.getNext();
		//}
		//
		//LLNode location = test2.list;
		//while (location != null)
		//{
		//	System.out.println(location.getInfo() + " " + location.getCount());
		//	
		//	location = location.getNext();
		//}
		
		//first one hundred words of List10
		BSTNode current1 = ((List10)Lists[9]).getMin();
		for (int i = 0; i < 100; i++)
		{
			if (current1 != null)
			{
				System.out.println(current1.getInfo());
				current1 = ((List10)Lists[9]).successor(current1);
			}
		}
		
		//first one hundred words of list2
		/*LLNode location1 = ((List2) Lists[1]).list;
		for (int i = 0; i < 100; i++)
		{
			System.out.println(location1.getInfo());
			location1 = location1.getNext();
		}*/
		
		//first one hundred words of list3
		/*LLNode location2 = ((List2a) Lists[5]).list;
		for (int i= 0; i < 100; i++)
		{
			System.out.println(location2.getInfo());
			location2 = location2.getNext();
		}*/
		
		//List lengths for Hashes
		/*for (int i = 0; i < 256; i++)
		{
			System.out.println(((List7) Lists[6]).listLength(i));
		}*/
			
	}
	
	public static String punctRemover(String word)
	{
		//checks for all punctuation at start
		while(word.startsWith("!") == true || word.startsWith("@") == true || word.startsWith("#") == true ||
			word.startsWith("$") == true || word.startsWith("%") == true || word.startsWith("^") == true ||
			word.startsWith("&") == true || word.startsWith("*") == true || word.startsWith("(") == true ||
			word.startsWith(")") == true || word.startsWith("_") == true || word.startsWith("+") == true ||
			word.startsWith("-") == true || word.startsWith("=") == true || word.startsWith("[") == true ||
			word.startsWith("]") == true || word.startsWith("\\") == true|| word.startsWith("{") == true ||
			word.startsWith("}") == true || word.startsWith("|") == true || word.startsWith(";") == true ||
			word.startsWith("\'") == true|| word.startsWith(":") == true || word.startsWith("\"") == true||
			word.startsWith("`") == true || word.startsWith("~") == true || word.startsWith(",") == true ||
			word.startsWith(".") == true || word.startsWith("/") == true || word.startsWith("<") == true ||
			word.startsWith(">") == true || word.startsWith("?") == true)
		{
			word = word.replace(word.charAt(0), ' ');	//switches the first character with an empty character
			word = word.trim();							//trims white space from word
		}
		//checks for all punctuation at end
		while (word.endsWith("!") == true || word.endsWith("@") == true || word.endsWith("#") == true ||
			word.endsWith("$") == true || word.endsWith("%") == true || word.endsWith("^") == true ||
			word.endsWith("&") == true || word.endsWith("*") == true || word.endsWith("(") == true ||
			word.endsWith(")") == true || word.endsWith("_") == true || word.endsWith("+") == true ||
			word.endsWith("-") == true || word.endsWith("=") == true || word.endsWith("[") == true ||
			word.endsWith("]") == true || word.endsWith("\\") == true|| word.endsWith("{") == true ||
			word.endsWith("}") == true || word.endsWith("|") == true || word.endsWith(";") == true ||
			word.endsWith("\'") == true|| word.endsWith(":") == true || word.endsWith("\"") == true||
			word.endsWith("`") == true || word.endsWith("~") == true || word.endsWith(",") == true ||
			word.endsWith(".") == true || word.endsWith("/") == true || word.endsWith("<") == true ||
			word.endsWith(">") == true || word.endsWith("?") == true)
		{
			word = word.replace(word.charAt(word.length() - 1), ' '); //switches last char with empty char
			word = word.trim(); //trims white space
		}
		
		return word;
	}
	
	
}

