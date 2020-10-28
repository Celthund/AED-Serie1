package serie1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static serie1.Arrays.lowerBound;

public class LowerBoundTest {

    static int[] v1 = new int[]{0,0,1,1,4,4};
    static int[] v2 = new int[]{0,0,0,1,1,1,4,4,4};
    @Test
    public void lowerBound_OnEmptyArray(){
        //retorna -1 nos arrays vazios.
        assertEquals( -1, lowerBound(v1, 0, -1, 0) );
        assertEquals( -1, lowerBound(v1, 1, 0, 0));
        assertEquals( -1, lowerBound(v1, v1.length, v1.length-1, 4));
    }

    @Test
    public void lowerBound_OnOneElementArray(){
        int[] a= new int[1];
        assertEquals( 0, lowerBound(a, 0, a.length - 1, 0));
        assertEquals( 0, lowerBound(a, 0, a.length - 1, -1));
        //o elemento não ocorre no array
        assertEquals( 1, lowerBound(a, 0, a.length - 1, 1));
    }

    @Test
    public void lowerBound_onBeginOfSequence(){
        assertEquals( 0, lowerBound(v1, 0, v1.length - 1, 0));
        assertEquals(0, lowerBound(v2, 0, v2.length - 1, 0));
    }

    @Test
    public void lowerBound_OnEndOfSequence(){
        assertEquals( 4,  lowerBound(v1, 0, v1.length-1, 4));
        assertEquals( 6, lowerBound(v2, 0, v2.length-1, 4));
    }

    @Test
    public void lowerBound_OnMiddle(){
        assertEquals( 2, lowerBound(v1, 0, v1.length-1, 1));
        assertEquals( 3, lowerBound(v2, 0, v2.length-1, 1));
    }

    @Test
    public void lowerBound_AllLessThan(){
        assertEquals( 6, lowerBound(v1, 0, v1.length-1, 5));
        assertEquals( 9, lowerBound(v2, 0, v2.length-1, 5));
    }

    @Test
    public void lowerBound_AllGreaterThan(){
        assertEquals( 0, lowerBound(v1, 0, v1.length-1, -1));
        assertEquals( 0, lowerBound(v2, 0, v2.length-1, -1));
    }


}
