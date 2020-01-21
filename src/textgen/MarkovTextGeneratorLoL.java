package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String[] words = sourceText.split("[ ]+");

		starter = words[0];
		String prevWord = starter;
		if (sourceText != "") {
			wordList.add(new ListNode(prevWord));
		}
		int count = 0;
		for (String w : words) {
			if (count == 0) {
				count++;
				continue;
			}
			boolean inList = false;
			ListNode ln = null;
			int k = 1;
			for (ListNode node : wordList) {
				if (node.getWord().equals(prevWord)){
					inList = true;
				 	ln = node;
				 	break;
				}
				k++;
			}

			if (inList) {
				ln.addNextWord(w);
			}

			else {
				ln = new ListNode(prevWord);
				ln.addNextWord(w);
				wordList.add(ln);
			}
			prevWord = w;
		}
		boolean lastWordCheck = false;
		ListNode sameNode = null;
		for (ListNode n : wordList) {
			if (prevWord.equals(n.getWord())){
				lastWordCheck = true;
				sameNode = n;
			}
		}
		if (!lastWordCheck){
			sameNode = new ListNode(prevWord);
			sameNode.addNextWord(starter);
			wordList.add(sameNode);
		} else {
			sameNode.addNextWord(starter);
		}
		// TODO: Implement this method
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String currWord = starter;
		String output = "";
		int count = 0;
		while (numWords > count) {
			ListNode nodeOfCurrWord = null;
			if (numWords == 0) {
				break;
			}
			else if (count == 0) {
				output = output + currWord;
				count++;
				continue;
			}
			for (ListNode n : wordList) {
				if (currWord.equals(n.getWord())) {
					nodeOfCurrWord = n;
				}
			}

			String w = nodeOfCurrWord == null? "" : nodeOfCurrWord.getRandomNextWord(rnGenerator);
			if (!w.equals("")) {
				output += " " + w + " ";
			}
			else {
				output += w;
			}
			currWord = w;
			count++;
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		starter = "";
		wordList = new LinkedList<ListNode>();
		train(sourceText);
		// TODO: Implement this method.
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior

		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));

		System.out.println(gen.generateText(10));
		gen.train("hi there hi Leo");
		System.out.println(gen.generateText(0));
		System.out.println(gen);
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class

		int randomNum = generator.nextInt(nextWords.size());

	    return nextWords.get(randomNum);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


