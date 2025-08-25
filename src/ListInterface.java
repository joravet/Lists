// ListInterface.java
//
// This interface lists all of the methods that the four test lists in this project
// have to support -- adding a word to the list (aka insert), and getters for the
// total number of key comparisons and reference changes, as well as the counts
// for distinct and total words (after the lists have been built)
//

public interface ListInterface
{
	public void add(String word); // add this word to the linked list
	
	public long getKeyCompare(); // Get the number of key comparisons
	public long getRefChanges(); // Get the number of reference changes
	 
	public int getDistinctWords(); // Get the # of distinct words on the list
	public int getTotalWords(); // Get the total number of ALL words 
}
