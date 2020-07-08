import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char c1 = 'A';
        char c2 = 'B';
        char c3 = 'a';
        char c4 = 'c';

        assertTrue(offByOne.equalChars(c1, c2));
        assertTrue(offByOne.equalChars(c2, c1));
        assertFalse(offByOne.equalChars(c1, c3));
        assertFalse(offByOne.equalChars(c3, c4));
        assertFalse(offByOne.equalChars(c3, c3));
    }
}