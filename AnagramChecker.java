package assign04;

import java.util.Comparator;

public class AnagramChecker {

	public static String sort(String string) {
		char[] stringArray = string.toLowerCase().toCharArray();
		Character[] charArray = new Character[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			charArray[i] = (Character) stringArray[i];
		}
		AnagramChecker.insertionSort(charArray, (c1, c2) -> ( c1).compareTo(c2) );
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = (char) charArray[i];
		}
		String sortedString = String.valueOf(stringArray);
		return sortedString;
	}
	
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
	
	public static String[] getLargestAnagramGroup(String[] stringArray) {
		AnagramComparator cmp = new AnagramComparator();
		insertionSort(stringArray, cmp);
		int currentStreak = 1;
		int longestStreak = 0;
		int streakStart = 0;
		
		for (int i = 0; i < stringArray.length - 1; i++) {
			if (areAnagrams(stringArray[i], stringArray[i+1])) {
				currentStreak++;
			} else if (currentStreak > longestStreak) {
				longestStreak = currentStreak;
				streakStart = i - (currentStreak - 1);
				currentStreak = 1;
			} else currentStreak = 1;
		}
		String[] largestGroup = new String[longestStreak];
		for (int i = 0; i < largestGroup.length; i++) {
			largestGroup[i] = stringArray[i + streakStart];
		}
 		return largestGroup;
	}
	
	public static String[] getLargestAnagramGroup(String filename) {
		return null;
	}

	public static boolean areAnagrams(String string1, String string2) {
		String leftString = sort(string1);
		String rightString = sort(string2);
		if (leftString.equals(rightString)) {
			return true;
		}
		return false;
	}
	
	private static class AnagramComparator implements Comparator<String> {
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