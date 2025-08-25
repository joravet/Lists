/* Lab2.java
 * Jackson Oravetz
 * Dr. Thomas
 * EECS 2500
 * 11/3/2023
 * 
 * This class contains the main method and punctuation method used in Lab2.
 * The goal of this code is to create a file object, create a list object 
 * for each list, create a array of names, and to attempt to add the files
 * contents to each list. A try catch block accounts for a file not found 
 * error. The code can take input from the arguments tab in run config or
 * through the command line. File is read 6 different times, the first time
 * to fill the O/S buffers, the second to gather overhead time, the third
 * to add to List1, the fourth to add to List2, the fifth to add to List3,
 * and the sixth to add to List4. System.currentTimeMillis, scanners, and 
 * files are all necessary for the running of the code. Printf statements
 * are used to format the final output. The punctuation method searches for 
 * any leading or trailing punctuation and removes it and then returns the
 * punctuationless word. 
 */

import java.util.Scanner;

public class Lab2
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);	//create a scanner
		java.io.File file = new java.io.File(args[0]);	//**currently gives error** create a file
		ListInterface[] Lists = new ListInterface[4];	//creates array of lists
		Lists[0] = new List1();							//for modularity sake
		Lists[1] = new List2(); 
		Lists[2] = new List3(); 
		Lists[3] = new List4();
		
		//test vars for first 100 words
		/*List3 test1 = new List3();
		List4 test2 = new List4();*/
		
		//array of list names for same reason as above
		String[] ListNames = {"Unsorted", "Sorted", "Self-Adj (Front)", "Self-Adj (By one)"};
		
		try	//try to run code, if no file, go to catch
		{
			double overheadTime = 0;
			//code for i = 2-5 is identical, with only variation being which List is called
			for (int i = 0; i < 6; i++)	//do all 6 variations
			{
				if (i == 0)	//opens file and reads it one word at a time
				{			//print header if file exists
					Scanner input0 = new Scanner(file);
					while (input0.hasNext() == true)
					{
						String word = input0.next();
					}
					System.out.println(" #    List Name      Run Time  Vocabulary  Total Words   Key Comp     Ref Chgs  ");
					System.out.println("-- ----------------- --------  ----------  ----------- ------------ ------------");
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
					//All print statements will from here on out will out the 5 necessary outputs in a formatted manner
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
					System.out.printf("%2d%9s%18.3f%11d%12d%14d%13d\n", 2, ListNames[1], elapsed - overheadTime, Lists[1].getDistinctWords(), Lists[1].getTotalWords(), Lists[1].getKeyCompare(), Lists[1].getRefChanges());
					input3.close(); //close file
				}
				else if (i == 4)	//complete list3
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
					/*Scanner input4 = new Scanner(file);
					while (test1.getDistinctWords() <= 100)
					{
						String word = input4.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							test1.add(word);
					}*/
				}
				
				else if (i == 5)	//complete list4
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
					/*Scanner input5 = new Scanner(file);
					while (test2.getDistinctWords() <= 100)
					{
						String word = input5.next().toLowerCase();
						word = punctRemover(word);
						if (word != "")
							test2.add(word);
					}*/
				}
			}
			
		}
		catch (Exception FileNotFound)	//ends program if there is no file
		{
			System.exit(0);
		}
		
		//code for testing first 100 words
		/*LLNode location = test1.list;
		while (location != null)
		{
			System.out.println(location.getInfo() + " " + location.getCount());
			
			location = location.getNext();
		}*/
		
		/*LLNode location = test2.list;
		while (location != null)
		{
			System.out.println(location.getInfo() + " " + location.getCount());
			
			location = location.getNext();
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

