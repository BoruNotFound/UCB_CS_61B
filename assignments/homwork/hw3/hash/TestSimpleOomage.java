package hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        // it's not a perfect hashCode due to too much collision by simply using r + b + g
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    /* NOTE!!! by Boru
     * java HashSet.contains(Object o) returns true if and only if:
     * the set contains an element e that
     * o == null ? e == null : o.equals(e)
     * so contains() method will use .equals() method to determine whether two objects are equal
     * In other words, even ooA (5, 10, 20) and ooA2(10, 5, 20) have the same hashCode,
     * hashSet.contains(ooA2) should be false because they are not equal.
     */
    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(10, 5, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        // System.out.println(ooA.hashCode()); // prints out 35
        // System.out.println(ooA2.hashCode()); // prints out 35
        assertFalse(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCodeSpread in OomageTestUtility */
    /*@Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }*/

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
