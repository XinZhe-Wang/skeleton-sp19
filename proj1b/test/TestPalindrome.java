import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator test = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("Palindrome");
        String actual = "";
        for (int i = 0; i < "Palindrome".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("Palindrome", actual);
    }

    @Test
    public void testisPalindrome1(){
        String word1 = "Palindrome";
        boolean word_boolean1 = palindrome.isPalindrome(word1);
        assertFalse(word_boolean1);

        String word2 = "101";
        boolean word_boolean2 = palindrome.isPalindrome(word2);
        assertTrue(word_boolean2);
    }

    @Test
    public void testisPalindrome2(){
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",obo));
        assertFalse(palindrome.isPalindrome("lal",obo));
        assertTrue(palindrome.isPalindrome("aeadb",obo));
    }

    @Test
    public void testisPalindrome3(){
        OffByN obn = new OffByN(5);
        assertTrue(palindrome.isPalindrome("aaff",obn));
        assertFalse(palindrome.isPalindrome("linux",obn));
    }
}