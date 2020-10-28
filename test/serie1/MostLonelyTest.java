package serie1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static serie1.Arrays.mostLonely;

public class MostLonelyTest {
    @Test
    public void mostLonely_onArrayWithOneElement() {
        int[] a= new int[] {5};
        assertEquals(5, mostLonely(a));
    }

    @Test
    public void mostLonely_onArrayWithTwoElement() {
        int[] a= new int[] {5,10};
        assertEquals(5, mostLonely(a));
    }

    @Test
    public void mostLonely_onArraySomeElements_Right() {
        int[] a= new int[] {10, 16, 1, 17, 25, 5};
        assertEquals(25, mostLonely(a));
    }


    @Test
    public void mostLonely_onArrayWithSomeElements_Middle(){
        int[] a= new int[] {10, 16, 1, 17, 5};
        assertEquals(10, mostLonely(a));
    }

    @Test
    public void mostLonely_onArraySomeElements_Left() {
        int[] a= new int[] {10, 16, 1, -10, 17, 25, 5};
        assertEquals(-10, mostLonely(a));
    }

    @Test
    public void mostLonely_withMoreThanOne() {
        int[] a= new int[] { 15, 10, 17, 20, 5};
        assertEquals(5, mostLonely(a));
    }

    @Test
    public void mostLonely_withEquals() {
        int[] a= new int[] {5,  15, 10, 17, 10, 19, 5};
        assertEquals(15, mostLonely(a));
    }
}
