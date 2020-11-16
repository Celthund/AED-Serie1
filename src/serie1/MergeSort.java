package serie1;

public class MergeSort {
    public static void mergeSort(int[] array){
        //Terminal Conditions
        if(array.length == 1) return;

        //Creates 2 arrays with half the length each
        int[] left = new int[array.length / 2];
        int[] right = new int[array.length - left.length];

        //Divides the array in 2
        divideArray(array, left, right);

        //Recursively divides all of the array
        mergeSort(left);
        mergeSort(right);

        //Merges the array by ascending order
        mergeArray(array, left, right);
    }

    //Method that divide the array in half
    private static void divideArray(int[] completeArray, int[] leftPart, int[] rightPart){
        int i;
        for (i = 0; i < leftPart.length; i++) leftPart[i] = completeArray[i];
        for (i = 0; i < rightPart.length; i++) rightPart[i] = completeArray[leftPart.length + i];
    }

    private static void mergeArray(int[] completeArray, int[] leftPart, int[] rightPart){
        int i = 0, l = 0, r = 0;

        //Merges the array by storing the smallest value between the left and right part on to
        //the completeArray
        while (l < leftPart.length && r < rightPart.length){
            if(leftPart[l] < rightPart[r]) completeArray[i++] = leftPart[l++];
            else completeArray[i++] = rightPart[r++];
        }

        //2 Cycles that will run what is left on one of the parts
        while (l < leftPart.length)
            completeArray[i++] = leftPart[l++];
        while (r < rightPart.length)
            completeArray[i++] = rightPart[r++];
    }
}
