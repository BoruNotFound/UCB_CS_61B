import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");

        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            char c = (char) d.removeFirst();
            actual += c;
        }
        assertEquals("persiflage", actual);
    }

    /** Tests isPalindrome() works fine in Palindrome and OffByOne. */
    @Test
    public void testIsPalindrome1() {
        String w1 = "noon";
        String w2 = "A";
        String w3 = "abbba";
        String w4 = "";
        String w5 = "abbbbb";
        String w6 = "horroh";

        assertTrue(palindrome.isPalindrome(w1));
        assertTrue(palindrome.isPalindrome(w2));
        assertTrue(palindrome.isPalindrome(w3));
        assertTrue(palindrome.isPalindrome(w4));
        assertFalse(palindrome.isPalindrome(w5));
        assertTrue(palindrome.isPalindrome(w6));
    }

    /** Tests isPalindrome() works fine. */
    @Test
    public void testIsPalindrome2() {
        String w1 = "flake";
        String w2 = "A";
        String w3 = "abbba";
        String w4 = "";
        String w5 = "abbbbb";
        String w6 = "horroh";

        assertTrue(offByOne.isPalindrome(w1));
        assertTrue(offByOne.isPalindrome(w2));
        assertFalse(offByOne.isPalindrome(w3));
        assertTrue(offByOne.isPalindrome(w4));
        assertFalse(offByOne.isPalindrome(w5));
        assertFalse(offByOne.isPalindrome(w6));
    }
}