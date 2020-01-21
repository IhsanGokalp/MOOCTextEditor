package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor

    public DictionaryLL() {
        dict = new LinkedList<String>();
    }

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
        word = word.toLowerCase();
        for (String wordInDict : dict) {
            if (word.equals(wordInDict)) {
                return true;
            }
        }
        dict.add(word);
        return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        int count = 0;
        for (String word : dict) {
            count++;
        }
        return count;
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
        s = s.toLowerCase();
        for (String word : dict) {
            if (word.equals(s))
                return true;
        }

        return false;
    }

    
}
