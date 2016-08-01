package com.zillion.challenge;

import java.util.HashMap;

/**
 * PatentHolder class used to store patent holder name, 
 * can calculate and store palindrome count(default = 1)
 * 
 * @author James Drohan
 *
 */
public class PatentHolder implements Comparable<PatentHolder> {
	
	String name;
	int count;
	
	public PatentHolder(String name) {
		this.name = name;
		this.count = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * Calculate Palindrome Count based on Concatenated Name
	 */
	public int calculatePalindromeCount() {
		// Concatenate name
		String flatName = name.replaceAll(" ","").toLowerCase();
		flatName = flatName.replaceAll("\"","");
		// Get Unique Letters
		//System.out.println("NAME from PH " + flatName);
		HashMap<Character,Integer> nameChars = new HashMap<Character,Integer>();	
		for (char c : flatName.toCharArray()) {
			//System.out.println("Char " + c);
			if(nameChars.containsKey(c)) {
				nameChars.put(c,nameChars.get(c)+1);
			}
			else {
				nameChars.put(c,1);
			}
		}
		// Palindrome permutations are the number of unique chars raised to half the length of the name
		int uniqueChars = nameChars.size();
		int halfLength = flatName.length() / 2;
		halfLength += flatName.length() % 2;
		// Starting point should init with number of unique chars
		int nomOfPermutations = uniqueChars;
		for (int i = 1; i < halfLength; ++i) {
			nomOfPermutations = nomOfPermutations * uniqueChars;
		}
		return nomOfPermutations;
	}
	
	public int compareTo(PatentHolder patentHolder) {
		// Descending Order
		if(this.getCount() == patentHolder.getCount()) {
			return 0;
		}
		else {
			return this.getCount() > patentHolder.getCount() ? 1 : -1;
		}
	}

}
