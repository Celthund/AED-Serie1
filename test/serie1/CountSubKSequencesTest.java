package serie1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static serie1.Arrays.countSubKSequences;

public class CountSubKSequencesTest {


    @Test
    public void countSubKSequences_EmptyArray(){
        assertEquals(0,countSubKSequences(new int[0],1));
        assertEquals(0,countSubKSequences(new int[0],3));
    }

    @Test
    public void countSubKSequences_SingletonArray(){
        int[] a={1};
        assertEquals(1,countSubKSequences(a,1));
        assertEquals(0,countSubKSequences(a,3));
    }

    @Test
    public void countSubKSequences_Example(){
        int[] a= {1,2,3,4,1};
        assertEquals(2,countSubKSequences(a,3));
    }

    @Test
    public void countSubKSequences_AllEquals(){
        int[] a={3,3,3,3,3,3,3};
        assertEquals(7,countSubKSequences(a,1));
        assertEquals(5,countSubKSequences(a,3));
        assertEquals(0,countSubKSequences(a,9));
    }
}
