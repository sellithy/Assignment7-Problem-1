import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDictionaryTest {
    @Test
    public void demo() {
        int testSize = 5;
        ArrayDictionary dict = new ArrayDictionary(testSize);
        assertTrue(dict.add(2, 82));
        assertTrue(dict.add(4, 84));
        assertTrue(dict.add(7, 87));
        System.out.println(dict);
    }

    @Test
    public void remove() {
        ArrayDictionary test = new ArrayDictionary(5);
        // CASE 1:
        assertFalse(test.remove(1));

        // CASE 2:
        test.add(0, 103);
        assertTrue(test.contains(0));
        assertTrue(test.remove(0));

        // CASE 3:
        test.add(0, 103);
        assertFalse(test.contains(1));

        // CASE 4:
        test.add(10, 115); // 10 collides with 0
        test.add(5, 109); // 5 collides with 0 and 10
        assertTrue(test.contains(5));
        assertTrue(test.contains(0));
        assertTrue(test.contains(10));
        assertTrue(test.remove(10));
        assertTrue(test.contains(0));
        assertTrue(test.contains(5));

        // CASE 5:
        test.add(1, 110); // 1 does not collide
        assertTrue(test.contains(1));
        assertTrue(test.remove(1));
        assertFalse(test.contains(1));

        // CASE 6:
        assertFalse(test.contains(1));
        assertFalse(test.remove(1));
    }

    @Test
    public void contains() {
        int[][] testCases = {
                {-1, 0, 1},
                {2, 0},
                {0, 1, 2, 3},
                {1, 4, 7, 8}
        };

        boolean[][] expected = {
                {false, false, false},
                {false, true},
                {true, true, false, false},
                {true, true, false, false},
        };

        ArrayDictionary[] sets = setUpSets();

        for (int i = 0; i < testCases.length; i++)
            testSet(sets[i], testCases[i], expected[i]);

        ArrayDictionary set4 = new ArrayDictionary(3);
        set4.add(0, 103); set4.add(1, 105);
        assertFalse(set4.contains(3));
        set4.add(2, 206);
        assertTrue(set4.contains(1));
    }

    private void testSet(ArrayDictionary set, int[] cases, boolean[] expected){
        for (int i = 0; i < cases.length; i++) {
            boolean actual = set.contains(cases[i]);
            assertEquals(expected[i], actual);
        }
    }

    private ArrayDictionary[] setUpSets(){
        ArrayDictionary[] sets = {
                new ArrayDictionary(1),
                new ArrayDictionary(1),
                new ArrayDictionary(2),
                new ArrayDictionary(3),
        };

        sets[1].add(0, 103);

        sets[2].add(0, 103);
        sets[2].add(1, 105);

        sets[3].add(0, 103);
        sets[3].add(1, 105);
        sets[3].add(2, 206);
        sets[3].add(4, 407);

        return sets;
    }
}
