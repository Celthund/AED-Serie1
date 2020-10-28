package serie1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static serie1.Arrays.median;

public class MedianTest {
	@Test
	public void getMedian_onArrayWith1Element(){
		int[] array={1};
		assertEquals(1,median(array,0,0));
	}
	
	@Test
	public void getMedian_onArrayWith2Elements(){
		int[] array={1,3};
		assertEquals(2,median(array,0,1));
	}
	
	@Test
	public void getMedian_onArrayWithOddNumberOfElements(){
		int[] array={20,2,10,9,7,6,5,4,30,50,60,1,0};
		assertEquals(7,median(array,0,array.length-1));
	}
	
	@Test
	public void getMedian_onArrayWithEvenNumberOfElements(){
		int[] array={20,2,10,9,7,5,3,4,30,50,60,1,0,70};
		assertEquals(8,median(array,0,array.length-1));
	}

	@Test
	public void getMedian_onSortedArray(){
		int[] array={1,2,3,4,5,6,7,8,9};
		assertEquals(5,median(array,0,array.length-1));
		assertEquals(5,median(array,1,array.length-2));
	}


}

	
