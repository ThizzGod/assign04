package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author Max Barker and Josi Gac 
 * Contains methods for checking if strings are anagrams and finding the
 * largest group of anagrams in a collection of words
 */
public class AnagramChecker {

	/**
	 * Sorts the inputed string in lexicographic order
	 * @param string the string needing to be sorted
	 * @return the sorted string
	 */
	public static String sort(String string) {
		char[] stringArray = string.toCharArray();
		Character[] charArray = new Character[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			charArray[i] = (Character) stringArray[i];
		}
		AnagramChecker.insertionSort(charArray, (c1, c2) -> ((Character) c1).compareTo((Character)c2) );
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = (char) charArray[i];
		}
		String sortedString = String.valueOf(stringArray);
		return sortedString;
	}
	
	/**
	 * Sorts the array given using insertion sort and the given comparator
	 * @param <T> type of elements in the array
	 * @param arr the array to be sorted
	 * @param cmp the comparator used to compare elements
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		int current = 1;
		int previous = 0;
		
		for (int i = 1; i <= arr.length; i++ ) {
			while ((previous >= 0) && (cmp.compare(arr[current], arr[previous]) < 0)) {
				T placeholder = arr[current];
				arr[current] = arr[previous];
				arr[previous] = placeholder;
				current--;
				previous--;
			}
			current = i;
			previous = i - 1;
		}
		
	}
	
	/**
	 * Finds and returns the largest group of anagrams in the given array,
     * if groups are tied for largest then any one of them will be returned
	 * @param stringArray the array to check for anagrams in
	 * @return an array containing the largest group of anagrams 
	 */
	public static String[] getLargestAnagramGroup(String[] stringArray) {
		AnagramComparator cmp = new AnagramComparator();
		insertionSort(stringArray, cmp);
		int currentStreak = 1;
		int longestStreak = 0;
		int streakStart = 0;

		for (int i = 0; i < stringArray.length - 1; i++) {
			if (areAnagrams(stringArray[i], stringArray[i+1])) {
				currentStreak++;
				if (currentStreak == stringArray.length) {
					longestStreak = currentStreak;
				}
			} else if (currentStreak > longestStreak) {
				longestStreak = currentStreak;
				streakStart = i - (currentStreak - 1);
				currentStreak = 1;
			} else currentStreak = 1;
		}
		String[] largestGroup = new String[longestStreak];
		if (longestStreak == 1) largestGroup = new String[0];
		for (int i = 0; i < largestGroup.length; i++) {
			largestGroup[i] = stringArray[i + streakStart];
		}
 		return largestGroup;
	}
	
	/**
	 * Finds and returns the largest group of anagrams in the given file,
     * if groups are tied for largest then any one of them will be returned
	 * @param filename the name of the file to be read from
	 * @return an array containing the largest group of anagrams 
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		ArrayList<String> wordsOnFile = new ArrayList<>();
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				wordsOnFile.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			return new String[0];
		}
		if (wordsOnFile.isEmpty()) {
			return new String[0];
		}
		String[] stringArray = wordsOnFile.toArray(new String[0]);
		return getLargestAnagramGroup(stringArray);
	}

	/**
	 * Checks if two strings are anagrams by sorting them and then checking if their equal
	 * @param string1 the first word
	 * @param string2 the second word
	 * @return true if they're anagrams or false if they aren't
	 */
	public static boolean areAnagrams(String string1, String string2) {
		String leftString = sort(string1.toLowerCase());
		String rightString = sort(string2.toLowerCase());
		if (leftString.equals(rightString)) {
			return true;
		}
		return false;
	}
	
	/**
	 * A helper class for comparing group anagrams
	 */
	private static class AnagramComparator implements Comparator<String> {
		/**
		 * This is used specifically for grouping anagrams, checks if 2 strings are anagrams
		 * @param s1 the first word
		 * @param s2 the second word
		 * @return returns 1 if they're anagrams, -1 if they aren't
		 */
		public int compare(String s1, String s2) {
			if (areAnagrams(s1, s2)) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
}