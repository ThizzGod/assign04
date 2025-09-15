package assign04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnagramCheckerTester {

    private String[] wordList;       
    private String[] smallAnagramGroup;
    private String[] noAnagrams;        

    @BeforeEach
    void setup() {
        wordList = new String[]{
            "carets", "Caller", "eat", "cellar", "recall", "Caters", "Ate", "caster",
            "aspired", "allergy", "despair", "asp", "pas", "least", "sap", "spa",
            "diapers", "praised", "crates", "Reacts", "bats", "tea", "Stab", "stale",
            "tabs", "recast", "darters", "Gallery", "starred", "code", "Coed", "deco",
            "traders", "traces"
        };

        smallAnagramGroup = new String[]{"eat", "tea", "Ate"};
        noAnagrams = new String[]{"caller", "gallery", "traders"};
    }
   

    @Test
    public void testSortBasicWord() {
        assertEquals("aelrt", AnagramChecker.sort("later"));
    }

    @Test
    public void testSortAlreadySorted() {
        assertEquals("abc", AnagramChecker.sort("abc"));
    }

    @Test
    public void testSortReverseOrder() {
        assertEquals("abc", AnagramChecker.sort("cba"));
    }

    @Test
    public void testSortWithMixedCasedLetters() {
        assertEquals("Caerst", AnagramChecker.sort("Caters"));
    }

    @Test
    public void testSortEmptyString() {
        assertEquals("", AnagramChecker.sort(""));
    }

    @Test
    void testAreAnagramsTrue() {
        assertTrue(AnagramChecker.areAnagrams("carets", "traces"));
    }

    @Test
    void testAreAnagramsFalse() {
        assertFalse(AnagramChecker.areAnagrams("carets", "gallery"));
    }

    @Test
    void testAreAnagramsFalseDifferentFrequency() {
        assertFalse(AnagramChecker.areAnagrams("bats", "stabbed"));
    }

    @Test
    void testAreAnagramsCases() {
        assertTrue(AnagramChecker.areAnagrams("Caters", "traces"));
    }

    @Test
    void testAreAnagramsEmptyStrings() {
        assertTrue(AnagramChecker.areAnagrams("", ""));
    }

    @Test
    void testLargestGroupFromSampleList() {
        String[] group = AnagramChecker.getLargestAnagramGroup(wordList);
        assertEquals(7, group.length);
    }

    @Test
    void testLargestGroupTie() {
        String[] group = AnagramChecker.getLargestAnagramGroup(smallAnagramGroup);
        assertEquals(3, group.length);
    }

    @Test
    void testLargestGroupNoAnagrams() {
        String[] group = AnagramChecker.getLargestAnagramGroup(noAnagrams);
        assertEquals(0, group.length);
    }

    @Test
    void testLargestGroupEmptyArray() {
        String[] group = AnagramChecker.getLargestAnagramGroup(new String[]{});
        assertEquals(0, group.length);
    }
    
    @Test
    void testGetLargestAnagramGroupFromFile() {
        String[] group = AnagramChecker.getLargestAnagramGroup(wordList);
    	String[] largestGroupFile = AnagramChecker.getLargestAnagramGroup("word-list.txt");
    	assertTrue(largestGroupFile.length == group.length); 
    	for(int i = 0; i < group.length; i++) {
    		assertEquals(largestGroupFile[i], group[i]);
    	}
    }
    
    @Test
    void testGetLargestAnagramGroupFromFileFileEmpty() {
    	String[] largestGroupStrings = AnagramChecker.getLargestAnagramGroup("empty.txt");
    	assertTrue(largestGroupStrings.length == 0);
    }
    
    @Test
    void testGetLargestAnagramGroupFromFileFileDNE() {
    	String[] largestGroupStrings = AnagramChecker.getLargestAnagramGroup("DNE.txt");
    	assertTrue(largestGroupStrings.length == 0);
    }
}